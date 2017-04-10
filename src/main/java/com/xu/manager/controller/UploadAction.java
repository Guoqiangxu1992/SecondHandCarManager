/**
 * 
 */
package com.xu.manager.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author Create By Xuguoqiang
 * @date 2016年10月20日--下午8:13:49--
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/upload")
public class UploadAction {
	@RequestMapping(value = "/uploadfile.do", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 // 转型为MultipartHttpRequest  
        try {  
        	String path = "D:\\uploadFile";
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
            List<MultipartFile> fileList = multipartRequest.getFiles("file");
            String originalFileName = request.getHeader("fileName");
            System.out.println("filNmae111111"+originalFileName);
            for(MultipartFile file1:fileList){
            	String firstName = "NB"+System.currentTimeMillis();
            	String lastName = originalFileName.substring(originalFileName.lastIndexOf("."),originalFileName.length());
            	String fileStoreName = firstName+lastName;
            	File targetFile = new File(path, fileStoreName);
            	String storeFilePath = path+"\\"+fileStoreName;
            	response.addHeader("url",storeFilePath );
            	
            	if (!targetFile.exists()) {
        			targetFile.mkdirs();
        		}
        	// 保存
        	try {
        		file1.transferTo(targetFile);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		}
	}

