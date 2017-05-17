package com.xu.task.forkJoinPool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import javax.annotation.Resource;

import com.Redis.RedisHashOperations;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.dao.ScanTaskDao;
import com.xu.manager.service.SensitiveWordService;

/**
* @author Create By Xuguoqiang
* @date   2017年5月16日--下午9:46:39--
*
*/
public class forkJoinTaskCountCar  extends RecursiveAction{
	private int last;//终止位置
	private int first;//起始位置
	private List<CarInformation> carInforList ;
	private RedisHashOperations redisHashOperations;
	public forkJoinTaskCountCar(){}
	public forkJoinTaskCountCar(List<CarInformation> carInforList,RedisHashOperations redisHashOperations,int first,int last){
		this.carInforList = carInforList;
		this.first  =first;
		this.last  =last;
		this.redisHashOperations = redisHashOperations;
		
	}

	@Override
	protected void compute() {
		if(last-first<20){//被设置的一个极限值，一个线程一次执行多少个数据。
			this.CountCarResult(carInforList.subList(first, last));
		}else{
			int middle  =(last+first)/2;
			forkJoinTaskCountCar task1 = new forkJoinTaskCountCar(carInforList,redisHashOperations, first, middle+1);
			forkJoinTaskCountCar task2  =new forkJoinTaskCountCar(carInforList, redisHashOperations,middle+1, last);
			invokeAll(task1,task2);
		}
	}
	private void CountCarResult(List<CarInformation> subList) {
		String hashKey = "count_car_type";
		  for(CarInformation car:subList){
			  redisHashOperations.saveHashIncrement(hashKey, car.getCarName(), 1l);
		  }
	}

}
