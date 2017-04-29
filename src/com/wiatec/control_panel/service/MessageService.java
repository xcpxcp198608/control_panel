package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.MessageInfo;
import com.wiatec.control_panel.repository.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    @Transactional (readOnly = true)
    public List<MessageInfo> get(String countryCode , String timeZone){
        List<MessageInfo> messageInfoList = messageDao.getCenter();
        messageInfoList.addAll(messageDao.getAll(countryCode , timeZone));
        return messageInfoList;
    }

    @Transactional (readOnly = true)
    public List<MessageInfo> getAll(String countryCode , String timeZone){
        return messageDao.getAll(countryCode , timeZone);
    }

    @Transactional(readOnly = true)
    public MessageInfo getMessageById(int id , String countryCode , String timeZone){
        return messageDao.getMessageById(id, countryCode ,timeZone);
    }

    @Transactional
    public void insert (MessageInfo messageInfo , String countryCode , String timeZone){
        messageDao.insert(messageInfo , countryCode , timeZone);
    }

    @Transactional
    public void update(MessageInfo messageInfo , String countryCode , String timeZone){
        messageDao.update(messageInfo , countryCode , timeZone);
    }

    @Transactional
    public void delete (int id ,String countryCode , String timeZone){
        messageDao.delete(id, countryCode , timeZone);
    }
}
