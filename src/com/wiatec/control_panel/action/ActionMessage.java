package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.MessageInfo;
import com.wiatec.control_panel.service.MessageService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 22/04/2017.
 */
@Controller
public class ActionMessage extends BaseAction {

    private List<MessageInfo> messageInfoList;
    private MessageInfo messageInfo;
    private int id;
    @Autowired
    private MessageService messageService;

    public void get(){
        messageInfoList = messageService.get(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(messageInfoList));
        out.flush();
        out.close();
    }

    public String show (){
        checkSession();
        messageInfoList = messageService.getAll(countryCode , timeZone);
        return "show";
    }

    public String edit(){
        checkSession();
        if(id >0){
            messageInfo =  messageService.getMessageById(id ,countryCode , timeZone);
        }
        return "edit";
    }

    public String update(){
        checkSession();
        if(id == 0 ){
            messageService.insert(messageInfo , countryCode , timeZone);
        }else{
            messageInfo.setId(id);
            messageService.update(messageInfo , countryCode , timeZone);
        }
        return "update";
    }

    public String delete (){
        checkSession();
        if(id >0){
            messageService.delete(id , countryCode , timeZone);
        }
        return "delete";
    }

    public List<MessageInfo> getMessageInfoList() {
        return messageInfoList;
    }

    public void setMessageInfoList(List<MessageInfo> messageInfoList) {
        this.messageInfoList = messageInfoList;
    }

    public MessageInfo getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
