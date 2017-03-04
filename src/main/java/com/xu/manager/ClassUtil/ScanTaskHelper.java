package com.xu.manager.ClassUtil;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.service.SensitiveWordService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午5:02:31--
*
*/
@Service
public class ScanTaskHelper {
	
	@Autowired
	private CarInformationDao carInformationDao;
	@Autowired
	private  SensitiveWordService sensitiveWordService;
	//存储下载任务的队列 未指定容量，默认容量为Integer.MAX_VALUE
	  private BlockingQueue<Runnable> dataqueue = new LinkedBlockingQueue<Runnable>();
	  
//	    corePoolSize
//	    核心线程数，核心线程会一直存活，即使没有任务需要处理。当线程数小于核心线程数时，即使现有的线程空闲，线程池也会优先创建新线程来处理任务，而不是直接交给现有的线程处理。
//	    核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出。
//
//	    maxPoolSize
//	    当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常。
//
//	    keepAliveTime
//	    当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize。如果allowCoreThreadTimeout设置为true，则所有线程均会退出直到线程数量为0。
//
//	    allowCoreThreadTimeout
//	    是否允许核心线程空闲退出，默认值为false。
//
//	    queueCapacity
//	    任务队列容量。从maxPoolSize的描述上可以看出，任务队列的容量会影响到线程的变化，因此任务队列的长度也需要恰当的设置。
	  //10个常驻线程，任务多的时候最多20个线程，如果线程闲置60秒则销毁，队列无边界
	  private   ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, dataqueue); 
	  
		public ThreadPoolExecutor getExecutor() {
			return executor;
		}
		
		
		public void scanTask(ScanTaskVo scanTaskVo){
			if(scanTaskVo!=null){
				System.out.println("当前队列大小："+dataqueue.size()+"正在执行的线程个数："+executor.getActiveCount());
				CarInformationDto carInfoDto = new CarInformationDto();
				carInfoDto.setCarName(scanTaskVo.getCarName());
				// CarInformationDao carInformationDao=SpringUtils.getBean("carInformationDao");
				// SensitiveWordService sensitiveWordService = SpringUtils.getBean("sensitiveWordServiceImpl");
				List<CarInformation> carInfoList = carInformationDao.getCarInformation1(carInfoDto);
				try {
					Thread.sleep(13000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(CarInformation car:carInfoList){
					ResultVo r= sensitiveWordService.checkSensitiveWord(car.getCarInfoDetail());
					
					System.out.println(car.getCarName());
					if(r.isHasSensitive()){
						for(String s:r.getWordList()){
							System.out.println(s);
						}
					}
				}
				
			}
			
		}

}
