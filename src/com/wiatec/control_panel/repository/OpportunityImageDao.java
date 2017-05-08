package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ImageInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 08/05/2017.
 */
@Repository
public class OpportunityImageDao extends BaseDao<List<ImageInfo>> {
    @Override
    public String setTableName() {
        return "opportunity_image";
    }

    @Override
    @Transactional
    public List<ImageInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from "+getTableName(countryCode ,timeZone);
        return jdbcTemplate.query(sql , imageInfoRowMapper);
    }
}
