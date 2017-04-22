package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.MessageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class MessageDao extends BaseDao<List<MessageInfo>> {
    @Override
    public String setTableName() {
        return "message";
    }

    @Override
    @Transactional (readOnly = true)
    public List<MessageInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from "+getTableName(countryCode ,timeZone);
        return jdbcTemplate.query(sql , messageInfoRowMapper);
    }

    @Transactional (readOnly = true)
    public List<MessageInfo> getCenter (){
        sql = "select * from message_center";
        return jdbcTemplate.query(sql , messageInfoRowMapper);
    }
}
