/**
 * 
 */
package com.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.dao.CarInformationDao;

/**
* @author Create By Xuguoqiang
* @date   2016年10月22日--下午11:25:53--
*
*/
@Component
public class InitCacheQuartz{
	private static Log logger = LogFactory.getLog(InitCacheQuartz.class);
	@Autowired
	private  CarInformationDao carInformationDao;
	public void execute()  {
		try{
			long ms = System.currentTimeMillis();
			String key = "BASE_WORD_KEY";
	        System.out.println("*******************************初始化Redis缓存start*************************");
	        List<String> list = new ArrayList<String>();
	        list = carInformationDao.queryBaseWord();
	        RedisClient.setObject(key, list);
	        Long end = System.currentTimeMillis();
	        System.out.println("*******************************初始化Redis缓存End saveTime*************************"+(end-ms));
		}catch(Exception e){
			logger.error("start word cache error!!", e);
		}
		
	}

}
