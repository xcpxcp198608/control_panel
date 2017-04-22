package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.Message1Info;
import com.wiatec.control_panel.service.Message1Service;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 22/04/2017.
 */
@Controller
public class ActionMessage1 extends BaseAction {

    private Message1Info message1Info;
    private List<Message1Info> message1InfoList;
    @Autowired
    private Message1Service message1Service;

    public void get(){
        message1InfoList = message1Service.get(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(message1InfoList));
        out.flush();
        out.close();
    }
}
