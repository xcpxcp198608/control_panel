package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.VideoInfo;
import com.wiatec.control_panel.repository.AdVideoDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionAdVideo extends BaseAction{

    private VideoInfo videoInfo;
    @Autowired
    private AdVideoDao adVideoDao;

    public void get(){
        videoInfo  = adVideoDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONObject.fromObject(videoInfo));
        out.flush();
        out.close();
    }
}
