package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.Message1Info;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

    @Transactional (readOnly = true)
    public Message1Info getMessageById(int id , String countryCode , String timeZone){
        sql ="select * from "+getTableName(countryCode ,timeZone) +" where id = ?";
        return jdbcTemplate.queryForObject(sql, message1InfoRowMapper , id);
    }

    @Transactional
    public void insert (Message1Info message1Info , String countryCode , String timeZone){
        sql = "insert into "+getTableName(countryCode , timeZone) +" (content , colorR ,colorG , colorB) values (" +
                ":content ,:colorR ,:colorG ,:colorB)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(message1Info);
        namedParameterJdbcTemplate.update(sql , sqlParameterSource);
    }

    @Transactional
    public void update (Message1Info message1Info , String countryCode , String timeZone){
        sql = "update "+getTableName(countryCode , timeZone)+" set content=:content , colorR=:colorR , colorG=:colorG ," +
                "colorB=:colorB where id=:id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(message1Info);
        namedParameterJdbcTemplate.update(sql , sqlParameterSource);
    }

    @Transactional
    public void delete (int id , String countryCode , String timeZone){
        sql = "delete from "+ getTableName(countryCode , timeZone) +" where id=?";
        jdbcTemplate.update(sql , id);
    }
}
