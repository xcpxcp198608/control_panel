package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ManagerInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.ManagerDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Transactional
    public String login(ManagerInfo managerInfo ,HttpSession session) {
        if (managerDao.check(managerInfo)) {
            String countryCode = managerDao.getCountryCode(managerInfo);
            String timeZone = managerDao.getTimeZone(managerInfo);
            if (countryCode != null && timeZone != null) {
                session.setAttribute("countryCode", countryCode);
                session.setAttribute("timeZone", timeZone);
                return "success";
            }else{
                return "failure";
            }

        } else {
            return "failure";
        }
    }
}
