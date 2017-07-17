package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.User1Dao;
import com.wiatec.control_panel.utils.EmailMaster;
import com.wiatec.control_panel.utils.RegularUtil;
import com.wiatec.control_panel.utils.TextUtils;
import com.wiatec.control_panel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * user handler
 */
@Service
public class User1Service {

    @Autowired
    private User1Dao user1Dao;

    /**
     * user register
     * @param user1Info user info what user commit
     *        REQUIRED: (userName , password , email , phone , firstName , lastName , mac , etherNetMac)
     *        OPTIONAL： (country , region , city , timeZone)
     * @param language the email language of user select when register
     * @return result
     */
    @Transactional
    public ResultInfo register(User1Info user1Info, String language){
        ResultInfo resultInfo = new ResultInfo();
        if(TextUtils.isEmpty(user1Info.getUserName())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("username error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPassword())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("password error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getEmail())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("email error");
            return resultInfo;
        }
        if(!RegularUtil.validateEmail(user1Info.getEmail())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("email format error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPhone())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("Phone number can't be empty");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getEthernetMac())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("s/n(e) error");
            return resultInfo;
        }
        if(user1Info.getPhone().length() < 7){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("Phone number wrong format");
            return resultInfo;
        }
        if(!user1Info.getEthernetMac().startsWith("5C:41:E7")){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("S/N(E) format error, please contact customer support.");
            return resultInfo;
        }
        if(user1Dao.isUserNameExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("username exists, please create another username");
            return resultInfo;
        }
        if(user1Dao.isEmailExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("email exists, please use another email");
            return resultInfo;
        }
        if(user1Dao.isEthernetMacExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("This BTVi3(E) already registered before, " +
                    "please contact customer support. ");
            return resultInfo;
        }
        user1Info.setToken(TokenUtils.create(user1Info.getUserName(), user1Info.getPassword()));
        user1Info.setActiveTime(System.currentTimeMillis());
        user1Info.setLevel(1);
        System.out.println(user1Info);
        if(user1Dao.insert(user1Info)){
            EmailMaster emailMaster = new EmailMaster();
            emailMaster.setEmailContent1(user1Info.getUserName(), user1Info.getToken() , language);
            emailMaster.send(user1Info.getEmail());
            resultInfo.setCode(ResultInfo.CODE_REGISTER_SUCCESS);
            resultInfo.setStatus(" Please check your email to confirm and activate the account. " +
                    "The activation email may take up to 60 minutes to arrive, if you didn't get " +
                    "the email, please contact customer service.");
            return resultInfo;
        }else {
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus("server error , try again later");
            return resultInfo;
        }
    }

    /**
     * user login
     * @param user1Info user info
     *        REQUIRED: (userName , password , mac)
     *        OPTIONAL: (country , region , city , timeZone)
     * @param request servlet request
     * @return result
     */
    @Transactional
    public ResultInfo login(User1Info user1Info, HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        if(TextUtils.isEmpty(user1Info.getUserName())){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus("username error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPassword())){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus("password error");
            return resultInfo;
        }
        //验证测试账号
        if(user1Info.getUserName().startsWith("whd") && user1Info.getUserName().endsWith("n1")){
            if(!user1Dao.validate(user1Info)){
                resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
                resultInfo.setStatus("username and password not match");
                return resultInfo;
            }else{
                long activeTime = user1Dao.getActiveTime(user1Info);
                if(System.currentTimeMillis() > activeTime + 1296000000){
                    resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
                    resultInfo.setStatus("permission expired ");
                    return resultInfo;
                }else {
                    resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
                    resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
                    resultInfo.setUserLevel(user1Dao.getLevel(user1Info));
                    return resultInfo;
                }
            }
        }
        if(TextUtils.isEmpty(user1Info.getEthernetMac())){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus("S/N(E) error");
            return resultInfo;
        }
        if(!user1Dao.validate(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus("username and password not match");
            return resultInfo;
        }
        if(!user1Dao.validateEmail(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus("Account is not activated, please make sure to check the email to activate it.");
            return resultInfo;
        }
        if(!user1Dao.isEthernetMacExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus("this device is not registered");
            return resultInfo;
        }
        if(!user1Dao.validateEthernetMacAndUserName(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus("Login has already been used on other BTVi3");
            return resultInfo;
        }
        user1Info.setToken(TokenUtils.create(user1Info.getUserName(), user1Info.getPassword()));
        if(!user1Dao.updateToken(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus("Authorization update failure");
            return resultInfo;
        }
        user1Info.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        user1Dao.updateLocation(user1Info);
        HttpSession session = SessionListener.getSession(user1Info.getUserName());
        int count = 1;
        if(session == null){
            session = request.getSession();
            session.setAttribute("userName" ,user1Info.getUserName());
            session.setAttribute("count" ,count);
        }else{
            session.setAttribute("userName" ,user1Info.getUserName());
            count = (int) session.getAttribute("count");
            count += 1;
            session.setAttribute("count", count);
        }
        user1Info = user1Dao.getUserInfoByUserName(user1Info.getUserName());
        resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
        resultInfo.setLoginCount(count);
        resultInfo.setToken(user1Info.getToken());
        resultInfo.setExtra(user1Info.getLastName());
        resultInfo.setUserLevel(user1Info.getLevel());
        return resultInfo;
    }

    /**
     * check user login status
     * @param user1Info user info (userName)
     * @param count the last login count from server session and store in user device
     * @param request servlet request
     * @return  result
     */
    @Transactional
    public ResultInfo checkRepeat(User1Info user1Info , int count, HttpServletRequest request){
//        System.out.println(user1Info);
        if(!user1Dao.isUserNameExists(user1Info)){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "username not exists");
            return resultInfo;
        }
        HttpSession session = SessionListener.getSession(user1Info.getUserName());
        if(session == null) {
            session = request.getSession();
            session.setAttribute("userName", user1Info.getUserName());
            session.setAttribute("count", count);
        }
        ResultInfo resultInfo = setCheckResult(count, user1Info);
        user1Info.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(System.currentTimeMillis())));
        user1Dao.updateLocation(user1Info);
        return resultInfo;
    }

    /**
     * set user level and 7 days experience period
     */
    private ResultInfo setCheckResult(int count, User1Info user1Info){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
        resultInfo.setLoginCount(count);
        //获取用户会员日期，如果超过当前日期将等级设置为1，否则返回当前等级
        int level = user1Dao.getLevel(user1Info);
        Long memberTime = user1Dao.getMemberTime(user1Info);
        if(level == 0){
            resultInfo.setUserLevel(level);
        }else {
            System.out.println(memberTime);
            if (memberTime > 0 && memberTime < System.currentTimeMillis()) {
                resultInfo.setUserLevel(1);
            } else {
                resultInfo.setUserLevel(user1Dao.getLevel(user1Info));
            }
        }
        //判断用户注册时间到现在时间是否超过7天，没有超出返回true，用于live play判断是否在7天预览期
        long currentTime = System.currentTimeMillis();
        long deviceActiveTime = user1Dao.getActiveTime(user1Info);
        long experienceLimitTime = deviceActiveTime + 604800000;
        if(currentTime > experienceLimitTime){
            resultInfo.setExtra("false");
        }else{
            resultInfo.setExtra("true");
        }
        return resultInfo;
    }

    /**
     * user wang reset password
     * @param user1Info user info （userName , email）
     * @return
     */
    @Transactional
    public ResultInfo resetPassword(User1Info user1Info){
        ResultInfo resultInfo = new ResultInfo();
        System.out.println(user1Info);
        if(!user1Dao.isUserNameExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("username not exists");
            return resultInfo;
        }
        if(!user1Dao.isEmailExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("email not exists");
            return resultInfo;
        }
        String currentToken = user1Dao.getToken(user1Info);
        if(TextUtils.isEmpty(currentToken)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("Authorization error , please try again later");
            return resultInfo;
        }
        EmailMaster emailMaster = new EmailMaster();
        emailMaster.setResetPasswordContent1(user1Info.getUserName(), currentToken);
        emailMaster.send(user1Info.getEmail());
        resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
        resultInfo.setStatus("Please check your email to confirm and reset the account password. " +
                "The reset email may take up to 60 minutes to arrive, if you didn't get the " +
                "email, please contact customer service.");
        return resultInfo;
    }

    /**
     * user press down RESET PASSWORD after received the reset email
     * @param user1Info reset info (token)
     * @param p1 the new password
     * @param p2 confirm new password
     * @return
     */
    @Transactional
    public ResultInfo updatePassword(User1Info user1Info , String p1 , String p2){
        ResultInfo resultInfo = new ResultInfo();
        if(!user1Dao.isTokenExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("Authorization error");
            return resultInfo;
        }
        if(!p1.equals(p2)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("password confirmation not match");
            return resultInfo;
        }
        user1Info.setPassword(p1);
        System.out.println(user1Info);
        if(user1Dao.updatePasswordByToken(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_SUCCESS);
            resultInfo.setObject(user1Dao.getUserInfoByToken(user1Info.getToken()));
        }else{
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("password reset error");
        }
        return resultInfo;
    }

    /**
     * user press down ACTIVE ACCOUNT after received the reset email
     * @param user1Info token info from email link
     * @return active result
     */
    @Transactional
    public ResultInfo confirmEmail(User1Info user1Info){
        ResultInfo resultInfo = new ResultInfo();
        if(!user1Dao.isTokenExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus("Authorization error");
            return resultInfo;
        }
        if(user1Dao.updateEmailStatusByToken(user1Info)){
            User1Info user1Info1 = user1Dao.getUserInfoByToken(user1Info.getToken());
            resultInfo.setObject(user1Info1);
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_SUCCESS);
            user1Info.setUserName(user1Dao.getUserNameByToken(user1Info));
            user1Info.setToken(TokenUtils.create(user1Info.getToken(), user1Info.getToken()));
            user1Dao.updateToken(user1Info);
        }else{
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus("server error, please try again later");
        }
        return resultInfo;
    }

    /**
     * update user member level
     * @param user1Info user info (userName)
     * @param level target level
     * @param month validate time
     */
    @Transactional
    public void updateLevel(User1Info user1Info , int level , int month){
        long memberTime;
        long perMonthTimeMillis = 2592000000l * month;
        System.out.println(user1Info);
        if(user1Info.getMemberTime() <= 0){
            memberTime = System.currentTimeMillis() + perMonthTimeMillis;
        }else{
            memberTime = user1Info.getMemberTime() + perMonthTimeMillis;
        }
        user1Info.setMemberTime(memberTime);
        user1Info.setMemberDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(memberTime));
        user1Info.setLevel(level);
        user1Dao.updateLevel(user1Info);
    }

    /**
     * show user information by page
     * @param selection
     * @param condition
     * @param currentPage
     * @param countOfPage
     * @return
     */
    @Transactional(readOnly = true)
    public List<User1Info> searchOfPage(String selection, String condition, int currentPage,
                                        int countOfPage){
        List<User1Info> list = user1Dao.searchOfPage(selection, condition, currentPage, countOfPage);
        Map<String, HttpSession> map = SessionListener.sessionMap;
        for(User1Info user1Info: list){
            if(map.containsKey(user1Info.getUserName())){
                user1Info.setOnline(true);
            }
        }
        return list;
    }

    /**
     * administrator active user email when user can not active account
     * @param user1Info
     * @return
     */
    @Transactional
    public boolean active(User1Info user1Info){
        return user1Dao.updateEmailStatusByUserName(user1Info);
    }

    /**
     * administrator search the user info by selection and condition
     * @param selection search selection mapping the column in user1 table
     *                  (id , userName , email, firstName , lastName , emailStatus , )
     * @param condition
     * @return
     */
    @Transactional
    public List<User1Info> search(String selection, String condition){
        return user1Dao.search(selection , condition);
    }

    /**
     * get all of user information by username
     * @param userName
     * @return
     */
    public User1Info details(String userName){
        return user1Dao.getUserInfoByUserName(userName);
    }

    /**
     * administrator delete user
     * @param user1Info
     * @return
     */
    @Transactional
    public boolean delete(User1Info user1Info){
        return user1Dao.delete(user1Info);
    }

}
