package com.xu.task.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import javax.annotation.Resource;

import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.dao.ScanTaskDao;
import com.xu.manager.service.SensitiveWordService;

/**
* @author Create By Xuguoqiang
* @date   2017年4月11日--下午9:55:16--
* @version ForkJoinTask线程池练习
*/
@SuppressWarnings("serial")
public class ForkJoinTask extends RecursiveAction{
	private int last;//终止位置
	private int first;//起始位置
	private List<CarInformation> carInforList ;
	@Resource(name = "sensitiveWordService")
	private SensitiveWordService sensitiveWordService;
	@Resource(name = "scanTaskDao")
	private ScanTaskDao scanTaskDao;
	public ForkJoinTask(){}
	public ForkJoinTask(List<CarInformation> carInforList,int first,int last){
		this.carInforList = carInforList;
		this.first  =first;
		this.last  =last;
		
	}

	@Override
	protected void compute() {
		if(last-first<20){//被设置的一个极限值，一个线程一次执行多少个数据。
			this.checkSensitiveResult(carInforList.subList(first, last));
		}else{
			int middle  =(last+first)/2;
			System.out.println("Task: pending tasking="+getQueuedTaskCount());
			ForkJoinTask task1 = new ForkJoinTask(carInforList, first, middle+1);
			ForkJoinTask task2  =new ForkJoinTask(carInforList, middle+1, last);
			invokeAll(task1,task2);
		}
		
	}
	
	
	
	public void checkSensitiveResult(List<CarInformation> carInforList){
		for (CarInformation car : carInforList) {
			ScanTaskDao scanTaskDao = SpringUtils.getBean("scanTaskDao");
			SensitiveWordService sensitiveWordService = SpringUtils.getBean("sensitiveWordService");
			ResultVo r = sensitiveWordService.checkSensitiveWord(car.getCarInfoDetail());
			List<CheckResult> resultList = new ArrayList<CheckResult>();
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
			scanTaskDao.saveCheckResult(resultList);
		}
	}

}
