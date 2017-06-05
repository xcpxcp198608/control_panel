package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.ApplicationContextHelper;
import com.wiatec.control_panel.entities.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public abstract  class BaseDao <T> {

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected RowMapper<ImageInfo> imageInfoRowMapper ;
    protected RowMapper<VideoInfo> videoInfoRowMapper ;
    protected RowMapper<UserInfo> userInfoRowMapper ;
    protected RowMapper<MessageInfo> messageInfoRowMapper ;
    protected RowMapper<Message1Info> message1InfoRowMapper;
    protected RowMapper<ManagerInfo> managerInfoRowMapper;
    protected RowMapper<DeviceInfo> deviceInfoRowMapper;
    protected RowMapper<UpdateInfo> updateInfoRowMapper;

    protected String sql = "";

    public BaseDao() {
        jdbcTemplate = (JdbcTemplate) ApplicationContextHelper.getApplicationContext().getBean("jdbcTemplate");
        namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ApplicationContextHelper
                .getApplicationContext().getBean("namedParameterJdbcTemplate");
        imageInfoRowMapper = new BeanPropertyRowMapper<>(ImageInfo.class);
        videoInfoRowMapper = new BeanPropertyRowMapper<>(VideoInfo.class);
        userInfoRowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        messageInfoRowMapper = new BeanPropertyRowMapper<>(MessageInfo.class);
        message1InfoRowMapper = new BeanPropertyRowMapper<>(Message1Info.class);
        managerInfoRowMapper = new BeanPropertyRowMapper<>(ManagerInfo.class);
        deviceInfoRowMapper = new BeanPropertyRowMapper<>(DeviceInfo.class);
        updateInfoRowMapper = new BeanPropertyRowMapper<>(UpdateInfo.class);

    }

    protected String getTableName(String countryCode , String timeZone ){
        String tableName = setTableName();
        if("MX".equals(countryCode)){
            tableName = tableName+"_mexico";
        }else if("CN".equals(countryCode)){
            tableName = tableName+"_china";
        }else if("TW".equals(countryCode)){
            tableName = tableName+"_tw";
        }else if("CZ".equals(countryCode)){
            tableName = tableName+"";
        }else if("RO".equals(countryCode)){
            tableName = tableName+"";
        }else if("SK".equals(countryCode)){
            tableName = tableName+"";
        }else if("JP".equals(countryCode)){
            tableName = tableName+"";
        }else if("US".equals(countryCode)){
            if("America/Los_Angeles".equals(timeZone) ||
                    "America/Boise".equals(timeZone) ||
                    "losangeles".equals(timeZone) ||
                    "America/Phoenix".equals(timeZone)){
                tableName = tableName+"";
            }else if("America/Chicago".equals(timeZone) ||
                    "America/Kentucky/Louisville".equals(timeZone) ||
                    "America/Indianan/Indianapolis".equals(timeZone) ||
                    "chicago".equals(timeZone) ||
                    "America/Detroit".equals(timeZone)){
                tableName = tableName+"_chicago";
            }else if("America/Denver".equals(timeZone)||
                    "denver".equals(timeZone)){
                tableName = tableName+"_denver";
            }else if("Pacific/Honolulu".equals(timeZone)||
                    "honolulu".equals(timeZone)){
                tableName = tableName+"_honolulu";
            }else if("America/New_York".equals(timeZone)||
                    "newyork".equals(timeZone)){
                tableName = tableName+"_new_york";
            }else {
                tableName = tableName+"";
            }
        }else {
            tableName = tableName+"";
        }
        return tableName;
    }

    public abstract String setTableName();
    public abstract T getAll (String countryCode , String timeZone);
}
