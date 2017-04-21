package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.VideoInfo;
import com.wiatec.control_panel.service.VideoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionVideo extends BaseAction {

    private VideoInfo videoInfo;
    private List<VideoInfo> videoInfoList;

    @Autowired
    private VideoService videoService;

    public void get(){
        videoInfoList = videoService.get(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(videoInfoList));
        out.flush();
        out.close();
    }
}
