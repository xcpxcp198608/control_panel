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

    @Transactional (readOnly = true)
    public List<ImageInfo> getAll(String countryCode , String timeZone){
        return rollOverImageDao.getAll(countryCode , timeZone);
    }

    @Transactional(readOnly = true)
    public ImageInfo getImageById(int id , String countryCode , String timeZone){
        return rollOverImageDao.getImageById(id , countryCode , timeZone);
    }

    @Transactional
    public void insert(ImageInfo imageInfo , String countryCode , String timeZone){
        rollOverImageDao.insert(imageInfo , countryCode , timeZone);
    }

    @Transactional
    public void update(ImageInfo imageInfo , String countryCode , String timeZone){
        rollOverImageDao.update(imageInfo , countryCode , timeZone);
    }

    @Transactional
    public void delete(int id , String countryCode , String timeZone){
        rollOverImageDao.delete(id, countryCode , timeZone);
    }
}
