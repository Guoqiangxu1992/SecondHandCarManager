package com.xu.manager.ClassUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.Dto.DownLoadDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.Pagination;
import com.xu.manager.controller.ImportExportAction;
import com.xu.manager.service.CarInformationService;

/**
* @author Create By Xuguoqiang
* @date   2016年11月4日--下午11:02:20--
*
*/
@Service
public class DownLoadHelper {
	@Autowired
	private CarInformationService carInformationService;
	//存储下载任务的队列 未指定容量，默认容量为Integer.MAX_VALUE
	  private BlockingQueue<Runnable> dataq = new LinkedBlockingQueue<Runnable>();
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
	  private   ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, dataq); 
	  
		public ThreadPoolExecutor getExecutor() {
			return executor;
		}
	  
	  
	  public void assignTask(DownLoadDto downLoadDto){
		  String filePath = this.getDownFilePath()+"/template/officeline/";
		  String tempFilePath = this.getDownFilePath()+"/downloadExcel/";
		  this.checkFile(filePath);
		  String fileName = downLoadDto.getFileName()+".xls";
		  filePath = filePath+fileName;
		  String taskId = downLoadDto.getTaskId();
		  System.out.println("************************开始处理"+fileName+"下载任务**********************");
		  System.out.println("当前队列大小"+dataq.size()+"目前正在执行的线程任务数为："+executor.getActiveCount());
		  if(dataq.size()>5){
			  System.out.println("目前的线程数大于5");
		  }
		  downLoadDto.setTaskType(1);
		  if(downLoadDto.getTaskType()==1){
			  CarInformationDto carInfoDto = new CarInformationDto();
				Pagination carpagination = carInformationService.getCarInformation(carInfoDto);
				@SuppressWarnings("unchecked")
				List<CarInformation> list = carpagination.getResultList();
				List<String> titleList = ExportCarInformationUtils.getTitle();
				if(CollectionUtils.isNotEmpty(list)&&CollectionUtils.isNotEmpty(titleList)){
					
			/*		2、通过spring提供的RequestContextHolder在非contrller层获取request和response对象*/
					//Object o = RequestContextHolder.getRequestAttributes();
					//request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
					
					//HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
					ExportCarInformationUtils.exportCarInformationUtils(list,titleList, tempFilePath, fileName);
					ImportExportAction.officeLineDownloadExcel(tempFilePath, downLoadDto.getResponse(), fileName);
				}
		  }
		  
	  }
	  
	  public String getDownFilePath(){
		  String classPath = DownLoadHelper.class.getClassLoader().getResource("").getPath(); // 经过测试，这种方法是安全的，最有效的
			String filePath = null;
			if(StringUtils.isNotEmpty(classPath) && classPath.contains("/WEB-INF/classes")){
				filePath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
			}
			// windows是\，unix是/
			if (StringUtils.isNotEmpty(filePath)&&"/".equals(File.separator)) {
				filePath = filePath.replace("\\", "/");
			}
			return filePath;
	  }
	  
		public void checkFile(String filePath){
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
		}

}
