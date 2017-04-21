package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.RollOverImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class RollOverImageService {

    @Autowired
    RollOverImageDao rollOverImageDao;

    @Transactional (readOnly = true)
    public List<ImageInfo> get(String countryCode , String timeZone){
        List<ImageInfo> imageInfoList = rollOverImageDao.getCenter();
        imageInfoList.addAll(rollOverImageDao.getAll(countryCode , timeZone));
        return imageInfoList;
    }
}
