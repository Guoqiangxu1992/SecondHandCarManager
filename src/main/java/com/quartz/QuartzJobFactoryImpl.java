package com.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.service.QuartzManagerTaskService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月8日--下午10:39:08--
*
*/
@DisallowConcurrentExecution
public class QuartzJobFactoryImpl implements Job{
	
	@Autowired
	private QuartzManagerTaskService quartzManagerTaskService;

	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]"+"任务组"+scheduleJob.getJobGroup()+"con表达式"+scheduleJob.getCronExpression());
        if("基础词库".equals(scheduleJob.getJobGroup())){
        	QuartzManagerTaskService quartzManagerTaskService = SpringUtils.getBean("quartzManagerTaskService");
        	quartzManagerTaskService.startBaseWordTask();
        }
    
    }
}
