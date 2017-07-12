package com.wiatec.control_panel;

import com.wiatec.control_panel.utils.RegularUtil;

/**
 * Created by xuchengpeng on 25/06/2017.
 */
public class Test {

    @org.junit.Test
    public void testRegular(){

        System.out.println(RegularUtil.validateEmail("sf_dsf_ds@gmail.com"));
    }
}
