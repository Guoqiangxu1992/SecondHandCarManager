package com.xu.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.dao.ScanTaskDao;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午2:45:16--
*
*/
@Service("managerCheckTaskResult")
public class ManagerCheckTaskResult {
	
	private ExecutorService executors = Executors.newFixedThreadPool(2);
	String key1 = "XUGUOQIANG_CHECKWORD_RESULT_TASK";
	String key2 = "XUGUOQIANG_CHECKWORD_RESULT_TASK_TEMP";
	public void startJob() throws InterruptedException, ExecutionException{
		while(StringUtil.isNotBlank(RedisClient.rpop(key1))){
			 RedisClient.rpoplpush(key1, key2);
			 String string = RedisClient.rpop(key2);
			 CheckResult checkResult= JSON.parseObject(string, CheckResult.class);
			 Task task = new Task(checkResult);
			Future<Integer> result =  executors.submit(task);
			System.out.println("save success!!"+result);
			if(result.get()==0){
				RedisClient.IntoListByRpush(key1, string);
			}
		}
	}

}

	class Task implements Callable<Integer>{
		@Resource(name = "carInformationDao")
		private CarInformationDao carInformationDao;
		private CheckResult checkResult;
		public Task(CheckResult checkResult){
		this.checkResult=checkResult;
		}
		ScanTaskDao scanTaskDao = SpringUtils.getBean("scanTaskDao");
		@Override
		public Integer call() throws Exception {
			try{
				List<CheckResult> list = new ArrayList<>();
				list.add(checkResult);
				scanTaskDao.saveCheckResult(list);
				return 1;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
			
		}
		
		
	}
