package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.DeviceInfo;
import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.service.RollImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
@Controller
public class ActionRollImage extends BaseAction{

    private ImageInfo imageInfo;
    private List<ImageInfo> imageInfoList;

    @Autowired
    private RollImageService rollImageService;

    public void get(){
        imageInfoList = rollImageService.get(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(imageInfoList);
        out.flush();
        out.close();
    }


    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }
}
