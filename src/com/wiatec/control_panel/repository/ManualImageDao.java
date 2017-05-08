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
public class ManualImageDao extends BaseDao<List<ImageInfo>> {
    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public List<ImageInfo> getAll(String countryCode, String timeZone) {
        return null;
    }

    @Transactional (readOnly = true)
    public List<ImageInfo> get(String product , String language){
        String tableName = "manual_image";
        tableName += "_"+product+"_"+language;
        sql = "select * from "+tableName;
        return jdbcTemplate.query(sql , imageInfoRowMapper);
    }
}
