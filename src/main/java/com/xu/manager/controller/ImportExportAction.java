/**
 * 
 */
package com.xu.manager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Create By Xuguoqiang
* @date   2016年10月29日--下午10:38:19--
*
*/

public class ImportExportAction {
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public static File upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		String path = request.getSession().getServletContext()
				.getRealPath("upload");
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/"
				+ fileName);

		return targetFile;
	}

	@RequestMapping(value = "/exportExcel.do")
	@ResponseBody
	public static void exportExcel(HttpServletRequest request,
			HttpServletResponse response,String fileName) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/template/"+fileName);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859_1"));
			OutputStream os = response.getOutputStream();
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/downloadExcel.do")
	@ResponseBody
	public static void downloadExcel(HttpServletRequest request,
			HttpServletResponse response,String fileName) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/downloadExcel/"+fileName);
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859_1"));
			OutputStream os = response.getOutputStream();
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//离线下载
	@RequestMapping(value = "/officelinedownloadExcel.do")
	@ResponseBody
	public static void officeLineDownloadExcel(String path,
			HttpServletResponse response,String fileName) {
		try {
		/*	String path = request.getSession().getServletContext()
					.getRealPath("/downloadExcel/"+fileName);*/
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859_1"));
			OutputStream os = response.getOutputStream();
			String filePath = path+fileName;
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
		//	fis.close();
		//	os.flush();
		//	os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
