/**
 * 
 */
package com.xu.manager.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.xu.manager.bean.UploadParam;

/**
* @author Create By Xuguoqiang
* @date   2016年10月20日--下午8:16:00--
*
*/

public interface UploadManager {
	public  UploadParam uploadFile(HttpSession session, File f,
			String filename, UploadParam param, Long carId);

}
