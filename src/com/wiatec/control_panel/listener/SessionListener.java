package com.wiatec.control_panel.listener;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
public class SessionListener implements HttpSessionListener ,HttpSessionAttributeListener{

    public static Map<String ,HttpSession> sessionMap = new HashMap<>();
    public static final String KEY = "userName";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSession().getId()+" created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSession().getId()+" destroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public static HttpSession getSession (String userName){
        return sessionMap.get(userName);
    }
}
