package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.entities.VideoInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by xuchengpeng on 20/04/2017.
 */
@Repository
public class RollImageDao extends BaseDao<List<ImageInfo>> {

    @Override
    public String setTableName() {
        return "roll_image";
    }

    @Override
    @Transactional (readOnly = true)
    public List<ImageInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from "+getTableName(countryCode , timeZone);
        return jdbcTemplate.query(sql ,imageInfoRowMapper);
    }

    @Transactional (readOnly = true)
    public List<ImageInfo> getCenter(){
        sql = "select * from roll_image_center";
        RowMapper<ImageInfo> rowMapper = new BeanPropertyRowMapper<>(ImageInfo.class);
        List<ImageInfo> rollImageInfoList = jdbcTemplate.query(sql ,rowMapper);
        return rollImageInfoList;
    }
}
