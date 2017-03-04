package com.quartz;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
* @author Create By Xuguoqiang
* @date   2016年12月8日--下午11:03:28--
*
*/

public class CurrentQuarz {
	@Resource
	private SchedulerFactoryBean schedulerFactoryBean;
	
	public List<ScheduleJob> getcurrentQuarzJob() throws SchedulerException{
		   //schedulerFactoryBean 由spring创建注入
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		int num = executingJobs.size();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();   
		for (JobExecutionContext executingJob : executingJobs) {
		    ScheduleJob job = new ScheduleJob();
		    JobDetail jobDetail = executingJob.getJobDetail();
		    JobKey jobKey = jobDetail.getKey();
		    Trigger trigger = executingJob.getTrigger();
		    job.setJobName(jobKey.getName());
		    job.setJobGroup(jobKey.getGroup());
		    job.setDesc("触发器:" + trigger.getKey());
		    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		 //  job.setJobStatus(triggerState.name());
		    if (trigger instanceof CronTrigger) {
		        CronTrigger cronTrigger = (CronTrigger) trigger;
		        String cronExpression = cronTrigger.getCronExpression();
		        job.setCronExpression(cronExpression);
		    }
		    jobList.add(job);
		}
		return jobList;
	}
}
