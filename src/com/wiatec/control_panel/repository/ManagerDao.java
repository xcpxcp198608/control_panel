package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ManagerInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class ManagerDao extends BaseDao<ManagerInfo> {
    @Override
    public String setTableName() {
        return null;
    }

    @Override
    public ManagerInfo getAll(String countryCode, String timeZone) {
        return null;
    }

    @Transactional (readOnly = true)
    public boolean check (ManagerInfo managerInfo){
        if(managerInfo == null){
            return false;
        }
        sql = "select count(*) from manager where username=? and password =?";
        int count = jdbcTemplate.queryForObject(sql ,Integer.class , managerInfo.getUserName() , managerInfo.getPassword());
        return count == 1;
    }

    @Transactional
    public String getCountryCode(ManagerInfo managerInfo){
        sql = "select countryCode from manager where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql ,String.class , managerInfo.getUserName() , managerInfo.getPassword());
    }

    @Transactional
    public String getTimeZone(ManagerInfo managerInfo){
        sql = "select timeZone from manager where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql ,String.class , managerInfo.getUserName() , managerInfo.getPassword());
    }
}
