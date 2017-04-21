package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.UserInfo;
import org.springframework.stereotype.Repository;

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
}
