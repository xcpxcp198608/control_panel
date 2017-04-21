package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ManagerInfo;
import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private ResultInfo resultInfo;

    @Transactional
    public ResultInfo login(ManagerInfo managerInfo){
        if(managerDao.check(managerInfo)){
            String countryCode = managerDao.getCountryCode(managerInfo);
            HttpSession session = SessionListener.getSession(managerInfo.getUserName());
            if(session == null){

            }
            System.out.println(countryCode);
            resultInfo.setCode(1);
            resultInfo.setSatus("success");
        }else{
            resultInfo.setCode(1);
            resultInfo.setSatus("failure");
        }
        return resultInfo;
    }
}
