package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.Message1Info;
import com.wiatec.control_panel.repository.Message1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
