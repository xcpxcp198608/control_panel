package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.DeviceInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        sql = "insert into device (mac , ethernetMac, username , country , country_code , region_name , city , time_zone , " +
                "current_login_time ,active_username ,register_time) values (:mac , :ethernetMac , :userName , :country ,:countryCode , :regionName , :city," +
                ":timeZone ,:currentLoginTime ,:activeUserName, :registerTime)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

    @Transactional (readOnly = true)
    public boolean isMacExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where mac = ? ";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getMac());
        return count >= 1;
    }

    @Transactional (readOnly = true)
    public boolean isEthernetMacExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where ethernetMac = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getEthernetMac());
        return count >= 1;
    }

    @Transactional (readOnly = true)
    public boolean isMacAndActiveUserExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where mac = ? and active_username = ? ";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getMac(), deviceInfo.getActiveUserName());
        return count >= 1;
    }

    @Transactional (readOnly = true)
    public boolean isEthernetMacAndActiveUserExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where ethernetMac = ? and active_username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getEthernetMac(), deviceInfo.getActiveUserName());
        return count >= 1;
    }

    @Transactional (readOnly = true)
    public boolean isUserNameExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getUserName());
        return count >=1;
    }

    @Transactional (readOnly = true)
    public boolean isUserNameExists(String userName){
        sql = "select count(*) from device where username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , userName);
        return count >=1;
    }

    @Transactional (readOnly = true)
    public boolean isActiveUserNameExists(DeviceInfo deviceInfo){
        sql = "select count(*) from device where active_username = ?";
        int count = jdbcTemplate.queryForObject(sql , Integer.class , deviceInfo.getUserName());
        return count >=1;
    }

    @Transactional
    public boolean updateMac (DeviceInfo deviceInfo){
        sql = "update device set mac=:mac where active_username=:userName";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) >= 1;
    }

    @Transactional
    public boolean cleanUserName(DeviceInfo deviceInfo){
        sql = "update device set username=' ' where username = ?";
        return jdbcTemplate.update(sql , deviceInfo.getUserName()) >=1;
    }

    @Transactional
    public boolean updateByMac (DeviceInfo deviceInfo){
        sql = "update device set ethernetMac=:ethernetMac , username=:userName , country=:country , country_code=:countryCode, " +
                "region_name=:regionName ,city=:city ,time_zone=:timeZone ," +
                "current_login_time=:currentLoginTime where mac=:mac";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) >= 1;
    }

    @Transactional
    public boolean updateByEthernetMac (DeviceInfo deviceInfo){
        sql = "update device set mac=:mac username=:userName , country=:country , country_code=:countryCode, " +
                "region_name=:regionName ,city=:city ,time_zone=:timeZone ," +
                "current_login_time=:currentLoginTime where ethernetMac=:ethernetMac";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(deviceInfo);
        return namedParameterJdbcTemplate.update(sql ,sqlParameterSource) >= 1;
    }

    @Transactional (readOnly = true)
    public String getRegisterTime(DeviceInfo deviceInfo){
        if(deviceInfo.getEthernetMac() != null && !"".equals(deviceInfo.getEthernetMac())){
            if(isEthernetMacAndActiveUserExists(deviceInfo)){
                sql = "select register_time from device where ethernetMac = ? and active_username = ?";
                return jdbcTemplate.queryForObject(sql, String.class, deviceInfo.getEthernetMac(), deviceInfo.getActiveUserName());
            }else{
                return null;
            }
        }else if (deviceInfo.getMac() != null && !"".equals(deviceInfo.getMac())){
            if(isMacAndActiveUserExists(deviceInfo)){
                sql = "select register_time from device where mac = ? and active_username = ?";
                return jdbcTemplate.queryForObject(sql, String.class, deviceInfo.getMac(), deviceInfo.getActiveUserName());
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Transactional (readOnly = true)
    public DeviceInfo getInfoByUserName(String userName){
        if(userName != null && !"".equals(userName)){
            if(!isUserNameExists(userName)){
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setUserName(userName);
                return deviceInfo;
            }else {
                try {
                    sql = "select * from device where username = ?";
                    return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                        DeviceInfo deviceInfo = new DeviceInfo();
                        deviceInfo.setId(i+1);
                        deviceInfo.setMac(resultSet.getString("mac"));
                        deviceInfo.setEthernetMac(resultSet.getString("ethernetMac"));
                        deviceInfo.setUserName(resultSet.getString("username"));
                        deviceInfo.setCountry(resultSet.getString("country"));
                        deviceInfo.setCountryCode(resultSet.getString("country_code"));
                        deviceInfo.setRegionName(resultSet.getString("region_name"));
                        deviceInfo.setCity(resultSet.getString("city"));
                        deviceInfo.setTimeZone(resultSet.getString("time_zone"));
                        deviceInfo.setCurrentLoginTime(resultSet.getString("current_login_time"));
                        deviceInfo.setActiveTime(resultSet.getString("active_time"));
                        deviceInfo.setActiveUserName(resultSet.getString("active_username"));
                        deviceInfo.setRegisterTime(resultSet.getLong("register_time"));
                        return deviceInfo;
                    }, userName);
                }catch (Exception e){
                    return null;
                }
            }
        }else{
            return null;
        }
    }


    public void delete (DeviceInfo deviceInfo){

    }

}
