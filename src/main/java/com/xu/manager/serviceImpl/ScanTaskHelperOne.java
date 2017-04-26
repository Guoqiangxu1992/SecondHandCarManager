package com.xu.manager.serviceImpl;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.xu.manager.bean.CarInformation;

/**
* @author Create By Xuguoqiang
* @date   2017年4月25日--下午11:40:12--
*
*/
@Component("scanTaskHelperOne")
public class ScanTaskHelperOne {
	//private ScanTaskHelperOne (){}   
	private static PriorityBlockingQueue<Runnable> priQuene = new PriorityBlockingQueue<Runnable>();
	 //10个常驻线程，任务多的时候最多20个线程，如果线程闲置60秒则销毁，队列无边界
	 private  static  ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, priQuene); 
	  
		public synchronized static ThreadPoolExecutor getExecutor() {
			if(executor==null){
				executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, priQuene); ;
			}
			return executor;
		}

		
}
