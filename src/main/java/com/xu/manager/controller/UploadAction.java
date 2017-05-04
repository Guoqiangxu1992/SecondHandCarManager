/**
 * 
 */
package com.xu.manager.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xu.manager.ClassUtil.SaveFileUtil;
import com.xu.manager.bean.FileInfomation;
import com.xu.manager.bean.HttpServletBean;
import com.xu.manager.bean.ScanTaskVo;

/**
 * @author Create By Xuguoqiang
 * @date 2016年10月20日--下午8:13:49--
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/upload")
public class UploadAction {
	private static Log logger = LogFactory.getLog(UploadAction.class);
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);

	@RequestMapping(value = "/uploadfile.do", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// 转型为MultipartHttpRequest
			HttpServletBean httpServletBean = new HttpServletBean();
			httpServletBean.setRequest(request);
			httpServletBean.setResponse(response);
				UploadTask uploadTask = new UploadTask(httpServletBean);
				Future<FileInfomation> result = executor.submit(uploadTask);
				System.out.println("上传成功，结果：url:" + result.get().getUrl());
			
		} catch (Exception e) {
			logger.error("UploadAction出错！", e);
		}
	}
}

class UploadTask implements Callable<FileInfomation> {
	private HttpServletBean httpServletBean;
	private static Log logger = LogFactory.getLog(UploadTask.class);

	public UploadTask(HttpServletBean httpServletBean) {
		this.httpServletBean = httpServletBean;
	}

	private ThreadLocal<HttpServletBean> httpThreadLocal = new ThreadLocal<HttpServletBean>() {
		@Override
		protected HttpServletBean initialValue() {
			return httpServletBean;
		}
	};

	@Override
	public FileInfomation call() throws Exception {
		FileInfomation fileInfomation = null;
		try {
			fileInfomation = SaveFileUtil.uploadFile(httpThreadLocal.get().getRequest());
			if (fileInfomation != null && fileInfomation.getStatus() == 1 && fileInfomation.getUrl() != null) {
				httpThreadLocal.get().getResponse().addHeader("url", fileInfomation.getUrl());
			}
			return fileInfomation;
		} catch (Exception e) {
			logger.error("调用SaveFileUtil.uploadFile()出错！", e);
		}
		return fileInfomation;
	}

}
