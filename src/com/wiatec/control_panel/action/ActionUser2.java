package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.User2Dao;
import com.wiatec.control_panel.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * user1 controller
 */
@Controller
public class ActionUser2 extends BaseAction {

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

    private int currentPage;
    private int totalPage;
    private static final int COUNT_OF_PAGE = 15;
    private int turn;
    private int totalCount; //the total count of table 'user1'
    private int totalOnlineCount; //the total count of online user

    @Autowired
    private User2Service user2Service;
    @Autowired
    private User2Dao user2Dao;

    /**
     * 登录到user control panel
     * @return
     */
    public String login() {
        if ("LDadmin9#".equals(user1Info.getUserName()) && "mimaLD77@#".equals(user1Info.getPassword())){
            session.setAttribute("countryCode","LDadmin9#");
            return "login";
        }else {
            throw new RuntimeException("user info error");
        }
    }

    /**
     * 根据检索条件和页数信息分页显示信息
     * @return
     */
    public String showByPage(){
        checkSession();
        if(turn == 2){ //next page
            currentPage += 1;
        }else if(turn == 1){ //previous page
            currentPage -= 1;
        }
        totalOnlineCount = SessionListener.sessionMap.size();
        totalCount = user2Dao.getTotalCount();
        totalPage = user2Dao.getTotalCountByCondition(selectionArray[searchKey], condition) / COUNT_OF_PAGE + 1;
        if(currentPage > totalPage){
            currentPage = totalPage;
        }
        if(currentPage < 1){
            currentPage = 1;
        }
        user1InfoList = user2Service.searchOfPage(selectionArray[searchKey], condition,currentPage , COUNT_OF_PAGE);
        return "show";
    }

    public String online(){
        user1InfoList = user2Service.selectOnline();
        return "online";
    }

    /**
     * user control panel active user email
     */
    public String active(){
        user1Info = new User1Info();
        user1Info.setUserName(userName);
        if(user2Service.active(user1Info)){
            return "active";
        }else{
            throw new RuntimeException("active failure");
        }
    }

    /**
     * user control panel delete user by username
     * @return 通过redirect跳转到(/WEB-INF/jsp/user1/show.jsp)
     */
    public String delete(){
        user1Info = new User1Info();
        user1Info.setUserName(userName);
        user2Service.delete(user1Info);
        return "delete";
    }

    /**
     * show user details by username
     * @return
     */
    public String details(){
        user1Info = user2Service.details(userName);
        return "details";
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalOnlineCount() {
        return totalOnlineCount;
    }

    public void setTotalOnlineCount(int totalOnlineCount) {
        this.totalOnlineCount = totalOnlineCount;
    }
}
