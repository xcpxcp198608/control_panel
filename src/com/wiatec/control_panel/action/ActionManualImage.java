package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.ManualImageDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by xuchengpeng on 08/05/2017.
 */
@Controller
public class ActionManualImage extends BaseAction {

    @Autowired
    private ManualImageDao manualImageDao;
    private List<ImageInfo> imageInfoList;
    private String product;
    private String language;

    public void get(){
        imageInfoList = manualImageDao.get(product , language);
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
