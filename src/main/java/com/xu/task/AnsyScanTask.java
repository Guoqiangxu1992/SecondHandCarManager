package com.xu.task;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.xu.manager.ClassUtil.ThreadLocalDateUtil;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.service.ScanTaskManager;

/**
* @author Create By Xuguoqiang
* @date   2017年3月18日--下午10:03:08--
*
*/
@Component
public class AnsyScanTask {
	@Resource
	private ScanTaskManager scanTaskManager;
	private static Log logger = LogFactory.getLog(AnsyScanTask.class);
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	@SuppressWarnings("deprecation")
	public void scanTask(ScanTaskVo scanTaskVo) throws InterruptedException, ExecutionException, ParseException{
		System.out.println("执行任务开始--->AnsyScanTask.scanTask()-->"+ThreadLocalDateUtil.formatDate(new Date()));
		Long start = System.currentTimeMillis();
		ScanTaskThread ScanTaskThread = new ScanTaskThread(scanTaskVo);
		Future<Integer> result = executor.submit(ScanTaskThread);
		if(1==result.get()){
			System.out.println("任务执行成功，耗时(ms):"+((System.currentTimeMillis())-(start)));
		}
	}
	
	class ScanTaskThread implements Callable<Integer>{
		private ScanTaskVo scanTaskVo;
		public ScanTaskThread(ScanTaskVo scanTaskVo){
			this.scanTaskVo = scanTaskVo;
		}

		@Override
		public Integer call() throws Exception {
			try{
				boolean flag = scanTaskManager.scanTask(scanTaskVo);
				if(flag){
					return 1;
				}
			}catch(Exception e){
				logger.error("启动线程失败！", e);
			}
			return 0;
		}
		
	}
	

}
