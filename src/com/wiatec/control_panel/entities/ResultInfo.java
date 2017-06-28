package com.wiatec.control_panel.entities;

import org.springframework.stereotype.Component;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Component
public class ResultInfo {

    public static final int CODE_REQUEST_SUCCESS = 11;
    public static final String STATUS_REQUEST_SUCCESS = "request success ";

    public static final int CODE_REQUEST_FAILURE = 12;
    public static final String STATUS_REQUEST_FAILURE = "request failure ";

    public static final int CODE_INPUT_INFO_ERROR = 100;
    public static final String STATUS_INPUT_INFO_ERROR = "input info error ";
    public static final int CODE_USER_NAME_EXISTS = 101;
    public static final String STATUS_USER_NAME_EXISTS = "UserName is exists ";
    public static final int CODE_EMAIL_EXISTS = 102;
    public static final String STATUS_EMAIL_EXISTS = "email is exists ";
    public static final int CODE_REGISTER_SUCCESS = 103;
    public static final String STATUS_REGISTER_SUCCESS = "register success ";
    public static final int CODE_REGISTER_FAILURE = 104;
    public static final String STATUS_REGISTER_FAILURE = "register failure ";

    public static final int CODE_LOGIN_INFO_ERROR = 201;
    public static final String STATUS_LOGIN_INFO_ERROR = "login information error ";
    public static final int CODE_EMAIL_VALIDATE_ERROR = 202;
    public static final String STATUS_EMAIL_VALIDATE_ERROR = "email no validate ";
    public static final int CODE_LOGIN_SUCCESS = 200;
    public static final String STATUS_LOGIN_SUCCESS = "login success ";

    public static final int CODE_LOGIN_ERROR = 301;
    public static final String STATUS_LOGIN_ERROR = "login error ";

    public static final int CODE_EMAIL_CONFIRM_SUCCESS = 401;
    public static final String STATUS_EMAIL_CONFIRM_SUCCESS = "active success ";
    public static final int CODE_EMAIL_CONFIRM_FAILURE = 402;
    public static final String STATUS_EMAIL_CONFIRM_FAILURE = "active failure ";

    private int code;
    private String status;
    private int loginCount;
    private String token;
    private int userLevel;
    private String extra;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", loginCount=" + loginCount +
                ", token='" + token + '\'' +
                ", userLevel=" + userLevel +
                ", extra='" + extra + '\'' +
                ", object=" + object +
                '}';
    }
}
