package com.xu.manager.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.quartz.CurrentQuarz;
import com.quartz.QuartzJobFactoryImpl;
import com.quartz.ScheduleJob;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.bean.MenuBean;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.service.HelloService;
import com.xu.manager.service.MessageManager;
import com.xu.manager.service.ScanTaskManager;
import com.xu.manager.service.SensitiveWordService;
import com.xu.task.AnsyScanTask;
import com.xu.task.TestTask;
import com.xu.task.forkJoinPool.ForkJoinScanJobManager;

/**
* @author Create By Xuguoqiang
* @date   2016年12月8日--下午10:41:50--
*
*/
@Controller
@RequestMapping("/test")
public class TestQuarzController {
	@Autowired
	private SensitiveWordService sensitiveWordService;
	@Resource(name="helloService")
	private HelloService helloService;
	@Resource
	private MessageManager messageManager;
	@Autowired
	private AnsyScanTask ansyScanTask;
	@Autowired
	private TestTask testTask;
	@Autowired
	private ScanTaskManager scanTaskManager;
	/*@Autowired
	private RedisCache redisCache;*/
    /*@Autowired  
    private CacheManager cachemanager;*/
    @Autowired
    private ForkJoinScanJobManager forkJoinScanJobManager;
	
	private static Log logger = LogFactory.getLog(TestQuarzController.class);
	
	
	@RequestMapping(value = "/quartz.do")
    public ModelAndView quartz() throws SchedulerException 
    {    
        
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dynamicQuarz.xml");  
        System.out.println(ctx);
        Scheduler scheduler = (Scheduler)ctx.getBean("schedulerFactoryBean");
        
        System.out.println(scheduler);
        //这里获取任务信息数据
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        
        for (int i = 0; i < 3; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setJobName("JobName_" + i);
            job.setJobGroup("dataWork");
            job.setCronExpression("0/15 * * * * ?");
            job.setDesc("数据导入任务");
            jobList.add(job);
        }

        for (ScheduleJob job : jobList) {
         
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
        
        ModelAndView mav = new ModelAndView();
        return mav;
    }
	
	@RequestMapping("/testq.do")
	public void test1(String content) throws SchedulerException{
		ResultVo result =  sensitiveWordService.checkSensitiveWord(content);
		System.out.println(result.isHasSensitive());
		List<String > a = result.getWordList();
		for(String s:a){
			System.out.println(s);
		}
	}
	
	@RequestMapping("/testRedis.do")
	public void testRedis() throws Exception {
		
	}
	
	@RequestMapping("/service.do")
	public void service() throws Exception {
		helloService.sayHello("xuguoqiang,hello!!!!!");
		Object o = helloService.set("yangchunxue1111", "l love you11111111!!!");
		System.out.println("返回值为：---》"+o.toString());
		//Object o1 = messageManager.callBack("xuguoqiang", "hahaha");
		System.out.println("call="+o.toString());
		
	}
	
	@RequestMapping("/ansyScanTask.do")
	public void ansyScanTask() throws InterruptedException, ExecutionException, ParseException{
		//ScanTaskVo scanTaskVo = new ScanTaskVo();
		//scanTaskVo.setCarName("东风");
		//scanTaskManager.scanTask(scanTaskVo);
	/*	try{
			logger.info("我是ino级别的日志噢噢噢噢");
			logger.debug("我是debug制作噢噢噢噢！");
			int a = 9/0;
			System.out.println(a);
		}catch(Exception e){
			logger.error("出现异常信息！！！", e);
		
	}*/
		ScanTaskVo scanTaskVo = new ScanTaskVo();
		scanTaskVo.setCarName("本田");
		forkJoinScanJobManager.scanTaskJob(scanTaskVo);
/*		redisCache = (RedisCache) cachemanager.getCache("default");
		redisCache.put("xu1112", "hahahahah");
		ValueWrapper name = redisCache.get("xu1112");*/
/*		System.out.println(name);*/
		try {
			RedisClient.setString("hahahaha", "value");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String aaa = (String) RedisClient.getString("hahahaha");
		System.out.println(aaa);
	}
}
