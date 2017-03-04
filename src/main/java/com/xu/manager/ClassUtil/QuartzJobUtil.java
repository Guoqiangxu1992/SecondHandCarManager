package com.xu.manager.ClassUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.quartz.QuartzJobFactoryImpl;
import com.quartz.ScheduleJob;

/**
* @author Create By Xuguoqiang
* @date   2016年12月10日--下午3:04:29--
*
*/
public class QuartzJobUtil {
	final Log logger = LogFactory.getLog(getClass());
	
	public static void startJob(ScheduleJob job) throws SchedulerException{

		  //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dynamicQuarz.xml");  
        System.out.println(ctx);
        Scheduler scheduler = (Scheduler)ctx.getBean("SchedulerFactoryBean");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
     
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        //不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactoryImpl.class)
                .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
     
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                .getCronExpression());
     
            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                .getCronExpression());
     
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(scheduleBuilder).build();
     
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    
	}

	public static void stopJob(ScheduleJob scheduleJob2) {
		  //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dynamicQuarz.xml");  
        System.out.println(ctx);
        Scheduler scheduler = (Scheduler)ctx.getBean("SchedulerFactoryBean");
        JobKey jobKey = JobKey.jobKey(scheduleJob2.getJobName(), scheduleJob2.getJobGroup()); 
        try {
        	List<ScheduleJob> a =getAllJobList();
        	System.out.println(a.get(0).getJobName());
		} catch (SchedulerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static List<ScheduleJob> getAllJobList() throws SchedulerException{
		//schedulerFactoryBean 由spring创建注入
       ApplicationContext ctx = new ClassPathXmlApplicationContext("dynamicQuarz.xml");  
        System.out.println(ctx);
        Scheduler scheduler = (Scheduler)ctx.getBean("SchedulerFactoryBean");
        
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDesc("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            //job.setJobStatus(triggerState.name());
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
