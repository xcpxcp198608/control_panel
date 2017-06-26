package com.wiatec.control_panel.service;

import com.wiatec.control_panel.entities.ResultInfo;
import com.wiatec.control_panel.entities.User1Info;
import com.wiatec.control_panel.listener.SessionListener;
import com.wiatec.control_panel.repository.User1Dao;
import com.wiatec.control_panel.repository.User2Dao;
import com.wiatec.control_panel.utils.EmailMaster;
import com.wiatec.control_panel.utils.RegularUtil;
import com.wiatec.control_panel.utils.TextUtils;
import com.wiatec.control_panel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * user handler
 */
@Service
public class User2Service {

    @Autowired
    private User2Dao user2Dao;

    /**
     * show user information by page
     * @param selection
     * @param condition
     * @param currentPage
     * @param countOfPage
     * @return
     */
    @Transactional(readOnly = true)
    public List<User1Info> searchOfPage(String selection, String condition, int currentPage,
                                        int countOfPage){
        List<User1Info> list = user2Dao.searchOfPage(selection, condition, currentPage, countOfPage);
        Map<String, HttpSession> map = SessionListener.sessionMap;
        for(User1Info user1Info: list){
            if(map.containsKey(user1Info.getUserName())){
                user1Info.setOnline(true);
            }
        }
        return list;
    }

    /**
     * administrator active user email when user can not active account
     * @param user1Info
     * @return
     */
    @Transactional
    public boolean active(User1Info user1Info){
        return user2Dao.updateEmailStatusByUserName(user1Info);
    }

    /**
     * administrator search the user info by selection and condition
     * @param selection search selection mapping the column in user1 table
     *                  (id , userName , email, firstName , lastName , emailStatus , )
     * @param condition
     * @return
     */
    @Transactional
    public List<User1Info> search(String selection, String condition){
        return user2Dao.search(selection , condition);
    }

    /**
     * get all of user information by username
     * @param userName
     * @return
     */
    public User1Info details(String userName){
        return user2Dao.getUserInfoByUserName(userName);
    }

    /**
     * administrator delete user
     * @param user1Info
     * @return
     */
    @Transactional
    public boolean delete(User1Info user1Info){
        return user2Dao.delete(user1Info);
    }

}
