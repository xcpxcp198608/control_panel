package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.utils.TextUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * user1 table operation
 */
@Repository
public class User2Dao extends BaseDao<List<User1Info>> {

    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public List<User1Info> getAll(String countryCode, String timeZone) {
        sql = "select id, userName, email, phone, firstName, lastName, level, emailStatus, mac, ethernetMac, " +
                "country, region, city, timeZone, token, activeDate, activeTime, memberDate,memberTime, lastLoginDate from user1";
        RowMapper<User1Info> rowMapper = new BeanPropertyRowMapper<>(User1Info.class);
        return jdbcTemplate.query(sql , rowMapper);
    }

    @Transactional(readOnly = true)
    public User1Info getUserInfoByUserName(String userName){
        sql = "select id, userName, email, phone, firstName, lastName, level, emailStatus, mac, ethernetMac, country, " +
                "region, city, timeZone, token, activeDate, activeTime, memberDate, memberTime, lastLoginDate from user1 " +
                "where userName = ?";
        RowMapper<User1Info> rowMapper = new BeanPropertyRowMapper<>(User1Info.class);
        return jdbcTemplate.queryForObject(sql , rowMapper , userName);
    }

    @Transactional
    public boolean insert(User1Info user1Info){
        sql = "insert into user1 (userName, password, email, phone, firstName, lastName, level, emailStatus, " +
                "mac, ethernetMac, country, region, city, timeZone, token, activeTime) VALUES (:userName, " +
                ":password, :email, :phone, :firstName, :lastName, :level, :emailStatus, :mac, :ethernetMac," +
                ":country, :region, :city, :timeZone, :token, :activeTime)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional(readOnly = true)
    public boolean validate(User1Info user1Info){
        sql = "select count(*) from user1 where userName = ? and password = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getUserName(), user1Info.getPassword());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean validateEmail(User1Info user1Info){
        sql = "select emailStatus from user1 where userName = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getUserName());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean validateMacAndUserName(User1Info user1Info){
        sql = "select mac from user1 where userName = ?";
        String mac = jdbcTemplate.queryForObject(sql , String.class, user1Info.getUserName());
        return user1Info.getMac().equals(mac);
    }

    @Transactional(readOnly = true)
    public boolean validateEthernetMacAndUserName(User1Info user1Info){
        sql = "select ethernetMac from user1 where userName = ?";
        String ethernetMac = jdbcTemplate.queryForObject(sql , String.class, user1Info.getUserName());
        return user1Info.getEthernetMac().equals(ethernetMac);
    }

    @Transactional(readOnly = true)
    public String getLastName(User1Info user1Info){
        sql = "select lastName from user1 where userName = ?";
        return jdbcTemplate.queryForObject(sql , String.class, user1Info.getUserName());
    }

    @Transactional(readOnly = true)
    public String getToken(User1Info user1Info){
        sql = "select token from user1 where userName = ?";
        return jdbcTemplate.queryForObject(sql , String.class, user1Info.getUserName());
    }

    @Transactional(readOnly = true)
    public Integer getLevel(User1Info user1Info){
        sql = "select level from user1 where userName = ?";
        return jdbcTemplate.queryForObject(sql , Integer.class, user1Info.getUserName());
    }

    @Transactional(readOnly = true)
    public Long getMemberTime(User1Info user1Info){
        sql = "select memberTime from user1 where userName = ?";
        return jdbcTemplate.queryForObject(sql , Long.class, user1Info.getUserName());
    }

    @Transactional(readOnly = true)
    public Long getActiveTime(User1Info user1Info){
        sql = "select activeTime from user1 where userName = ?";
        return jdbcTemplate.queryForObject(sql , Long.class, user1Info.getUserName());
    }

    @Transactional(readOnly = true)
    public String getUserNameByToken(User1Info user1Info){
        sql = "select userName from user1 where token = ?";
        return jdbcTemplate.queryForObject(sql , String.class, user1Info.getToken());
    }

    @Transactional(readOnly = true)
    public boolean isUserNameExists(User1Info user1Info){
        sql = "select count(*) from user1 where userName = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getUserName());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean isEmailExists(User1Info user1Info){
        sql = "select count(*) from user1 where email = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getEmail());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean isMacEmpty(User1Info user1Info){
        sql = "select mac from user1 where userName = ?";
        String mac =  jdbcTemplate.queryForObject(sql , String.class , user1Info.getUserName());
        return TextUtils.isEmpty(mac);
    }

    @Transactional(readOnly = true)
    public boolean isEthernetMacExists(User1Info user1Info){
        sql = "select count(*) from user1 where ethernetMac = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getEthernetMac());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean isMacExists(User1Info user1Info){
        sql = "select count(*) from user1 where mac = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getMac());
        return count == 1;
    }

    @Transactional(readOnly = true)
    public boolean isTokenExists(User1Info user1Info){
        sql = "select count(*) from user1 where token = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , user1Info.getToken());
        return count == 1;
    }

    @Transactional
    public boolean updateToken(User1Info user1Info){
        sql = "update user1 set token=:token where userName=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource) == 1;

    }

    @Transactional
    public boolean updateLocation(User1Info user1Info){
        sql = "update user1 set country=:country, region=:region, city=:city, timeZone=:timeZone, " +
                "lastLoginDate=:lastLoginDate where userName=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updateEmailStatusByToken(User1Info user1Info){
        sql = "update user1 set emailStatus=1 where token=:token";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updateEmailStatusByUserName(User1Info user1Info){
        sql = "update user1 set emailStatus=1 where userName=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updatePasswordByToken(User1Info user1Info){
        sql = "update user1 set password=:password where token=:token";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updateLevel(User1Info user1Info){
        sql = "update user1 set level=:level, memberDate=:memberDate, memberTime=:memberTime " +
                "where userName=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional(readOnly = true)
    public List<User1Info> search(String selection, String condition){
        if(TextUtils.isEmpty(selection) || TextUtils.isEmpty(condition)){
            return null;
        }
        if("userName".equals(selection) || "firstName".equals(selection) ||
                "lastName".equals(selection) || "email".equals(selection)){
            sql = "select id, username, email, phone, firstname, lastname, level, emailStatus, mac," +
                    "ethernetMac, country, region, city, timeZone, token, activeDate, activeTime," +
                    "memberDate, memberTime from user1 where " + selection + " like " + "'%"+condition+"%'";
        }else if("id".equals(selection) || "level".equals(selection) || "emailStatus".equals(selection)){
            sql = "select id, username, email, phone, firstname, lastname, level, emailStatus, mac," +
                    "ethernetMac, country, region, city, timeZone, token, activeDate, activeTime," +
                    "memberDate, memberTime from user1 where " + selection + " = " + condition;
        }else{
            return null;
        }
        RowMapper<User1Info> rowMapper = new BeanPropertyRowMapper<>(User1Info.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Transactional
    public boolean delete(User1Info user1Info){
        sql = "delete from user1 where userName=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user1Info);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    /**
     * 获取当前数据库数据总数
     * @return
     */
    @Transactional(readOnly = true)
    public Integer getTotalCount(){
        sql = "select count(*) from user1 where id > 100";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }


    /**
     * 根据当前需要显示的页数获取数据
     */
    @Transactional(readOnly = true)
    public List<User1Info> getCurrentPageData(int currentPage, int countOfPage){
        int from = (currentPage - 1) * countOfPage + 1;
        sql ="select id, userName, email, phone, firstName, lastName, level, emailStatus," +
                "mac, ethernetMac, country, region, city, timeZone, activeDate, activeTime," +
                "memberDate, memberTime FROM user1 LIMIT ? , ?";
        return jdbcTemplate.query(sql , new BeanPropertyRowMapper<>(User1Info.class), from, countOfPage);
    }

    /**
     * 获取当前数据库数据总数
     * @return
     */
    @Transactional(readOnly = true)
    public Integer getTotalCountByCondition(String selection, String condition){
        if(TextUtils.isEmpty(selection) || TextUtils.isEmpty(condition)){
            sql="select count(*) from user1 where id > 100";
        }else if("userName".equals(selection) || "firstName".equals(selection) ||
                "lastName".equals(selection) || "email".equals(selection)){
            sql = "select count(*) from user1 where id > 100 and " + selection + " like " + "'%"+condition+"%'";
        }else if("id".equals(selection) || "level".equals(selection) || "emailStatus".equals(selection)){
            sql = "select count(*) from user1 where id > 100 and " + selection + " = " + condition;
        }else{
            return 0;
        }
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Transactional(readOnly = true)
    public List<User1Info> searchOfPage(String selection, String condition, int currentPage,
                                        int countOfPage){
        int from = (currentPage -1)*countOfPage;
        if(TextUtils.isEmpty(selection) || TextUtils.isEmpty(condition)){
            sql="select id, username, email, phone, firstname, lastname, level, emailStatus, mac," +
                    "ethernetMac, country, region, city, timeZone, token, activeDate, activeTime," +
                    "memberDate, memberTime from user1 where id > 100 LIMIT "+from +" , "+countOfPage;
        }else if("userName".equals(selection) || "firstName".equals(selection) ||
                "lastName".equals(selection) || "email".equals(selection)){
            sql = "select id, username, email, phone, firstname, lastname, level, emailStatus, mac," +
                    "ethernetMac, country, region, city, timeZone, token, activeDate, activeTime," +
                    "memberDate, memberTime from user1 where id > 100 and " + selection + " like " + "'%"+condition+"%'"+
                    " LIMIT "+from +" , "+countOfPage;
        }else if("id".equals(selection) || "level".equals(selection) || "emailStatus".equals(selection)){
            sql = "select id, username, email, phone, firstname, lastname, level, emailStatus, mac," +
                    "ethernetMac, country, region, city, timeZone, token, activeDate, activeTime," +
                    "memberDate, memberTime from user1 where id > 100 and " + selection + " = " + condition+
                    " LIMIT "+from +" , "+countOfPage;
        }else{
            return null;
        }
        RowMapper<User1Info> rowMapper = new BeanPropertyRowMapper<>(User1Info.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
