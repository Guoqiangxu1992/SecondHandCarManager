package com.xu.task;

import com.xu.manager.bean.CarInformation;
import com.xu.manager.service.ScanTaskManager;
import com.xu.manager.serviceImpl.ScanTaskHelperOne;

/**
* @author Create By Xuguoqiang
* @date   2017年4月25日--下午11:50:38--
*
*/
public class TaskInOrder implements Runnable,Comparable<TaskInOrder>{
	private Long priority;
	private CarInformation carInformation;
	private ScanTaskManager scanTaskManager;
	
	public TaskInOrder(Long priority,ScanTaskManager scanTaskManager){
		this.priority = priority;
		this.scanTaskManager = scanTaskManager;
	}

	@Override
	public int compareTo(TaskInOrder o) {
		if(this.priority>o.priority){
			return -1;
		}else if(this.priority<o.priority){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanTaskManager.scanTask(priority);
		//System.out.println("the priority is:"+priority);
	}

}
