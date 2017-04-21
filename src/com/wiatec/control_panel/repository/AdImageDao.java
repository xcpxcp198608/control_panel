package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ImageInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class AdImageDao extends BaseDao<ImageInfo> {

    @Override
    public String setTableName() {
        return "ad_image";
    }

    @Override
    @Transactional (readOnly = true)
    public ImageInfo getAll(String countryCode, String timeZone) {
        sql = "select * from "+getTableName(countryCode , timeZone);
        return jdbcTemplate.queryForObject(sql , imageInfoRowMapper);
    }
}
