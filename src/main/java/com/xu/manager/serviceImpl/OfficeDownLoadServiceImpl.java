package com.xu.manager.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xu.manager.ClassUtil.DownLoadHelper;
import com.xu.manager.ClassUtil.DownTaskRuning;
import com.xu.manager.Dto.DownLoadDto;
import com.xu.manager.service.OfficeDownloadService;

/**
* @author Create By Xuguoqiang
* @date   2016年11月4日--下午10:58:37--
*
*/

@Service("officeDownloadService")
@Scope("prototype")
@Transactional
public class OfficeDownLoadServiceImpl implements OfficeDownloadService{
       private static Log logger = LogFactory.getLog(OfficeDownLoadServiceImpl.class);
       @Autowired
        DownLoadHelper downLoadHelper = new DownLoadHelper();
       ThreadPoolExecutor executor = downLoadHelper.getExecutor();
       
       public List<String> addTaskToQueue(DownLoadDto downLoadDto){
    	   List<String> resultList = new ArrayList<String>();
    	 try{
    		  if("".equals(downLoadDto.getFileName())){
       		   resultList.add("缺少文件名");
       		   return resultList;
       	   }
       	   if(downLoadDto.getOperatorId()==null){
       		   resultList.add("缺少操作人Id");
       		   return resultList;
       	   }
       	   
       	   DownTaskRuning downTask = new DownTaskRuning(downLoadHelper);
       	   downTask.setDownLoadDto(downLoadDto);
       	   executor.execute(downTask);
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 logger.error("提交任务异常", e);
    		 resultList.add("提交任务异常");
    		 return resultList;
    	 }
    	   
		return resultList;
    	   
       }
}
