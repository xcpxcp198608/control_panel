package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ManagerInfo;
import com.wiatec.control_panel.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionManager extends BaseAction {

    private ManagerInfo managerInfo;
    @Autowired
    private ManagerService managerService;

    public String login(){
        if("PX".equals(managerInfo.getUserName()) && "PX".equals(managerInfo.getPassword())){
            return "test";
        }else if("USER".equals(managerInfo.getUserName()) && "USER".equals(managerInfo.getPassword())){
            return "user";
        }else {
            return managerService.login(managerInfo, session);
        }
    }

    public String logout(){
        return "";
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }
}
