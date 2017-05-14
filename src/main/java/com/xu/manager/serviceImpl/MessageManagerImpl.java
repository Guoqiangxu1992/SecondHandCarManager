package com.xu.manager.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redis.RedisListOperations;
import com.Redis.RedisValueOperations;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.service.MessageManager;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager{
	@Autowired
	private RedisListOperations redisListOperations;

	public void callBack(String channel,String message) {
		 String channel1 = "XUGUOQIANG_CHANNEL_TEST";
		System.out.println("***********回调方法开始执行。。。。。。。。。。。");
		System.out.println("start up.............callBack:---->channel="+channel+"--->message:"+message);
		redisListOperations.saveList(channel1, message);
	}

}
