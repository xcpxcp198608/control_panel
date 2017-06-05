package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class DeviceInfo {
    private int id;
    private String mac;
    private String ethernetMac;
    private String userName;
    private String country;
    private String countryCode;
    private String regionName;
    private String city;
    private String timeZone;
    private String currentLoginTime;
    private String activeTime;
    private String activeUserName;
    private long registerTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getEthernetMac() {
        return ethernetMac;
    }

    public void setEthernetMac(String ethernetMac) {
        this.ethernetMac = ethernetMac;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(String currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveUserName() {
        return activeUserName;
    }

    public void setActiveUserName(String activeUserName) {
        this.activeUserName = activeUserName;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id=" + id +
                ", mac='" + mac + '\'' +
                ", ethernetMac='" + ethernetMac + '\'' +
                ", userName='" + userName + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", city='" + city + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", currentLoginTime='" + currentLoginTime + '\'' +
                ", activeTime='" + activeTime + '\'' +
                ", activeUserName='" + activeUserName + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
