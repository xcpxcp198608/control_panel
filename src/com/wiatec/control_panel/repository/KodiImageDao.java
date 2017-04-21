package com.wiatec.control_panel.repository;

import com.wiatec.control_panel.entities.ImageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Repository
public class KodiImageDao extends BaseDao<List<ImageInfo>> {
    @Override
    public String setTableName() {
        return "kodi_image";
    }

    @Override
    @Transactional (readOnly = true)
    public List<ImageInfo> getAll(String countryCode, String timeZone) {
        sql = "select * from "+getTableName(countryCode , timeZone);
        return jdbcTemplate.query(sql ,imageInfoRowMapper);
    }
}
