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
            resultInfo.setSatus(ResultInfo.STATUS_INPUT_INFO_ERROR);
            return resultInfo;
        }
        if(userDao.isUserNameExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_USER_NAME_EXISTS);
            resultInfo.setSatus(ResultInfo.STATUS_USER_NAME_EXISTS);
            return resultInfo;
        }
        if(userDao.isEmailExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_EXISTS);
            resultInfo.setSatus(ResultInfo.STATUS_EMAIL_EXISTS);
            return resultInfo;
        }
        String token = createToken(userInfo);
        userInfo.setToken(token);
        deviceInfo.setActiveUserName(userInfo.getUserName());
        deviceInfo.setUserName(userInfo.getUserName());
        deviceInfo.setCurrentLoginTime("");
        if(userDao.insert(userInfo) && deviceDao.insert(deviceInfo)){
            EmailMaster emailMaster = new EmailMaster();
            emailMaster.setEmailContent(userInfo.getUserName() , token , language);
            emailMaster.send(userInfo.getEmail());
            resultInfo.setCode(ResultInfo.CODE_REGISTER_SUCCESS);
            resultInfo.setSatus(ResultInfo.STATUS_REGISTER_SUCCESS);
        }else{
            resultInfo.setCode(ResultInfo.CODE_REGISTER_FAILURE);
            resultInfo.setSatus(ResultInfo.STATUS_REGISTER_FAILURE);
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo login(UserInfo userInfo , DeviceInfo deviceInfo , HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        if(!userDao.validate(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_INFO_ERROR);
            resultInfo.setSatus(ResultInfo.STATUS_LOGIN_INFO_ERROR);
            return resultInfo;
        }
        if(!userDao.validateEmailStatus(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_VALIDATE_ERROR);
            resultInfo.setSatus(ResultInfo.STATUS_EMAIL_VALIDATE_ERROR);
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
            count ++;
            session.setAttribute("count", count);
        }
        deviceInfo.setUserName(userInfo.getUserName());
        String currentTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        deviceInfo.setCurrentLoginTime(currentTime);
        if(deviceDao.isMacExists(deviceInfo)){
            if(deviceDao.isUserNameExists(deviceInfo)){
                deviceDao.cleanUserName(deviceInfo);
                deviceDao.update(deviceInfo);
            }else{
                deviceDao.update(deviceInfo);
            }
        }else{
            if(deviceDao.isUserNameExists(deviceInfo)){
                deviceDao.cleanUserName(deviceInfo);
                deviceDao.insert(deviceInfo);
            }else{
                deviceDao.insert(deviceInfo);
            }
        }
        resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
        resultInfo.setSatus(ResultInfo.STATUS_LOGIN_SUCCESS);
        resultInfo.setLoginCount(count);
        resultInfo.setToken(token);
        return resultInfo;
    }

    public ResultInfo checkRepeat(UserInfo userInfo , int count , HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        if(!userDao.isUserNameExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
            resultInfo.setSatus(ResultInfo.STATUS_LOGIN_ERROR);
            return resultInfo;
        }
        HttpSession session = SessionListener.getSession(userInfo.getUserName());
        if(session ==null){
            session = request.getSession();
            session.setAttribute("userName",userInfo.getUserName());
            session.setAttribute("count",count);
            resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
            resultInfo.setSatus(ResultInfo.STATUS_LOGIN_SUCCESS);
            resultInfo.setLoginCount(count);
        }else{
            int currentCount = (int) session.getAttribute("count");
            if(currentCount == count){
                resultInfo.setCode(ResultInfo.CODE_LOGIN_SUCCESS);
                resultInfo.setSatus(ResultInfo.STATUS_LOGIN_SUCCESS);
                resultInfo.setLoginCount(count);
            }else{
                resultInfo.setCode(ResultInfo.CODE_LOGIN_ERROR);
                resultInfo.setSatus(ResultInfo.STATUS_LOGIN_ERROR + "currentCount:"+currentCount +"count"+count);
            }
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo confirmEmail(UserInfo userInfo){
        ResultInfo resultInfo = new ResultInfo();
        if(! userDao.isTokenExists(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setSatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE);
            return resultInfo;
        }
        if(userDao.updateEmailStatus(userInfo)){
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_SUCCESS);
            resultInfo.setSatus(ResultInfo.STATUS_EMAIL_CONFIRM_SUCCESS);
        }else{
            resultInfo.setCode(ResultInfo.CODE_EMAIL_CONFIRM_FAILURE);
            resultInfo.setSatus(ResultInfo.STATUS_EMAIL_CONFIRM_FAILURE);
        }
        return resultInfo;
    }

    @Transactional
    public ResultInfo checkUserLevel(UserInfo userInfo){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setUserLevel(userDao.getLevel(userInfo));
        return resultInfo;
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
