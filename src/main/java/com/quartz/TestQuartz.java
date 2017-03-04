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

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.dao.CarInformationDao;

/**
* @author Create By Xuguoqiang
* @date   2016年10月22日--下午11:25:53--
*
*/
@DisallowConcurrentExecution
public class TestQuartz implements Job{
	private static int counter = 0;  
	@Autowired
	private CarInformationDao carInformationDao;


	public void execute() throws JobExecutionException {
        long ms = System.currentTimeMillis();  
        System.out.println("*******************************定时任务开始执行，五分钟更新一次*************************");
        System.out.println("");
        EhCacheUtil ehcacheUtil =  EhCacheUtil.getInstace();
        List<String> list = new ArrayList<String>();
        list = carInformationDao.queryBaseWord();
        ehcacheUtil.setRuleWordCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD, list);
        List<String> list2 = ehcacheUtil.getRuleWordInCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD);
       Result list1 = ToAnalysis.parse("我就是这样的一个人，你能咋地呢？,贵州是我老家啊");
       for(Term a:list1){
    	   System.out.println(a.toString());
       }
    	   
        System.out.println("再次初始化 基础词加载到内存成功(2小时候后清空)");
        System.out.print("(" + counter++ + ")                                       "); 
        System.out.println("\t\t" + new Date(ms));  
        System.out.println("");
        System.out.println("************************************************************************************");
		
	}


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}  
}
