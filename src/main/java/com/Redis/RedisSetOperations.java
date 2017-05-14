package com.Redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

/**
* @author Create By Xuguoqiang
* @date   2017年5月14日--下午6:07:27--
*
*/
@Repository
public class RedisSetOperations {
	@Autowired   
    private RedisTemplate<String,Object> redisTemplate;

	@Resource(name="redisTemplate")
	private SetOperations<String, Object> redisSetOperations;
	
	public void save(){
	}
	
}
