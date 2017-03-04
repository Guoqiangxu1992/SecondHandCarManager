package com.xu.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xu.manager.Dto.DownLoadDto;
import com.xu.manager.service.OfficeDownloadService;

/**
* @author Create By Xuguoqiang
* @date   2016年11月5日--下午2:29:44--
*
*/
@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestOfficeController {
	@Autowired
	private OfficeDownloadService officeDownloadService;
	
	@RequestMapping(value = "/test.do")
	public void test(HttpServletRequest request,HttpServletResponse response,Model model){
		DownLoadDto downLoadDto = new DownLoadDto();
		downLoadDto.setFileName("汽车信息");
		downLoadDto.setOperatorId(1l);
		downLoadDto.setRequest(request);
		downLoadDto.setResponse(response);
		downLoadDto.setTaskId("NB"+System.currentTimeMillis());
		officeDownloadService.addTaskToQueue(downLoadDto);
	}

}
