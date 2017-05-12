package com.wiatec.control_panel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext mApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        mApplicationContext = applicationContext;
        System.setProperty("user.timezone","GMT-8");
    }

    public static ApplicationContext getApplicationContext(){
        return mApplicationContext;
    }
}
