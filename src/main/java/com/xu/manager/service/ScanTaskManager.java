package com.xu.manager.service;

import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.ScanTaskVo;

/**
* @author Create By Xuguoqiang
* @date   2017年3月19日--下午1:29:49--
*
*/
public interface ScanTaskManager {
	public boolean scanTask(ScanTaskVo scanTaskVo);

	public void scanTask(Long priority);
}
