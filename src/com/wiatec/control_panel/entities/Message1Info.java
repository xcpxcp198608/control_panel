package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class Message1Info {
    private int id;
    private String content;
    private int colorR;
    private int colorG;
    private int colorB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    @Override
    public String toString() {
        return "Message1Info{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", colorR=" + colorR +
                ", colorG=" + colorG +
                ", colorB=" + colorB +
                '}';
    }
}
