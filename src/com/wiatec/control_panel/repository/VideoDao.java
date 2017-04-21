package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.VideoInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class VideoDao extends BaseDao<List<VideoInfo>> {

    @Override
    public String setTableName() {
        return "video";
    }

    @Override
    @Transactional (readOnly = true)
    public List<VideoInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from " + getTableName(countryCode , timeZone);
        return jdbcTemplate.query(sql ,videoInfoRowMapper);
    }

    @Transactional (readOnly = true)
    public List<VideoInfo> getCenter(){
        sql = "select * from video_center";
        return jdbcTemplate.query(sql ,videoInfoRowMapper);
    }
}
