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

import com.xu.manager.ClassUtil.ThreadLocalDateUtil;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.service.ScanTaskManager;
import com.xu.task.AnsyScanTask.ScanTaskThread;

/**
* @author Create By Xuguoqiang
* @date   2017年3月19日--下午7:10:41--
*
*/
public class TestTask {

	@Resource
	private ScanTaskManager scanTaskManager;
	private static Log logger = LogFactory.getLog(AnsyScanTask.class);
	private static ExecutorService executor = Executors.newFixedThreadPool(10);

	@SuppressWarnings("deprecation")
	public void scanTask(ScanTaskVo scanTaskVo) throws InterruptedException, ExecutionException, ParseException{
		System.out.println("执行任务开始--->AnsyScanTask.scanTask()-->"+ThreadLocalDateUtil.formatDate(new Date()));
		ScanTaskThread ScanTaskThread = new ScanTaskThread(scanTaskVo);
		 //executor.execute(ScanTaskThread);
		ScanTaskVo scanTaskVo1 = new ScanTaskVo();
		for(int i = 0;i<10;i++){
			
			scanTaskVo1.setCarName("红旗"+i);
			ScanTaskThread ScanTaskThread1 = new ScanTaskThread(scanTaskVo1);
			//System.out.println("aaa"+scanTaskVo1.hashCode()+"bb"+scanTaskVo1.getCarName());
			//new Thread(new ScanTaskThread(scanTaskVo1)).start();;
			//Thread.sleep(2000l);
			executor.execute(ScanTaskThread1);
			
		}
	}
	
	class ScanTaskThread implements Runnable{
		private ScanTaskVo scanTaskVo;
		public ScanTaskThread(ScanTaskVo scanTaskVo){
			this.scanTaskVo=scanTaskVo;
		}
		private ThreadLocal<ScanTaskVo> threadLocal = new ThreadLocal<ScanTaskVo>(){
			@Override 
	        protected ScanTaskVo initialValue() { 
	            //System.out.println(Thread.currentThread().getName()+"initialValue"); 
	            return scanTaskVo; 
	        }
		};
		
		@Override
		public void run() {
			try{
				for(int i = 0;i<10;i++){
					scanTaskVo.setCarName("haha"+i);
					threadLocal.set(scanTaskVo);
					System.out.println(Thread.currentThread().getName()+"---入场:"+threadLocal.get().getCarName());
					Thread.sleep((long) ((Math.random()*2)*1000l));
				}
				
				//scanTaskVo.setCarName(Thread.currentThread().getName());
				//threadLocal.set(scanTaskVo);
				//boolean flag = scanTaskManager.scanTask(scanTaskVo);
				//System.out.println(Thread.currentThread().getName()+"---结果:"+threadLocal.get().getCarName());
				//scanTaskVo.setCarName("hahahahaha");
			}catch(Exception e){
				logger.error("启动线程失败！", e);
			}
		}

		
	}
	



}
