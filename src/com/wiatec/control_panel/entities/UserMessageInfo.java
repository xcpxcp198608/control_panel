package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class UserMessageInfo {
    private int id;
    private String userName;
    private String img;
    private String message;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserMessageInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", img='" + img + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
