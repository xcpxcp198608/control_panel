package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class BaseAction implements ServletResponseAware {

    protected PrintWriter out;
    protected DeviceInfo deviceInfo;
    protected ResultInfo resultInfo;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("text/html ; charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        try {
            out = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deviceInfo = new DeviceInfo();
        resultInfo = new ResultInfo();
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
}
