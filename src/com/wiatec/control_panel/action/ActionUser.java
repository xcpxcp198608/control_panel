package com.wiatec.control_panel.action;

import com.sun.xml.internal.rngom.parse.host.Base;
import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.UserInfo;
import com.wiatec.control_panel.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 26/04/2017.
 */
@Controller
public class ActionUser extends BaseAction {

    private UserInfo userInfo;
    private DeviceInfo deviceInfo;
    private ResultInfo resultInfo;
    private String language;
    private int count;
    @Autowired
    private UserService userService;

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
        resultInfo = userService.checkRepeat(userInfo , count , request);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public void confirmEmail(){
        resultInfo = userService.confirmEmail(userInfo);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public void checkLevel(){
        resultInfo = userService.checkUserLevel(userInfo);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
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
}
