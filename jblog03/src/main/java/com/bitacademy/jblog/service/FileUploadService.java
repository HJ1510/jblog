package com.bitacademy.jblog.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static String RESTORE_PATH = "";
	private static String URL_BASE = "";
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		if (multipartFile.isEmpty()) {
			return url;
		}
		
		String originalFilename =  multipartFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
		String restoreFilename = generatrSaveFilename(extName);
		
		System.out.println("url:"+url);
		return url;
	}

	private String generatrSaveFilename(String extName) {
		String filename="";
		
		Calendar calender = Calendar.getInstance();
		filename += calender.get(Calendar.YEAR);
		filename += calender.get(Calendar.MONTH);
		filename += calender.get(Calendar.DATE);
		filename += calender.get(Calendar.HOUR);
		filename += calender.get(Calendar.MINUTE);
		filename += calender.get(Calendar.SECOND);
		filename += calender.get(Calendar.MILLISECOND);
		filename += ("."+extName);
		
		return filename;
	}
	

}
