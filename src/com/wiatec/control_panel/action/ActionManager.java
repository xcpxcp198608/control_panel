package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ManagerInfo;
import com.wiatec.control_panel.service.ManagerService;
import net.sf.json.JSONObject;
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

    public void login(){
        resultInfo = managerService.login(managerInfo);
        out.println(JSONObject.fromObject(resultInfo));
        out.flush();
        out.close();
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }
}
