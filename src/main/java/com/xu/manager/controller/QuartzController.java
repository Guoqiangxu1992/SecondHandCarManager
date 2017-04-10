package com.xu.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quartz.ScheduleJob;
import com.quartz.ScheduleJobDto;
import com.xu.manager.ClassUtil.QuartzJobUtil;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.service.QuartzService;

import net.sf.json.JSONObject;

/**
* @author Create By Xuguoqiang
* @date   2016年12月9日--下午6:36:39--
*
*/
@Controller
@RequestMapping("/quartz")
public class QuartzController {
	
	@Autowired
	private QuartzService quartzService;
	final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("/job.do")
	public ModelAndView initQuartzView(){
		return new ModelAndView("/system/quartz/quartzList");
		
	}
	
	
	@RequestMapping("/getQuartzList.do")
	@ResponseBody
	public String getQuartzList(ScheduleJobDto scheduleJobDto){
		JSONObject jsonResult = new JSONObject();
		List<ScheduleJob> schJob = quartzService.getQuartzList(scheduleJobDto);
		jsonResult = com.xu.manager.ClassUtil.JsonUtils.toGridJson(11,schJob);
		return jsonResult.toString();
		
	}
	
	@RequestMapping("/addJobInfo.do")
	public ModelAndView addJobInfo(){
		return new ModelAndView("/system/quartz/addJob");
	}
	@RequestMapping("/saveJobInfo.do")
	public void saveJobInfo(HttpSession session, ScheduleJob ScheduleJob){
		boolean flag = true;
		if(ScheduleJob!=null){
			flag = CronExpression.isValidExpression(ScheduleJob.getCronExpression());
			if(flag){
				ScheduleJob.setJobId(System.currentTimeMillis());
				LoginUser user = (LoginUser) session.getAttribute("SESSION_LOGIN_USER");
				if(user!=null){
					ScheduleJob.setCreator(user.getLoginName());
				}
				quartzService.insertQuartzJob(ScheduleJob);
			}else{
				return ;
			}
		}
		return ;
		
	}
	
	@RequestMapping("/managerJob.do")
	@ResponseBody
	public ModelAndView managerJob(ScheduleJob scheduleJob) throws SchedulerException{
		try{
			if(scheduleJob!=null&&scheduleJob.getJobId()!=null){
				ScheduleJob scheduleJob2 = quartzService.findScheduleJobByJobId(scheduleJob.getJobId());
				if(scheduleJob2.getJobStatus()==0){
					quartzService.addJob(scheduleJob2);
					quartzService.updateStatusByJobId(scheduleJob.getJobId());
					return new ModelAndView("/system/quartz/quartzList");
				}else if(scheduleJob2.getJobStatus()==1){
					quartzService.pauseJob(scheduleJob2);
					quartzService.updateStatusPauseByJobId(scheduleJob.getJobId());
					return new ModelAndView("/system/quartz/quartzList");
				}
				
			}
		}catch(Exception e){
			logger.error("启动定时任务出错！",e);
		}
		return new ModelAndView("/system/quartz/quartzList");
	}
	
	

	
	@RequestMapping("/testJob.do")
	public void test222() throws SchedulerException{
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobGroup("测试组1");
		scheduleJob.setJobName("测试2");
		  quartzService.pauseJob(scheduleJob);
		  System.out.println("重启11");
		  try {
			Thread.sleep(10000);
			System.out.println("重启22");
			ScheduleJob scheduleJob1 = new ScheduleJob();
			scheduleJob1.setJobGroup("测试组1");
			scheduleJob1.setJobName("测试2");
			quartzService.resumeJob(scheduleJob1);
			System.out.println("重启");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/editJob.do")
	@ResponseBody
	public int editJob(ScheduleJob scheduleJob){
		 try{
			 if(scheduleJob!=null&&scheduleJob.getJobId()!=null){
					ScheduleJob scheduleJob2 = quartzService.findScheduleJobByJobId(scheduleJob.getJobId());
					scheduleJob2.setCronExpression(scheduleJob.getCronExpression());
					quartzService.updateJobCron(scheduleJob2);
					quartzService.updateCronExpressionByJobId(scheduleJob2.getJobId(),scheduleJob2.getCronExpression());
					return 1;
				}
		 }catch(Exception e){
			 logger.error("更新时间表达式出错",e);
		 }
		return 0;
		
	}

}
