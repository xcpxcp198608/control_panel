package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.DeviceInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class DeviceDao extends BaseDao<DeviceInfo> {
    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public DeviceInfo getAll(String countryCode, String timeZone) {
        return null;
    }

    @Transactional
    public boolean insert (DeviceInfo deviceInfo){
        sql = "insert into device (mac , username , country , country_code , region_name , city , time_zone , " +
                "current_login_time ,active_username) values (:mac , :userName , :country ,:countryCode , :regionName , :city," +
                ":timeZone ,:currentLoginTime ,:activeUserName)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional (readOnly = true)
    public boolean isMacExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where mac = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getMac());
        return count >=1;
    }

    @Transactional (readOnly = true)
    public boolean isUserNameExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getUserName());
        return count >=1;
    }

    @Transactional
    public boolean cleanUserName(DeviceInfo deviceInfo){
        sql = "update device set username=' ' where username = ?";
        return jdbcTemplate.update(sql , deviceInfo.getUserName()) >=1;
    }

    @Transactional
    public boolean update (DeviceInfo deviceInfo){
        sql = "update device set username=:userName , country=:country , country_code=:countryCode, " +
                "region_name=:regionName ,city=:city ,time_zone=:timeZone ," +
                "current_login_time=:currentLoginTime where mac=:mac";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) >= 1;
    }

    public void delete (DeviceInfo deviceInfo){

    }

}
