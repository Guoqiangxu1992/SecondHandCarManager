package com.xu.manager.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.ClassUtil.ThreadLocalDateUtil;
import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.dao.ScanTaskDao;
import com.xu.manager.service.ScanTaskManager;
import com.xu.manager.service.SensitiveWordService;
import com.xu.manager.service.TaskInOrderService;
import com.xu.task.TaskInOrder;
import com.xu.task.ThreadPoolFactory.ThreadPoolConfig;
import com.xu.task.ThreadPoolFactory.ThreadPoolFactory;

/**
* @author Create By Xuguoqiang
* @date   2017年4月25日--下午11:24:42--
*
*/
@Service("taskInOrderService")
public class TaskInOrderServiceImpl implements TaskInOrderService{
	
	@Resource(name = "carInformationDao")
	private CarInformationDao carInformationDao;
	@Resource
	private SensitiveWordService sensitiveWordService;
	@Resource
	private ScanTaskDao scanTaskDao;
	@Resource
	private ScanTaskManager scanTaskManager;
	@SuppressWarnings("static-access")
	public void taskInOrderExecute() throws ParseException{
		CarInformationDto carInfoDto = new CarInformationDto();
		carInfoDto.setCarName("本田");
		System.out.println("taskInOrderExecute扫描任务开启--->" + ThreadLocalDateUtil.formatDate(new java.util.Date()));
		List<CarInformation> carInfoList = carInformationDao.getCarInformation1(carInfoDto);
		Task task = new Task(carInfoList);
		ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
		threadPoolConfig.setCorePoolSize(5);
		threadPoolConfig.setKeepAliveTime(20);
		threadPoolConfig.setMaximumPoolSize(10);
		threadPoolConfig.setUnit(TimeUnit.MILLISECONDS);
		BlockingQueue<Runnable> quene = new LinkedBlockingQueue<>(50);
		threadPoolConfig.setWorkQueue(quene);
		//ThreadPoolFactory executor = ThreadPoolFactory.getInstance(threadPoolConfig);
		PriorityBlockingQueue<Runnable> priorityQuene = new PriorityBlockingQueue<>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5, 1, TimeUnit.MILLISECONDS, priorityQuene);
		for(long i = 0;i<10;i++){
			TaskInOrder TaskInOrder  =new TaskInOrder(i,scanTaskManager);
			executor.execute(TaskInOrder);
		}
		
		
		
	}
	
	
	class Task implements Runnable{
		private List<CarInformation> carInfoList;
		public Task(List<CarInformation> carInfoList){
			this.carInfoList = carInfoList;
		}

		@Override
		public void run() {
			checkSensitiveResult(carInfoList);
		}
		
		
		public void checkSensitiveResult(List<CarInformation> carInforList){
			List<CheckResult> resultList = new ArrayList<CheckResult>();
			for (CarInformation car : carInforList) {
				ResultVo r = sensitiveWordService.checkSensitiveWord(car.getCarInfoDetail());
				if (r.isHasSensitive()) {
					CheckResult checkResult = new CheckResult();
					checkResult.setCarId(car.getCarId());
					checkResult.setCarName(car.getCarName());
					StringBuffer stringBuffer = new StringBuffer();
					for (String s : r.getWordList()) {
						stringBuffer.append(s);
						stringBuffer.append("&");
					}
					checkResult.setResult(stringBuffer.toString());
					resultList.add(checkResult);
				}
			}
			scanTaskDao.saveCheckResult(resultList);
		}
		
		
	}

}
