package com.xu.manager.serviceImpl;

import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.service.MessageManager;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager{

	public void callBack(String channel,String message) {
		System.out.println("***********回调方法开始执行。。。。。。。。。。。");
		
		System.out.println("callBack:---->channel="+channel+"--->message:"+message);
		System.out.println("start up.............");
		RedisClient.IntoListByRpush(channel, message);
		/*while(true){
			if(message!=null){
				return message;
			}
		}*/
	}

}
