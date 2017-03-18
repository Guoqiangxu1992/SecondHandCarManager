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
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.dao.CarInformationDao;

/**
* @author Create By Xuguoqiang
* @date   2016年10月22日--下午11:25:53--
*
*/
public class InitCacheQuartz implements InitializingBean,ServletContextAware{
	private static int counter = 0;  
	@Autowired
	private static CarInformationDao carInformationDao;
	
	static {
		try {
			execute();
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}


	public static void execute() throws JobExecutionException {
       
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		 long ms = System.currentTimeMillis();  
	        System.out.println("*******************************初始化缓存开始*************************");
	        System.out.println("");
	        EhCacheUtil ehcacheUtil =  EhCacheUtil.getInstace();
	        List<String> list = new ArrayList<String>();
	        list = carInformationDao.queryBaseWord();
	        ehcacheUtil.setRuleWordCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD, list);
		
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
