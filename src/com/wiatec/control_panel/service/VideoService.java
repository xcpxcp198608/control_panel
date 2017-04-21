package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.VideoInfo;
import com.wiatec.control_panel.repository.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class VideoService {

    @Autowired
    private VideoDao videoDao;

    @Transactional (readOnly = true)
    public List<VideoInfo> get(String countryCode , String timeZone) {
        List<VideoInfo> videoInfoList = videoDao.getCenter();
        videoInfoList.addAll(videoDao.getAll(countryCode , timeZone));
        return videoInfoList;
    }
}
