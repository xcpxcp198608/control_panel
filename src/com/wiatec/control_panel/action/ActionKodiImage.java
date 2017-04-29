package com.wiatec.control_panel.action;

import com.wiatec.control_panel.entities.ImageInfo;
import com.wiatec.control_panel.repository.KodiImageDao;
import com.wiatec.control_panel.utils.FileUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.List;

/**
 * Created by xuchengpeng on 21/04/2017.
 */
@Controller
public class ActionKodiImage extends BaseAction {

    private ImageInfo imageInfo;
    private List<ImageInfo>  imageInfoList;
    private int id;
    private File file;
    private String fileFileName;
    private String fileContentType;
    private String uploadStatus;
    @Autowired
    private KodiImageDao kodiImageDao;

    public void get(){
        imageInfoList = kodiImageDao.getAll(deviceInfo.getCountryCode() , deviceInfo.getTimeZone());
        out.println(JSONArray.fromObject(imageInfoList));
        out.flush();
        out.close();
    }

    public String show(){
        checkSession();
        imageInfoList = kodiImageDao.getAll(countryCode , timeZone);
        return "show";
    }

    public String edit(){
        checkSession();
        if(id >0){
            imageInfo = kodiImageDao.getImageById(id , countryCode , timeZone);
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
                imageInfo = kodiImageDao.getImageById(id, countryCode ,timeZone);
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
            kodiImageDao.insert(imageInfo,countryCode , timeZone);
        }else{
            imageInfo.setId(id);
            kodiImageDao.update(imageInfo,countryCode , timeZone);
        }
        return "update";
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
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
