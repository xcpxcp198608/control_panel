package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.RollImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
@Service
public class RollImageService {

    @Autowired
    private RollImageDao rollImageDao;

    @Transactional (readOnly = true)
    public List<ImageInfo> get(String countryCode , String timeZone){
        List<ImageInfo> imageInfos = rollImageDao.getCenter();
        imageInfos.addAll(rollImageDao.getAll(countryCode ,timeZone));
        return imageInfos;
    }
}
