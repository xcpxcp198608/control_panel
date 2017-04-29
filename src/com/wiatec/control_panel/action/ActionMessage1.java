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
    private int id;
    @Autowired
    private Message1Service message1Service;

    public void get(){
        message1InfoList = message1Service.get(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(message1InfoList));
        out.flush();
        out.close();
    }

    public String show (){
        checkSession();
        message1InfoList = message1Service.getAll(countryCode , timeZone);
        return "show";
    }

    public String edit(){
        checkSession();
        if(id >0){
            message1Info =  message1Service.getMessageById(id ,countryCode , timeZone);
        }
        return "edit";
    }

    public String update(){
        checkSession();
        if(id == 0 ){
            message1Service.insert(message1Info , countryCode , timeZone);
        }else{
            message1Info.setId(id);
            message1Service.update(message1Info , countryCode , timeZone);
        }
        return "update";
    }

    public String delete (){
        checkSession();
        if(id >0){
            message1Service.delete(id , countryCode , timeZone);
        }
        return "delete";
    }

    public Message1Info getMessage1Info() {
        return message1Info;
    }

    public void setMessage1Info(Message1Info message1Info) {
        this.message1Info = message1Info;
    }

    public List<Message1Info> getMessage1InfoList() {
        return message1InfoList;
    }

    public void setMessage1InfoList(List<Message1Info> message1InfoList) {
        this.message1InfoList = message1InfoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
