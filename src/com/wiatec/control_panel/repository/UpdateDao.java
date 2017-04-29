package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.UpdateInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class UpdateDao extends BaseDao<UpdateInfo>{
    @Override
    public String setTableName() {
        return null;
    }

    @Override
    @Transactional (readOnly = true)
    public UpdateInfo getAll(String countryCode, String timeZone) {
        String sql = "select * from update_app";
        return jdbcTemplate.queryForObject(sql , updateInfoRowMapper);
    }
}
