package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class UserInfo {

    private int id;
    private String userName;
    private String password;
    private String email;
    private String token;
    private short level;
    private short emailStatus;
    private String registerDate;
    private long registerTime;
    private String firstName;
    private String lastName;
    private String memberDate;
    private long memberTime;
    private String status;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public short getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(short emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    public long getMemberTime() {
        return memberTime;
    }

    public void setMemberTime(long memberTime) {
        this.memberTime = memberTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", level=" + level +
                ", emailStatus=" + emailStatus +
                ", registerDate='" + registerDate + '\'' +
                ", registerTime=" + registerTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", memberDate='" + memberDate + '\'' +
                ", memberTime=" + memberTime +
                ", status='" + status + '\'' +
                '}';
    }
}
