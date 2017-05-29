package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.UserInfo;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.UserDao;
import com.wiatec.control_panel.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuchengpeng on 26/04/2017.
 */
@Controller
public class ActionUser extends BaseAction {

    private UserInfo userInfo;
    private List<UserInfo> userInfoList;
    private DeviceInfo deviceInfo;
    private ResultInfo resultInfo;
    private String language;
    private int count;
    private String p1;
    private String p2;
    private int level1;
    private int month1;
    private List<Integer> levelList;
    private List<Integer> monthList;
    private String userName;
    private long memberTime;
    private Map<String, HttpSession> userSessionMap;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    public ActionUser() {
        levelList = new ArrayList<>();
        levelList.add(1);
        levelList.add(0);
        levelList.add(1);
        levelList.add(2);
        levelList.add(3);
        monthList = new ArrayList<>();
        monthList.add(1);
        monthList.add(1);
        monthList.add(3);
        monthList.add(6);
        monthList.add(12);
    }

    public String login1(){
        if("USER".equals(userInfo.getUserName()) && "USER".equals(userInfo.getPassword())){
            return "login1";
        }else{
            throw new RuntimeException("user info error");
        }
    }

    public String changeMember(){
        if(level1 ==0 || month1 ==0){
            return "show";
        }
        userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setMemberTime(memberTime);
        userService.changeMember(userInfo ,levelList.get(level1) ,monthList.get(month1));
        return "changeMember";
    }

    public String show(){
        userInfoList = userDao.getAll(null, null);
        for(UserInfo userInfo : userInfoList){
            if(userInfo.getEmailStatus() == 1){
                userInfo.setStatus("ACTIVE");
            }else{
                userInfo.setStatus("NEGATIVE");
            }
        }
        return "show";
    }

    public void register(){
        resultInfo = userService.register(userInfo , deviceInfo ,language);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public void login (){
        resultInfo = userService.login(userInfo , deviceInfo ,request);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public void checkRepeat(){
        resultInfo = userService.checkRepeat(userInfo, deviceInfo, count , request);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public void confirmEmail(){
        resultInfo = userService.confirmEmail(userInfo);
        if(resultInfo.getCode() == ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS){
            out.println("ACTIVATION SUCCESS");
        }else{
            out.println("ACTIVATION FAILURE");
        }
        out.flush();
        out.close();
    }

    public void checkLevel(){
        resultInfo = userService.checkUserLevel(userInfo);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public String goreset(){
        return "goreset";
    }

    public void requestUpdateP(){
        resultInfo = userService.resetPassword(userInfo);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public String resetp(){
        userInfo = userDao.get(userInfo);
        return "resetp";
    }

    public void updatep(){
        resultInfo = userService.updatePassword(userInfo ,p1 ,p1);
        out.println(resultInfo.getStatus());
        out.flush();
        out.close();
    }

    public String status(){
        userSessionMap = SessionListener.sessionMap;
        return "status";
    }

    public String goRegister(){
        return "goRegister";
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    @Override
    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
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

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
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

    public Map<String, HttpSession> getUserSessionMap() {
        return userSessionMap;
    }

    public void setUserSessionMap(Map<String, HttpSession> userSessionMap) {
        this.userSessionMap = userSessionMap;
    }
}
