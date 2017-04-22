package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.DeviceInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    public void insert (DeviceInfo deviceInfo){
        sql = "insert into device (mac , username , country , country_code , region_name , city , time_zone , " +
                "current_login_time) values (:mac , :userName , :country ,:countryCode , :regionName , :city," +
                ":timeZone ,:currentLoginTime)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        namedParameterJdbcTemplate.update(sql , sqlParameterSource);
    }

}
