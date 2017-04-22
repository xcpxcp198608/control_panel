package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.Message1Info;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class Message1Dao extends BaseDao<List<Message1Info>> {
    @Override
    public String setTableName() {
        return "message1";
    }

    @Override
    @Transactional (readOnly = true)
    public List<Message1Info> getAll(String countryCode, String timeZone) {
        sql = "select * from " + getTableName(countryCode , timeZone);
        return jdbcTemplate.query(sql , message1InfoRowMapper);
    }

    @Transactional (readOnly = true)
    public List<Message1Info> getCenter (){
        sql = "select * from message1_center";
        return jdbcTemplate.query(sql , message1InfoRowMapper);
    }
}
