package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.utils.FileUtil;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class BaseAction implements ServletResponseAware ,ServletRequestAware{

    protected PrintWriter out;
    protected DeviceInfo deviceInfo;
    protected ResultInfo resultInfo;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected String countryCode;
    protected String timeZone;
    protected String imagePath = "http://appota.gobeyondtv.co:8081/launcher/image/" ;
//    protected String imagePath = "http://appotaeu.gobeyondtv.co:8081/launcher/image/" ; //europe server

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("text/html ; charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        response = httpServletResponse;
        try {
            out = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deviceInfo = new DeviceInfo();
        resultInfo = new ResultInfo();
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
        session = httpServletRequest.getSession();
        countryCode = (String) session.getAttribute("countryCode");
        timeZone = (String) session.getAttribute("timeZone");
        imagePath = FileUtil.getImagePath(countryCode , timeZone, imagePath);
    }

    protected boolean checkSession (){
        if(countryCode ==null){
            try {
                response.sendRedirect("control_panel/rollimage/login");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }else{
            return true;
        }
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
