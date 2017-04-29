package com.wiatec.control_panel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class FileUtil {
	
	public static boolean upload(File file ,String fileFileName ,String countryCode , String timeZone){
		boolean flag = false;
		if(file == null || fileFileName == null){
			return flag;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String targetDir = servletContext.getRealPath(getImagePath(countryCode , timeZone , "/image/"));
		File file1 = new File(targetDir);
		if(!file1.exists()){
			file1.mkdir();
		}
		String dir = servletContext.getRealPath(getImagePath(countryCode , timeZone , "/image/")+fileFileName);
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		try {
			fileOutputStream = new FileOutputStream(dir);
			fileInputStream = new FileInputStream(file);
			byte [] buffer = new byte[1024];
			int length = 0 ;
			while((length = fileInputStream.read(buffer)) != -1){
				fileOutputStream.write(buffer , 0 , length);
			}
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			flag = false;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			try {
				if(fileOutputStream != null){
					fileOutputStream.close();
				}
				if(fileInputStream != null){
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static String getImagePath(String countryCode ,String timeZone, String imagePath){
		if("CN".equals(countryCode)){
			imagePath += "china/";
		}else if("MX".equals(countryCode)){
			imagePath += "mexico/";
		}else if("TW".equals(countryCode)){
			imagePath += "tw/";
		}else if("CZ".equals(countryCode)){
			imagePath += "czechia/";
		}else if("RO".equals(countryCode)){
			imagePath += "romania/";
		}else if("SK".equals(countryCode)){
			imagePath += "slovak/";
		}else if("US".equals(countryCode)){
			if("losangeles".equals(timeZone)){
				imagePath += "losangeles/";
			}else if("chicago".equals(timeZone)){
				imagePath += "chicago/";
			}else if("denver".equals(timeZone)){
				imagePath += "denver/";
			}else if("newyork".equals(timeZone)){
				imagePath += "newyork/";
			}else if("honolulu".equals(timeZone)){
				imagePath += "honolulu/";
			}else{
				imagePath += "losangeles/";
			}
		}else{
			imagePath += "losangeles/";
		}
		return imagePath;
	}

}
