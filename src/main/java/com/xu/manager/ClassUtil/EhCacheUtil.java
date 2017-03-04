/**
 * 
 */
package com.xu.manager.ClassUtil;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
* @author Create By Xuguoqiang
* @date   2016年10月26日--下午8:59:31--
*
*/
public class EhCacheUtil {
     private CacheManager cacheManager = CacheManager.create();
     public final static String CACHE_NAME="RULEWORD_CACHE";//扫描规则的缓存名称
 	
 	public final static String BASE_SCAN_WORD="BASEWORD";//基础词库
 	
 	
     private static EhCacheUtil ehCacheUtil = new EhCacheUtil();
     
     public static EhCacheUtil getInstace(){
    	 return ehCacheUtil;
     }
 	public synchronized Cache getRuleCache(String cacheName) {
		if(cacheManager.getCache(cacheName)==null){
			Cache cache = new Cache(cacheName, 500,  true, false, 7200, 7200);
			cacheManager.addCache(cache);
		}
		return cacheManager.getCache(cacheName);
    }
 	
	@SuppressWarnings("unchecked")
	public  List<String> getRuleWordInCache(String cacheName, String key) {
        Cache ruleCache = getRuleCache(cacheName);
        if(null==ruleCache){
        	return null;
        }else{
        	Element ele = ruleCache.get(key);
        	if(null==ele){
        		return null;
        	}else{
        		return (List<String>) ele.getObjectValue();
        	}
        } 
    }
	
	
	 public synchronized  void setRuleWordCache(String cacheName,String key, List<String> wordList) {
	        Cache ruleCache = getRuleCache(cacheName);
	        ruleCache.remove(key);
	        if(CollectionUtils.isEmpty(getRuleWordInCache(cacheName, key))){
				List<String> matchList = new ArrayList<String>();
				if (CollectionUtils.isNotEmpty(wordList)) {
					for (String wordItem:wordList) {
						if (StringUtils.isNotEmpty(wordItem)) {
							matchList.add(wordItem);
						}
					}
				}
				Element ruleElement = new Element(key, matchList);
		        ruleCache.put(ruleElement);
			}
		 }
	 
	 
	 public static void removeCache(String cacheName, String key){
	 }
     
}
