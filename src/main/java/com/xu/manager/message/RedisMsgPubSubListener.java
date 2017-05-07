package com.xu.manager.message;


import com.xu.manager.serviceImpl.MessageManagerImpl;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
    private MessageManagerImpl messageManagerImpl;
	public RedisMsgPubSubListener(MessageManagerImpl messageManagerImpl) {
		this.messageManagerImpl = messageManagerImpl;
	}

	@Override
	public void onMessage(String channel, String message) {
		// 接收到消息之后可以定义自己的处理业务逻辑
		System.out.println("现在接收的频道为:【" + channel + "】--------->接收到消息为 :【" + message + "】");
		// this.unsubscribe();
		try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		messageManagerImpl.callBack(channel, message);
		this.subscribe(channel);
		System.out.println("执行订阅之后是否执行代码！！！");

	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);

	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

}
