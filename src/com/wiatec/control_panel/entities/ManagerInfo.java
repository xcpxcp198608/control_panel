package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class ManagerInfo {

    private int id;
    private String userName;
    private String password;
    private String countryCode;
    private String timeZone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "ManagerInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
