package com.xu.task.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.dao.ScanTaskDao;
import com.xu.manager.service.SensitiveWordService;

/**
* @author Create By Xuguoqiang
* @date   2017年4月13日--下午10:31:10--
*
*/
@SuppressWarnings("serial")
public class ForkJoinTaskHasReturn extends RecursiveTask<List<CheckResult>>{
	
	private List<CarInformation> list;
	List<CheckResult> resultlist = new ArrayList<>();
	private int first;
	private int last;
	
	public ForkJoinTaskHasReturn(List<CarInformation> list,int first,int last){
		this.list = list;
		this.last = last;
		this.first = first;
	}

	@Override
	protected List<CheckResult> compute() {
		
		List<CheckResult> resultlistTemp = new ArrayList<>();
		if((last-first)<10){
			resultlistTemp = checkSensitiveResult(list.subList(first, last));
		}else{
			int middle = (first+last)/2;
			ForkJoinTaskHasReturn task1 =new ForkJoinTaskHasReturn(list,first,middle+1);
			ForkJoinTaskHasReturn task2 =new ForkJoinTaskHasReturn(list,middle+1,last);
			invokeAll(task1,task2);
			try {
				resultlistTemp = groppResults(task1.get(),task2.get());
				System.out.println("size:"+resultlist.size());
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return resultlistTemp;
	}
	
	public List<CheckResult> checkSensitiveResult(List<CarInformation> carInforList){
		List<CheckResult> resultList = new ArrayList<CheckResult>();
		for (CarInformation car : carInforList) {
			SensitiveWordService sensitiveWordService = SpringUtils.getBean("sensitiveWordService");
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
		return resultList;
	}
	
	
	public List<CheckResult> groppResults(List<CheckResult> result1,List<CheckResult> result2){
		result1.addAll(result2);
		return result1;
		
	}

}
