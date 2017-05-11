package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.UserInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class UserDao extends BaseDao<List<UserInfo>> {
    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public List<UserInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from user";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Transactional (readOnly = true)
    public UserInfo get(UserInfo userInfo){
        sql = "select * from user where token=?";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbcTemplate.query(sql , rowMapper , userInfo.getToken()).get(0);
    }

    @Transactional (readOnly = true)
    public UserInfo getUserByName(UserInfo userInfo){
        sql = "select * from user where username=?";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbcTemplate.queryForObject(sql , rowMapper , userInfo.getUserName());
    }

    @Transactional (readOnly = true)
    public boolean isUserNameExists(UserInfo userInfo){
        sql = "select count(*) from user where username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , userInfo.getUserName());
        return count >= 1;
    }

    @Transactional (readOnly = true)
    public boolean isEmailExists (UserInfo userInfo){
        sql = "select count(*) from user where email = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , userInfo.getEmail());
        return count >= 1;
    }

    @Transactional
    public boolean insert (UserInfo userInfo){
        sql = "insert into user (username , password , email , token , firstname , lastname ,registertime) values " +
                "(:userName ,:password ,:email ,:token ,:firstName ,:lastName ,:registerTime)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional (readOnly = true)
    public boolean validate (UserInfo userInfo){
        sql = "select count(*) from user where binary username = ? and binary password = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , userInfo.getUserName() , userInfo.getPassword());
        return count ==1 ;
    }

    @Transactional (readOnly = true)
    public boolean validateEmailStatus (UserInfo userInfo){
        sql = "select email_status from user where binary username = ? and binary password = ?";
        short count = jdbcTemplate.queryForObject(sql , Short.class , userInfo.getUserName(),userInfo.getPassword());
        return count == 1;
    }

    @Transactional (readOnly = true)
    public boolean isTokenExists(UserInfo userInfo){
        sql = "select count(*) from user where token = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , userInfo.getToken());
        return count == 1;
    }

    @Transactional
    public boolean updateEmailStatus(UserInfo userInfo){
        sql = "update user set email_status=1 where token=:token";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) == 1;
    }

    @Transactional (readOnly = true)
    public Integer getLevel(UserInfo userInfo){
        sql = "select level from user where binary username=?";
        return jdbcTemplate.queryForObject(sql , Integer.class , userInfo.getUserName());
    }

    @Transactional (readOnly = true)
    public String getLastName(UserInfo userInfo){
        sql = "select lastname from user where binary username=?";
        return jdbcTemplate.queryForObject(sql , String.class , userInfo.getUserName());
    }

    @Transactional (readOnly = true)
    public String getToken(UserInfo userInfo){
        sql = "select token from user where binary username=? and email=?";
        return jdbcTemplate.queryForObject(sql , String.class , userInfo.getUserName() , userInfo.getEmail());
    }

    @Transactional
    public boolean updatePassword (UserInfo userInfo){
        sql = "update user set password=:password where username=:userName and email=:email";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updateToken (UserInfo userInfo){
        sql = "update user set token=:token where username=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional
    public boolean updateMemberTime(UserInfo userInfo){
        sql = "update user set level=:level , membertime=:memberTime , memberdate=:memberDate where username=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }


}
