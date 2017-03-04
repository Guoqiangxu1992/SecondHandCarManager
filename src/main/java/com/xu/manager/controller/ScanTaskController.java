package com.xu.manager.controller;

import java.util.List;

import org.ansj.app.crf.Model;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nlpcn.commons.lang.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xu.manager.bean.ReturnVo;
import com.xu.manager.bean.ScanTaskDto;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.service.ScanTaskService;

import net.sf.json.JSONObject;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午2:39:47--
*
*/
@Controller
@RequestMapping("/scan")
public class ScanTaskController {
	final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ScanTaskService scanTaskService;
	
	@RequestMapping("/scanTask.do")
	public ModelAndView initScanTask(){
		return new ModelAndView("/system/scan/scanTask");
		
	}
	
	@RequestMapping("/getScanTaskList.do")
	@ResponseBody
	public String getScanTaskList(ScanTaskDto scanTaskDto){
		JSONObject jsonResult = new JSONObject();
		try{
			if(scanTaskDto!=null){
				List<ScanTaskVo> scanTaskList = scanTaskService.getScanTaskList(scanTaskDto);
				jsonResult = com.xu.manager.ClassUtil.JsonUtils.toGridJson(11,scanTaskList);
			}
		}catch(Exception e){
			logger.error("查询任务出错！", e);
		}
		return jsonResult.toString();
		
	}
	
	@RequestMapping("/startScanTask.do")
	public ModelAndView startScanTask(Long taskId){
		if(taskId!=null){
			ScanTaskDto scanTaskDto = new ScanTaskDto();
			scanTaskDto.setTaskId(taskId);
			List<ScanTaskVo> scanTaskList =	scanTaskService.getScanTaskList(scanTaskDto);
			if(CollectionUtils.isNotEmpty(scanTaskList)){
				ScanTaskVo scanTaskVo = scanTaskList.get(0);
				try{
					ReturnVo returnVo = scanTaskService.checkSensitiveWordByTask(scanTaskVo);
				}catch(Exception e){
					logger.error("执行扫描任务出错", e);
				}
			}
		}
		return new ModelAndView("/system/scan/scanTask");
		
	}
	
	@RequestMapping("/threadInfo.do")
    public ModelAndView getThreadInfo(){
		return new ModelAndView("/system/scan/threadInfo");
	}
}
