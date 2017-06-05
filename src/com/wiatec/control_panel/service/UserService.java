package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.UserInfo;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.DeviceDao;
import com.wiatec.control_panel.repository.UserDao;
import com.wiatec.control_panel.utils.EmailMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xuchengpeng on 26/04/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DeviceDao deviceDao;

    @Transactional
    public ResultInfo register(UserInfo userInfo , DeviceInfo deviceInfo ,String language){
        ResultInfo resultInfo = new ResultInfo();
        if(userInfo.getUserName() == null || userInfo.getPassword() == null || userInfo.getEmail() == null){
            resultInfo.setCode(ResultInfo.CODE_INPUT_INFO_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_INPUT_INFO_ERROR);
            return resultInfo;
        }
        if(userDao.isUserNameExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_USER_NAME_EXISTS);
            resultInfo.setStatus(ResultInfo.STATUS_USER_NAME_EXISTS);
            return resultInfo;
        }
        if(userDao.isEmailExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_EXISTS);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_EXISTS);
            return resultInfo;
        }
        String token = createToken(userInfo);
        userInfo.setToken(token);
        userInfo.setRegisterTime(System.currentTimeMillis());
        deviceInfo.setActiveUserName(userInfo.getUserName());
        deviceInfo.setUserName(userInfo.getUserName());
        deviceInfo.setCurrentLoginTime("");
        deviceInfo.setRegisterTime(System.currentTimeMillis());
        System.out.println(userInfo);
        System.out.println(deviceInfo);
        if(deviceDao.isActiveUserNameExists(deviceInfo)){
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE+ " the user name exists");
            return resultInfo;
        }else {
            if (userDao.insert(userInfo) && deviceDao.insert(deviceInfo)) {
                EmailMaster emailMaster = new EmailMaster();
                emailMaster.setEmailContent(userInfo.getUserName(), token, language);
                emailMaster.send(userInfo.getEmail());
                resultInfo.setCode(ResultInfo.CODE_REGISTER_SUCCESS);
                resultInfo.setStatus(ResultInfo.STATUS_REGISTER_SUCCESS + " please check your email and activation your account" +
                        ", sometime the email received spend a few time, please be patient.");
                System.out.println(resultInfo);
                return resultInfo;
            } else {
                resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
                resultInfo.setStatus(ResultInfo.STATUS_REGISTER_FAILURE);
                return resultInfo;
            }
        }
    }

    @Transactional
    public ResultInfo login(UserInfo userInfo , DeviceInfo deviceInfo , HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        if(!userDao.validate(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_INFO_ERROR);
            return resultInfo;
        }
        if(!userDao.validateEmailStatus(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_VALIDATE_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_VALIDATE_ERROR);
            return resultInfo;
        }
        String token = createToken(userInfo);
        userInfo.setToken(token);
        userDao.updateToken(userInfo);
        HttpSession session = SessionListener.getSession(userInfo.getUserName());
        int count = 1;
        if(session == null){
            session = request.getSession();
            session.setAttribute("userName" ,userInfo.getUserName());
            session.setAttribute("count" ,count);
        }else{
            session.setAttribute("userName" ,userInfo.getUserName());
            count = (int) session.getAttribute("count");
            if( !(userInfo.getLevel() == 10)){
                count += 1;
            }
            session.setAttribute("count", count);
        }
        deviceInfo.setUserName(userInfo.getUserName());
        String currentTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        deviceInfo.setCurrentLoginTime(currentTime);
        if(deviceDao.isMacExists(deviceInfo)){
            if(deviceDao.isUserNameExists(deviceInfo)){
                deviceDao.cleanUserName(deviceInfo);
            }
            deviceDao.updateByMac(deviceInfo);
            String lastName = userDao.getLastName(userInfo)+"";
            resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
            resultInfo.setLoginCount(count);
            resultInfo.setToken(token);
            resultInfo.setExtra(lastName);
        }else if(deviceDao.isEthernetMacExists(deviceInfo)){
            if(deviceDao.isUserNameExists(deviceInfo)){
                deviceDao.cleanUserName(deviceInfo);
            }
            deviceDao.updateByEthernetMac(deviceInfo);
            String lastName = userDao.getLastName(userInfo)+"";
            resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
            resultInfo.setLoginCount(count);
            resultInfo.setToken(token);
            resultInfo.setExtra(lastName);
        }else{
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_INFO_ERROR + " this device does not register");
        }
        return resultInfo;
    }

    public ResultInfo checkRepeat(UserInfo userInfo ,DeviceInfo deviceInfo, int count , HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        if(!userDao.isUserNameExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR);
            return resultInfo;
        }
        HttpSession session = SessionListener.getSession(userInfo.getUserName());
        if(session == null){
            session = request.getSession();
            session.setAttribute("userName",userInfo.getUserName());
            session.setAttribute("count",count);
            setCheckResult(resultInfo,count,userInfo,deviceInfo);
        }else{
            int currentCount = (int) session.getAttribute("count");
            if(count >= currentCount){
                session.setAttribute("count" , count);
                setCheckResult(resultInfo,count,userInfo,deviceInfo);
            }else{
                resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
                resultInfo.setStatus(ResultInfo.STATUS_LOGIN_ERROR + " currentCount: "+currentCount +" count: "+count);
            }
        }
        return resultInfo;
    }

    private void setCheckResult(ResultInfo resultInfo, int count,UserInfo userInfo,DeviceInfo deviceInfo){
        resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_LOGIN_SUCCESS);
        resultInfo.setLoginCount(count);
        resultInfo.setUserLevel(userDao.getLevel(userInfo));
        long currentTime = System.currentTimeMillis();
        deviceInfo.setActiveUserName(userInfo.getUserName());
        String deviceRegisterTime = deviceDao.getRegisterTime(deviceInfo);
        long experienceLimitTime;
        if(deviceRegisterTime == null){
            experienceLimitTime = 0;
        }else {
            experienceLimitTime = Long.parseLong(deviceRegisterTime) + 604800000;
        }
        if(currentTime > experienceLimitTime){
            resultInfo.setExtra("false");
        }else{
            resultInfo.setExtra("true");
        }
    }

    @Transactional
    public ResultInfo confirmEmail(UserInfo userInfo){
        ResultInfo resultInfo = new ResultInfo();
        if(! userDao.isTokenExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE);
            return resultInfo;
        }
        if(userDao.updateEmailStatus(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_SUCCESS);
        }else{
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE);
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo checkUserLevel(UserInfo userInfo){
        UserInfo userInfo1 = userDao.getUserByName(userInfo);
        long currentTime = System.currentTimeMillis();
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
        resultInfo.setStatus(ResultInfo.STATUS_REQUEST_SUCCESS);
        if(currentTime > userInfo1.getMemberTime()){
            resultInfo.setUserLevel(1);
        }else {
            resultInfo.setUserLevel(userInfo1.getLevel());
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo resetPassword(UserInfo userInfo){
        String token = userDao.getToken(userInfo);
        ResultInfo resultInfo = new ResultInfo();
        if(token != null){
            EmailMaster emailMaster = new EmailMaster();
            emailMaster.setResetPasswordContent(userInfo.getUserName() , token);
            if(emailMaster.send(userInfo.getEmail())) {
                resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
                resultInfo.setStatus(ResultInfo.STATUS_REQUEST_SUCCESS + " please check your email, sometime the email " +
                        "received spend a few time, please be patient.");
            }else{
                resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
                resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE);
            }
            emailMaster = null;
        }else{
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus(ResultInfo.STATUS_REQUEST_FAILURE);
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo updatePassword(UserInfo userInfo , String p1 , String p2){
        ResultInfo resultInfo = new ResultInfo();
        if(p1.equals(p2)){
            userInfo.setPassword(p1);
            if(userDao.updatePassword(userInfo)) {
                resultInfo.setCode(ResultInfo.CODE_REQUEST_SUCCESS);
                resultInfo.setStatus("password reset success");
            }else{
                resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
                resultInfo.setStatus("password reset error");
            }
        }else{
            resultInfo.setCode(ResultInfo.CODE_REQUEST_FAILURE);
            resultInfo.setStatus("password different");
        }
        return resultInfo;
    }

    @Transactional
    public void changeMember(UserInfo userInfo , int level , int month){
        long memberTime = 0;
        long monthTimeMillis = 2592000000l * month;
        if(userInfo.getMemberTime() == 0){
            memberTime = System.currentTimeMillis() + monthTimeMillis;
        }else{
            memberTime = userInfo.getMemberTime() + monthTimeMillis;
        }
        userInfo.setMemberTime(memberTime);
        userInfo.setMemberDate(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(memberTime));
        userInfo.setLevel((short) level);
        userDao.updateMemberTime(userInfo);
    }

    //control panel 激活用户email
    @Transactional
    public boolean active(String userName){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        return userDao.active(userInfo);
    }

    //control panel 删除用户
    @Transactional
    public boolean delete(String userName){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        return userDao.delete(userInfo);
    }

    @Transactional
    public List<UserInfo> search(String key, String condition){
        List<UserInfo> list = userDao.search(key, condition);
        return list;
    }

    private String createToken(UserInfo userInfo){
        try {
            long time = System.currentTimeMillis();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((userInfo.getUserName()+userInfo.getPassword()+time).getBytes());
            BigInteger bigInteger = new BigInteger(1,messageDigest.digest());
            return bigInteger.toString(32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
