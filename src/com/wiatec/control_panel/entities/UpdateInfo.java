package com.wiatec.control_panel.entities;

/**
 * Created by xuchengpeng on 20/04/2017.
 */
public class UpdateInfo {

    private int id;
    private String name;
    private String fileName;
    private String packageName;
    private String url;
    private String version;
    private int code;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}
