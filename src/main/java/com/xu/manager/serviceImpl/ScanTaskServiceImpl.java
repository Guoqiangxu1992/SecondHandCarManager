package com.xu.manager.serviceImpl;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.ScanTaskHelper;
import com.xu.manager.ClassUtil.ScanTaskThread;
import com.xu.manager.bean.ReturnVo;
import com.xu.manager.bean.ScanTaskDto;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.dao.ScanTaskDao;
import com.xu.manager.service.ScanTaskService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午3:34:17--
*
*/
@Service
public class ScanTaskServiceImpl implements ScanTaskService{
	@Autowired
	private ScanTaskDao scanTaskDao;
	@Autowired
	private ScanTaskHelper scanTaskHelper ;

	@Override
	public List<ScanTaskVo> getScanTaskList(ScanTaskDto scanTaskDto) {
		return scanTaskDao.getScanTaskList(scanTaskDto);
	}

	@Override
	public ReturnVo checkSensitiveWordByTask(ScanTaskVo scanTaskVo) {
		System.out.println("添加一个任务开始。。。。。。。");
		final Log logger = LogFactory.getLog(getClass());
		
		ThreadPoolExecutor executor = scanTaskHelper.getExecutor(); 
		ReturnVo returnVo = new ReturnVo();
		if(scanTaskVo!=null){
			if(scanTaskVo.getTaskId()==null){
				returnVo.setResultCode(0);
				returnVo.setMessage("任务id不能为空");
				return returnVo;
			}
			
			if(StringUtils.isBlank(scanTaskVo.getCarName())){
				returnVo.setResultCode(0);
				returnVo.setMessage("汽车类别不能为空");
				return returnVo;
			}
			
			if(scanTaskVo.getStatus()==2){
				returnVo.setResultCode(0);
				returnVo.setMessage("扫描任务正在等待中，不能重新启动");
				return returnVo;
			}else if(scanTaskVo.getStatus()==3){
				returnVo.setResultCode(0);
				returnVo.setMessage("扫描任务正在执行中，不能重新启动");
				return returnVo;
			}
			
		  try{
				ScanTaskThread scanTaskThread = new ScanTaskThread(scanTaskHelper);
				scanTaskThread.setScanTaskVo(scanTaskVo);
				executor.execute(scanTaskThread);
				returnVo.setResultCode(1);
				returnVo.setMessage("扫描任务完成");
				return returnVo;
		  }catch(Exception e){
			  logger.error("扫描任务启动异常。", e);
			  returnVo.setResultCode(0);
				returnVo.setMessage("扫描任务启动异常");
		  }
		}
		return returnVo;
		
		
	}

	public ScanTaskHelper getScanTaskHelper() {
		return scanTaskHelper;
	}

	public void setScanTaskHelper(ScanTaskHelper scanTaskHelper) {
		this.scanTaskHelper = scanTaskHelper;
	}
	
	
	

}
