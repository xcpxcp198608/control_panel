package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.UserDataInfo;
import com.wiatec.control_panel.repository.UserDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 26/04/2017.
 */
@Controller
public class ActionUserData extends BaseAction{

    private UserDataInfo userDataInfo;
    @Autowired
    private UserDataDao userDataDao;

    public void save(){
        if(userDataInfo != null){
            userDataDao.insert(userDataInfo);
        }
    }

    public UserDataInfo getUserDataInfo() {
        return userDataInfo;
    }

    public void setUserDataInfo(UserDataInfo userDataInfo) {
        this.userDataInfo = userDataInfo;
    }
}
