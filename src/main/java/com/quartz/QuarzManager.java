package com.quartz;

import java.util.HashMap;
import java.util.Map;

/**
* @author Create By Xuguoqiang
* @date   2016年12月8日--下午7:50:56--
*
*/
public class QuarzManager {
	
	/** 计划任务map */
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();
	public static void addJob(ScheduleJob scheduleJob) {
	    jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
	}
}
