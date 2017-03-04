package com.xu.manager.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xu.manager.Dto.DownLoadDto;

/**
* @author Create By Xuguoqiang
* @date   2016年11月4日--下午10:56:09--
*离线下载
*/
public interface OfficeDownloadService {
	public List<String> addTaskToQueue(DownLoadDto downLoadDto);
}
