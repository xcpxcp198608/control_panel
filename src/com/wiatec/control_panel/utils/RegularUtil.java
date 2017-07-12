package com.wiatec.control_panel.utils;

import java.util.regex.Pattern;

/**
 * regular util
 */
public class RegularUtil {

    /**
     * validate email input format
     */
    public static boolean validateEmail(String email){
        String regular = "(\\w)+@(\\w)*(\\.(\\w)+)+$";
        Pattern pattern = Pattern.compile(regular);
        return pattern.matcher(email).matches();
    }
}
