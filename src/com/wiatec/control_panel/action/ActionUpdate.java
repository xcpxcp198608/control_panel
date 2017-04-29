package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.UpdateInfo;
import com.wiatec.control_panel.repository.UpdateDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 24/04/2017.
 */
@Controller
public class ActionUpdate extends BaseAction {

    private UpdateInfo updateInfo;
    @Autowired
    private UpdateDao updateDao;

    public void get(){
        updateInfo = updateDao.getAll("","");
        out.println(JSONObject.fromObject(updateInfo));
        out.flush();
        out.close();
    }

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
    }
}
