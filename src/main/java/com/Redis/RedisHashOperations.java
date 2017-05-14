package com.Redis;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
* @author Create By Xuguoqiang
* @date   2017年5月9日--下午11:26:19--
*
*/
@Repository
public class RedisHashOperations {
	@Autowired   
    private RedisTemplate<String,Object> redisTemplate;

	@Resource(name="redisTemplate")
	 private HashOperations<String , String, Object> opsForHash;
	
	public void saveHash(String key,String hashKey,Object value){
		opsForHash.put(key, hashKey, value);
	}
	
	public Object getHash(String key,String hashKey){
		return opsForHash.get(key, hashKey);
	}
	
	public void saveHashIncrement(String key,String hashKey,Long value){
		opsForHash.increment(key, hashKey, value);
	}
	
	public Set<String> getHashKeys(String key){
		return opsForHash.keys(key);
	}
	
	

}
