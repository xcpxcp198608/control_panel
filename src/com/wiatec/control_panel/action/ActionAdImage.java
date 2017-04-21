package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.AdImageDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionAdImage  extends BaseAction {

    private ImageInfo imageInfo;
    @Autowired
    private AdImageDao adImageDao;

    public void get(){
        imageInfo = adImageDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONObject.fromObject(imageInfo));
        out.flush();
        out.close();
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }
}
