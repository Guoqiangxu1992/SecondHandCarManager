package com.xu.manager.ClassUtil;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xu.manager.bean.FileInfomation;

/**
 * @author Create By Xuguoqiang
 * @date 2017年5月4日--下午9:42:54--
 *
 */
public class SaveFileUtil {
	private static Log logger = LogFactory.getLog(SaveFileUtil.class);

	public static synchronized FileInfomation uploadFile(HttpServletRequest request) {
		FileInfomation fileInfomation = new FileInfomation();
		try {
			String basePath = "D:\\uploadFile";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("file");
			String originalFileName = "aaaa.jpg";
			System.out.println("originalFileName" + originalFileName);
			for (MultipartFile file1 : fileList) {
				String firstName = "NB-YANGCHUNXUE" +  UUID.randomUUID();
				String lastName = originalFileName.substring(originalFileName.lastIndexOf("."),
						originalFileName.length());
				String fileStoreName = firstName + lastName;
				File targetFile = new File(basePath, fileStoreName);
				String storeFilePath = basePath + "\\" + fileStoreName;
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					file1.transferTo(targetFile);
					fileInfomation.setStatus(1);
					fileInfomation.setUrl(storeFilePath);
					return fileInfomation;
				} catch (Exception e) {
					logger.error("file1.transferTo出错！", e);
				}
			}
		} catch (Exception e) {
			logger.error("uploadFile()方法出错！！！", e);
		}
		return fileInfomation;

	}

}
