package com.xu.manager.ClassUtil;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mchange.v2.util.DoubleWeakHashMap;
import com.xu.manager.Dto.DownLoadDto;

/**
* @author Create By Xuguoqiang
* @date   2016年11月4日--下午11:09:37--
*
*/
public class DownTaskRuning extends Thread{
	private static Log logger = LogFactory.getLog(DownTaskRuning.class);
	private DownLoadDto downLoadDto;
	//队列管理器
	@Autowired
	private DownLoadHelper downLoadHelper ;
	public DownTaskRuning(DownLoadHelper downLoadHelper){
		this.downLoadHelper = downLoadHelper;
	}
	
	public void run(){
		if(downLoadDto==null){
			System.out.println("erroe:下载的参数出错。。");
		}
		if(downLoadDto!=null){
			try{
				if(downLoadHelper!=null){
					
					System.out.println("Runing的任务分配开始。。");
					downLoadHelper.assignTask(downLoadDto);
					
				}
			}catch(Exception e){
				logger.error("任务分配错误",e);
			}
			}
		}

	public DownLoadDto getDownLoadDto() {
		return downLoadDto;
	}

	public void setDownLoadDto(DownLoadDto downLoadDto) {
		this.downLoadDto = downLoadDto;
	} 
	

}
