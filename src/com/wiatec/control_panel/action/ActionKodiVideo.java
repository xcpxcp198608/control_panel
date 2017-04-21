package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.VideoInfo;
import com.wiatec.control_panel.repository.KodiVideoDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionKodiVideo extends BaseAction {

    private VideoInfo videoInfo;
    @Autowired
    private KodiVideoDao kodiVideoDao;

    public void get(){
        videoInfo = kodiVideoDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONObject.fromObject(videoInfo));
        out.flush();
        out.close();
    }

}
