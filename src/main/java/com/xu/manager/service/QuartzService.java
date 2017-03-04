package com.xu.manager.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.quartz.ScheduleJob;
import com.quartz.ScheduleJobDto;

/**
* @author Create By Xuguoqiang
* @date   2016年12月9日--下午6:54:43--
*
*/
public interface QuartzService {

	public List<ScheduleJob> getQuartzList(ScheduleJobDto scheduleJobDto);

	public void insertQuartzJob(ScheduleJob scheduleJob);

	public ScheduleJob findScheduleJobByJobId(Long jobId);

	public void updateStatusByJobId(Long jobId);
	public  List<ScheduleJob> getAllJobList() throws SchedulerException;

	public void addJob(ScheduleJob scheduleJob2)throws SchedulerException ;
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException;

	public void resumeJob(ScheduleJob scheduleJob1) throws SchedulerException ;

	public void updateStatusPauseByJobId(Long jobId);
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;

	public void updateCronExpressionByJobId(Long jobId, String cronExpression);

}
