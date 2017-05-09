package com.Redis;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
* @author Create By Xuguoqiang
* @date   2017年5月9日--下午11:08:01--
*@version ListOperations：针对list类型的数据操作
*/
@Repository
public class RedisListOperations {
	@Autowired   
    private RedisTemplate<String,Object> redisTemplate;
	
	@Resource(name="redisTemplate") 
	private ListOperations<String, Object> opsForList;
	
	
		public void saveList(String key,Object value){
			opsForList.leftPush(key, value);
		}
		
		public List<Object> getList(String key,Long start,Long end){
			return opsForList.range(key, start, end);
		}
	

}
