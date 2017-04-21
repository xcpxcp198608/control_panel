package com.wiatec.control_panel.entities;

import org.springframework.stereotype.Component;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Component
public class ResultInfo {

    private int code;
    private String satus;
    private int count;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", satus='" + satus + '\'' +
                ", count=" + count +
                ", token='" + token + '\'' +
                '}';
    }
}
