package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.User1Dao;
import com.wiatec.control_panel.service.User1Service;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * user1 controller
 */
@Controller
public class ActionUser1 extends BaseAction {

    private List<User1Info> user1InfoList;
    private User1Info user1Info;
    private String language;
    private int count;
    private String p1;
    private String p2;
    private String userName;
    private long memberTime;
    private int searchKey;
    private int level1;
    private int month1;
    private String condition;
    private String[] selectionArray = {"1", "id", "firstName", "lastName", "email", "userName", "emailStatus", "level"};
    private int[] levelArray = {1, 0, 1, 2, 3};
    private int[] monthArray = {0, 0, 1, 3, 6, 12};
    private Map<String , HttpSession> userSessionMap;

    @Autowired
    private User1Service user1Service;
    @Autowired
    private User1Dao user1Dao;

    /**
     * 用户注册,需要提交的必要信息（userName, password , email , phone , firstName, lastName,
     * ethernetMac, language）
     */
    public void register() {
        resultInfo = user1Service.register(user1Info, language);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    /**
     * 用户登录，需要提交的必要信息(userName, password, ethernetMac)
     * 选填信息(country, region, city, timeZone)
     */
    public void login() {
        resultInfo = user1Service.login(user1Info, request);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    /**
     * 用户登录状态监测，需要提交的必要信息(userName , count)
     * 返回结果信息包含 level , 是否在7天试看期
     */
    public void checkRepeat() {
        resultInfo = user1Service.checkRepeat(user1Info, count, request);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    /**
     * 用户确认激活邮件
     */
    public void confirmEmail() {
        resultInfo = user1Service.confirmEmail(user1Info);
        if (resultInfo.getCode() == ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS) {
            out.println("ACTIVATION SUCCESS");
        } else {
            out.println("ACTIVATION FAILURE " + resultInfo.getStatus());
        }
        out.flush();
        out.close();
    }

    /**
     * 用户在设备上点击重置密码，需要提交(userName , email)
     */
    public void requestUpdateP() {
        resultInfo = user1Service.resetPassword(user1Info);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    /**
     * 用户邮箱点击链接跳转到reset password 页面
     * @return (/WEB-INF/jsp/register1/reset.jsp)
     */
    public String resetp() {
        return "resetp";
    }

    /**
     * 用户设置新密码后提交
     */
    public void updatep() {
        resultInfo = user1Service.updatePassword(user1Info, p1, p2);
        out.println(resultInfo.getStatus());
        out.flush();
        out.close();
    }

    /**
     * 登录到user control panel
     * @return
     */
    public String login1() {
        if ("USER".equals(user1Info.getUserName()) && "USER".equals(user1Info.getPassword())) {
            return "login1";
        } else {
            throw new RuntimeException("user info error");
        }
    }

    /**
     * user panel 显示所有用户状态
     * @return 跳转到(/WEB-INF/jsp/user1/show.jsp)
     */
    public String show() {
        user1InfoList = user1Dao.getAll(null, null);
        setEmailStatus(user1InfoList);
        System.out.println(user1InfoList);
        return "show";
    }

    /**
     * 根据指定条件搜索用户
     * @return 跳转到(/WEB-INF/jsp/user1/show.jsp)
     */
    public String search() {
        user1InfoList = user1Service.search(selectionArray[searchKey], condition);
        setEmailStatus(user1InfoList);
        return "show";
    }

    /**
     * 根据emailStatus 设置显示ACTIVE 和 NEGATIVE
     * @param user1InfoList 需要设置的用户列表
     */
    private void setEmailStatus(List<User1Info> user1InfoList) {
        if (user1InfoList != null && user1InfoList.size() > 0) {
            for (User1Info user1Info : user1InfoList) {
                if (user1Info.getEmailStatus() == 1) {
                    user1Info.setStatus("ACTIVE");
                } else {
                    user1Info.setStatus("NEGATIVE");
                }
            }
        }
    }

    /**
     * user control panel active user email
     */
    public String active(){
        user1Info = new User1Info();
        user1Info.setUserName(userName);
        if(user1Service.active(user1Info)){
            return "active";
        }else{
            throw new RuntimeException("active failure");
        }

    }

    /**
     * 修改用户member 等级和期限
     * @return 通过redirect跳转到(/WEB-INF/jsp/user1/show.jsp)
     */
    public String changeMember() {
        if (level1 == 0 || month1 == 0) {
            return "show";
        }
        user1Info = new User1Info();
        user1Info.setUserName(userName);
        user1Info.setMemberTime(memberTime);
        user1Service.updateLevel(user1Info, levelArray[level1], monthArray[month1]);
        return "changeMember";
    }

    /**
     * 测试账号px点击Register 链接
     * @return
     */
    public String goRegister(){
        return "goRegister";
    }

    /**
     * 测试账号px点击Forget password 链接
     * @return (/WEB-INF/jsp/register1/goreset.jsp)
     */
    public String goreset() {
        return "goreset";
    }

    /**
     * 测试账号px点击status链接通过session显示在线用户状态
     * @return (/WEB-INF/jsp/user1/status.jsp)
     */
    public String status(){
        userSessionMap = SessionListener.sessionMap;
        user1InfoList = new ArrayList<>();
        for (Map.Entry<String, HttpSession> entry: userSessionMap.entrySet()){
            User1Info user1Info = user1Dao.getUserInfoByUserName(entry.getKey());
            if(user1Info != null){
                user1InfoList.add(user1Info);
            }
        }
        return "status";
    }

    /**
     * user control panel delete user by username
     * @return 通过redirect跳转到(/WEB-INF/jsp/user1/show.jsp)
     */
    public String delete(){
        user1Info = new User1Info();
        user1Info.setUserName(userName);
        user1Service.delete(user1Info);
        return "delete";
    }


    public List<User1Info> getUser1InfoList() {
        return user1InfoList;
    }

    public void setUser1InfoList(List<User1Info> user1InfoList) {
        this.user1InfoList = user1InfoList;
    }

    public User1Info getUser1Info() {
        return user1Info;
    }

    public void setUser1Info(User1Info user1Info) {
        this.user1Info = user1Info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMemberTime() {
        return memberTime;
    }

    public void setMemberTime(long memberTime) {
        this.memberTime = memberTime;
    }

    public int getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(int searchKey) {
        this.searchKey = searchKey;
    }

    public int getLevel1() {
        return level1;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public int getMonth1() {
        return month1;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Map<String, HttpSession> getUserSessionMap() {
        return userSessionMap;
    }

    public void setUserSessionMap(Map<String, HttpSession> userSessionMap) {
        this.userSessionMap = userSessionMap;
    }
}
