package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.entities.VideoInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class AdVideoDao extends BaseDao<VideoInfo> {

    @Override
    public String setTableName() {
        return "ad_video";
    }

    @Override
    @Transactional (readOnly = true)
    public VideoInfo getAll(String countryCode, String timeZone) {
        sql = "select * from " + getTableName(countryCode , timeZone);
        return jdbcTemplate.queryForObject(sql , videoInfoRowMapper);
    }


}
