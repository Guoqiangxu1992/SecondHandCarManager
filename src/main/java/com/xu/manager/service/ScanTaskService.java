package com.xu.manager.service;

import java.util.List;

import com.xu.manager.bean.ReturnVo;
import com.xu.manager.bean.ScanTaskDto;
import com.xu.manager.bean.ScanTaskVo;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午3:32:27--
*
*/
public interface ScanTaskService {

	public List<ScanTaskVo> getScanTaskList(ScanTaskDto scanTaskDto);

	public ReturnVo checkSensitiveWordByTask(ScanTaskVo scanTaskVo);

}
