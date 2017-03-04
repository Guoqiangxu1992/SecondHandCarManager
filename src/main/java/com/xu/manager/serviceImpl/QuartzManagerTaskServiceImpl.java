package com.xu.manager.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.service.QuartzManagerTaskService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月11日--下午4:48:48--
*
*/
@Service("quartzManagerTaskService")
public class QuartzManagerTaskServiceImpl implements QuartzManagerTaskService{
	
	private static int counter = 0;  
	@Autowired
	private CarInformationDao carInformationDao;
	
	@Override
	public void startBaseWordTask(){
	
		 long ms = System.currentTimeMillis();  
	     System.out.println("*******************************定时任务开始执行，2分钟更新一次*************************");
	     System.out.println("");
	     EhCacheUtil ehcacheUtil =  EhCacheUtil.getInstace();
	     List<String> list = new ArrayList<String>();
	     list = carInformationDao.queryBaseWord();
	     ehcacheUtil.setRuleWordCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD, list);
	     List<String> list2 = ehcacheUtil.getRuleWordInCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD);
	     for(String a:list2){
	    	 System.out.println(a);
	     }
	    String list1 = ToAnalysis.parse("我就是这样的一个人，你能咋地呢？,贵州是我老家啊").toStringWithOutNature();
	    String a[] = list1.split(",");
	    for(int i = 0;i<a.length;i++){
	 	   System.out.println(a[i]);
	    }
	 	   
	     System.out.println("再次初始化 ");
	     System.out.print("(" + counter++ + ")                                       "); 
	     System.out.println("\t\t" + new Date(ms));  
	     System.out.println("");
	     System.out.println("************************************************************************************");
			
		
		


	}
	
	
}
