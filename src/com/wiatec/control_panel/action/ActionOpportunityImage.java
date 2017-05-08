package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.OpportunityImageDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 08/05/2017.
 */
@Controller
public class ActionOpportunityImage extends BaseAction {

    @Autowired
    private OpportunityImageDao opportunityImageDao;
    private List<ImageInfo> imageInfoList;

    public void get(){
        imageInfoList = opportunityImageDao.getAll(deviceInfo.getCountryCode() ,deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(imageInfoList));
        out.flush();
        out.close();
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }
}
