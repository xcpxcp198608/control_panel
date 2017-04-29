package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.AdImageDao;
import com.wiatec.control_panel.utils.FileUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionAdImage  extends BaseAction {

    private ImageInfo imageInfo;
    private int id;
    private File file;
    private String fileFileName;
    private String fileContentType;
    private String uploadStatus;
    @Autowired
    private AdImageDao adImageDao;

    public void get(){
        imageInfo = adImageDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONObject.fromObject(imageInfo));
        out.flush();
        out.close();
    }

    public String show(){
        checkSession();
        imageInfo = adImageDao.getAll(countryCode , timeZone);
        return "show";
    }

    public String edit(){
        checkSession();
        if(id >0){
            imageInfo = adImageDao.getImageById(id , countryCode , timeZone);
        }
        return "edit";
    }

    public String upload(){
        checkSession();
        if(FileUtil.upload(file , fileFileName , countryCode , timeZone)){
            if(id==0){
                imageInfo = new ImageInfo();
                imageInfo.setName(fileFileName);
                imageInfo.setUrl(imagePath+fileFileName);
                imageInfo.setLink("http://");
            }else{
                imageInfo = adImageDao.getImageById(id, countryCode ,timeZone);
                imageInfo.setName(fileFileName);
                imageInfo.setUrl(imagePath+fileFileName);
            }
            uploadStatus = "upload success";
        }else{
            uploadStatus = "upload failure";
        }
        return "upload";
    }

    public String update(){
        checkSession();
        if(id==0){
            adImageDao.insert(imageInfo,countryCode , timeZone);
        }else{
            imageInfo.setId(id);
            adImageDao.update(imageInfo,countryCode , timeZone);
        }
        return "update";
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
}
