package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.MessageInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

    @Transactional (readOnly = true)
    public MessageInfo getMessageById (int id , String countryCode, String timeZone){
        sql = "select * from " + getTableName(countryCode , timeZone) +" where id =?";
        return jdbcTemplate.queryForObject(sql , messageInfoRowMapper , id);
    }

    @Transactional
    public void insert (MessageInfo messageInfo , String countryCode , String timeZone){
        sql = "insert into "+getTableName(countryCode , timeZone) +" (title,content,icon,link,type) values (" +
                ":title ,:content ,'1' ,:link ,:type)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(messageInfo);
        namedParameterJdbcTemplate.update(sql , sqlParameterSource);
    }

    @Transactional
    public void update (MessageInfo messageInfo , String countryCode , String timeZone){
        sql ="update "+getTableName(countryCode , timeZone)+" set title=:title ,content=:content ,icon='1' ," +
                "link=:link , type=:type where id=:id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(messageInfo);
        namedParameterJdbcTemplate.update(sql , sqlParameterSource);
    }

    @Transactional
    public void delete(int id , String countryCode , String timeZone){
        sql = "delete from "+getTableName(countryCode ,timeZone) +" where id = ?";
        jdbcTemplate.update(sql , id);
    }
}
