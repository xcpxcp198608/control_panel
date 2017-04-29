package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.Message1Info;
import com.wiatec.control_panel.entities.MessageInfo;
import com.wiatec.control_panel.repository.Message1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class Message1Service {

    @Autowired
    private Message1Dao message1Dao;

    public List<Message1Info> get(String countryCode , String timeZone){
        List<Message1Info> message1InfoList = message1Dao.getCenter();
        message1InfoList.addAll(message1Dao.getAll(countryCode , timeZone));
        return message1InfoList;
    }

    @Transactional(readOnly = true)
    public List<Message1Info> getAll(String countryCode , String timeZone){
        return message1Dao.getAll(countryCode , timeZone);
    }

    @Transactional(readOnly = true)
    public Message1Info getMessageById(int id , String countryCode , String timeZone){
        return message1Dao.getMessageById(id, countryCode ,timeZone);
    }

    @Transactional
    public void insert (Message1Info message1Info , String countryCode , String timeZone){
        message1Dao.insert(message1Info , countryCode , timeZone);
    }

    @Transactional
    public void update(Message1Info message1Info , String countryCode , String timeZone){
        message1Dao.update(message1Info , countryCode , timeZone);
    }

    @Transactional
    public void delete (int id ,String countryCode , String timeZone){
        message1Dao.delete(id, countryCode , timeZone);
    }
}
