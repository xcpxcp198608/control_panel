package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.User1Dao;
import com.wiatec.control_panel.utils.EmailMaster;
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

/**
 * Created by xuchengpeng on 07/06/2017.
 */
@Service
public class User1Service {

    @Autowired
    private User1Dao user1Dao;
    private ResultInfo resultInfo = new ResultInfo();

    @Transactional
    public ResultInfo register(User1Info user1Info, String language){
        if(TextUtils.isEmpty(user1Info.getUserName())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "username error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPassword())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "password error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getEmail())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "email error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPhone())){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "phone error");
            return resultInfo;
        }
        if(user1Dao.isUserNameExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "username exists, please create another username");
            return resultInfo;
        }
        if(user1Dao.isEmailExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "email exists, please use another email");
            return resultInfo;
        }
        if(user1Dao.isEthernetMacExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "This BTVi3 already registered before, please contact customer support. ");
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
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_SUCCESS + " Please check your email to confirm and activate " +
                    "the account. The activation email may take up to 60 minutes to arrive, if you didn't get the email, " +
                    "please contact customer service.");
            return resultInfo;
        }else {
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE + "error code 101");
            return resultInfo;
        }
    }

    @Transactional
    public ResultInfo login(User1Info user1Info, HttpServletRequest request){
        if(TextUtils.isEmpty(user1Info.getUserName())){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_INFO_ERROR + "username error");
            return resultInfo;
        }
        if(TextUtils.isEmpty(user1Info.getPassword())){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_INFO_ERROR + "password error");
            return resultInfo;
        }
        if(!user1Dao.validate(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "username and password not match");
            return resultInfo;
        }
        if(!user1Dao.validateEmail(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "email no validate");
            return resultInfo;
        }
        if(!user1Dao.isEthernetMacExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "this device is not registered");
            return resultInfo;
        }
        if(!user1Dao.validateEthernetMac(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "username and this BTVi3 S/N do not match");
            return resultInfo;
        }
        user1Info.setToken(TokenUtils.create(user1Info.getUserName(), user1Info.getPassword()));
        if(!user1Dao.updateToken(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "token update failure");
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

    @Transactional
    public ResultInfo checkRepeat(User1Info user1Info , int count, HttpServletRequest request){
//        System.out.println(user1Info);
        if(!user1Dao.isUserNameExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + "username not exists");
            return resultInfo;
        }
        HttpSession session = SessionListener.getSession(user1Info.getUserName());
        if(session == null) {
            session = request.getSession();
            session.setAttribute("userName", user1Info.getUserName());
            session.setAttribute("count", count);
            setCheckResult(resultInfo , count , user1Info);
        }else{
            int currentCount = (int) session.getAttribute("count");
            if(count >= currentCount) {
                session.setAttribute("count", count);
                setCheckResult(resultInfo , count , user1Info);
            }else{
                resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
                resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR +
                        " currentCount: "+currentCount +" count: "+count);
            }
        }
        user1Info.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(System.currentTimeMillis())));
        user1Dao.updateLocation(user1Info);
        return resultInfo;
    }

    private void setCheckResult(ResultInfo resultInfo, int count, User1Info user1Info){
        resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
        resultInfo.setLoginCount(count);
        //获取用户会员日期，如果超过当前日期将等级设置为1，否则返回当前等级
        Long memberTime = user1Dao.getMemberTime(user1Info);
        if(memberTime < System.currentTimeMillis()){
            resultInfo.setUserLevel(1);
        }else {
            resultInfo.setUserLevel(user1Dao.getLevel(user1Info));
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
    }

    @Transactional
    public ResultInfo resetPassword(User1Info user1Info){
        System.out.println(user1Info);
        if(!user1Dao.isUserNameExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "username not exists");
            return resultInfo;
        }
        if(!user1Dao.isEmailExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "email not exists");
            return resultInfo;
        }
        String currentToken = user1Dao.getToken(user1Info);
        if(currentToken == null){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "token get error");
            return resultInfo;
        }
        EmailMaster emailMaster = new EmailMaster();
        emailMaster.setResetPasswordContent1(user1Info.getUserName(), currentToken);
        emailMaster.send(user1Info.getEmail());
        resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_REQUEST_SUCCESS + " Please check your email to confirm and activate " +
                "the account. The activation email may take up to 60 minutes to arrive, if you didn't get the email, " +
                "please contact customer service.");
        return resultInfo;
    }

    @Transactional
    public ResultInfo updatePassword(User1Info user1Info , String p1 , String p2){
        if(!user1Dao.isTokenExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "token not exists");
            return resultInfo;
        }
        if(!p1.equals(p2)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "password confirmation not match");
            return resultInfo;
        }
        user1Info.setPassword(p1);
        System.out.println(user1Info);
        if(user1Dao.updatePasswordByToken(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_SUCCESS);
        }else{
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE + "password reset error");
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo confirmEmail(User1Info user1Info){
        if(!user1Dao.isTokenExists(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE + "token invalidate");
            return resultInfo;
        }
        if(user1Dao.updateEmailStatusByToken(user1Info)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_SUCCESS);
            user1Info.setUserName(user1Dao.getUserNameByToken(user1Info));
            user1Info.setToken(TokenUtils.create(user1Info.getToken(), user1Info.getToken()));
            user1Dao.updateToken(user1Info);
        }else{
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE + "error code 102");
        }
        return resultInfo;
    }

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
        user1Info.setMemberDate(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(memberTime));
        user1Info.setLevel(level);
        user1Dao.updateLevel(user1Info);
    }

    @Transactional
    public boolean active(User1Info user1Info){
        return user1Dao.updateEmailStatusByUserName(user1Info);
    }

    @Transactional
    public List<User1Info> search(String selection, String condition){
        return user1Dao.search(selection , condition);
    }

    @Transactional
    public boolean delete(User1Info user1Info){
        return user1Dao.delete(user1Info);
    }

}
