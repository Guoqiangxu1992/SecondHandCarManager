package com.Redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.xu.manager.bean.CarInformation;

/**
* @author Create By Xuguoqiang
* @date   2017年5月9日--下午9:22:38--
* @version  ValueOperations：简单K-V操作
*/
@Repository
public class RedisValueOperations {
	
	@Autowired   
    private RedisTemplate<String,Object> redisTemplate;
	
	@Resource(name="redisTemplate")   
    private  ValueOperations<String, Object> opsForValue;
	
	/**
	 * k-v保存
	 * 
	 * */
	public  void saveValue(String key,Object value){
		opsForValue.set(key, value);
	}
	
	/**
	 * k-v保存-增加过期时间
	 * 
	 * */
	public  void saveValue(String key,Object value,Long timeout){
		opsForValue.set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * k-v保存-获取值
	 * 
	 * */
	public Object getValue(String key){
		return opsForValue.get(key);
	}

}
