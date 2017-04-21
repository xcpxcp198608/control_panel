package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.KodiImageDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionKodiImage extends BaseAction {

    private ImageInfo imageInfo;
    private List<ImageInfo>  imageInfoList;
    @Autowired
    private KodiImageDao kodiImageDao;

    public void get(){
        imageInfoList = kodiImageDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(imageInfoList));
        out.flush();
        out.close();
    }

}
