package com.xu.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quartz.ScheduleJob;
import com.quartz.ScheduleJobDto;

/**
* @author Create By Xuguoqiang
* @date   2016年12月9日--下午7:03:20--
*
*/
public interface QuartzDao {

	public List<ScheduleJob> getQuartzList(ScheduleJobDto scheduleJobDto);

	public void insertQuartzJob(ScheduleJob scheduleJob);

	public ScheduleJob findScheduleJobByJobId(Long jobId);

	public void updateStatusByJobId(Long jobId);

	public void updateStatusPauseByJobId(Long jobId);

	public void updateCronExpressionByJobId(@Param("jobId")Long jobId, @Param("cronExpression")String cronExpression);

}
