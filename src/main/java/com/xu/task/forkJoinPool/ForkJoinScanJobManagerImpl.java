package com.xu.task.forkJoinPool;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.dao.ScanTaskDao;

/**
* @author Create By Xuguoqiang
* @date   2017年4月11日--下午10:13:08--
*
*/
@Service
public class ForkJoinScanJobManagerImpl implements ForkJoinScanJobManager{
	@Resource(name = "carInformationDao")
	private CarInformationDao carInformationDao;
	
	
	public void scanTaskJob(ScanTaskVo scanTaskVo){
		Long time1 = System.currentTimeMillis();
		if(scanTaskVo!=null){
			CarInformationDto carInfoDto = new CarInformationDto();
			System.out.println("扫描任务开启--->" + scanTaskVo.getCarName());
			carInfoDto.setCarName(scanTaskVo.getCarName());
			List<CarInformation> carInfoList = carInformationDao.getCarInformation1(carInfoDto);
			System.out.println("当前carInfoList大小:"+carInfoList.size());
			if(CollectionUtils.isNotEmpty(carInfoList)){
				ForkJoinTaskHasReturn forkJoinTask = new ForkJoinTaskHasReturn(carInfoList,0,carInfoList.size());
				ForkJoinPool forkJoinPool = new ForkJoinPool();
				forkJoinPool.execute(forkJoinTask);
				
				do{
					System.out.println("now Thread count is :"+forkJoinPool.getActiveThreadCount());
					System.out.println("now thread steal count is :"+forkJoinPool.getStealCount());
					try {
						TimeUnit.MILLISECONDS.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(!forkJoinTask.isDone());
				
				forkJoinPool.shutdown();
				
				if(forkJoinTask.isCompletedNormally()){
					System.out.println("Thread is complete!!!!");
				}
				
				ScanTaskDao scanTaskDao = SpringUtils.getBean("scanTaskDao");
				try {
					System.out.println("返回结果大小:"+forkJoinTask.get().size());
					//scanTaskDao.saveCheckResult(forkJoinTask.get());
					List<CheckResult> checkResultList = forkJoinTask.get();
					String key  = "XUGUOQIANG_CHECKWORD_RESULT_TASK";
					for(CheckResult result:checkResultList){
						RedisClient.IntoListByRpush(key, result);
					}
					RedisClient.publishMessage("XUGUOQIANG_CHANNEL_TEST", "YOU TASK HAS BEAN DOWN!!!!,YOU CAN TO DO IT!");
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		Long time2 = System.currentTimeMillis();
		System.out.println("扫描完成耗时时间"+(time2-time1));
		
		
	}

}
