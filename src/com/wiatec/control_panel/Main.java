package com.wiatec.control_panel;

import com.wiatec.control_panel.utils.HttpUrlUtil;

/**
 * Created by xuchengpeng on 11/05/2017.
 */
public class Main {

    public static void main (String [] args){
        HttpUrlUtil httpUrlUtil = new HttpUrlUtil();
        httpUrlUtil.execute("http://www.baidu.com", new HttpUrlUtil.OnHttpListener() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(String e) {
                System.out.println(e);
            }
        });
    }


}
