package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.UserDataInfo;
import com.wiatec.control_panel.entities.UserInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class UserDataDao extends BaseDao<UserInfo> {

    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public UserInfo getAll(String countryCode, String timeZone) {
        return null;
    }

    @Transactional
    public boolean insert(UserDataInfo userDataInfo){
        sql = "insert into user_data1 (username, ip , country , city , mac , exitTime , stayTime) values (" +
                ":userName , :ip ,:country , :city ,:mac ,:exitTime , :stayTime)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userDataInfo);
        return namedParameterJdbcTemplate.update(sql , sqlParameterSource) == 1;
    }

}
