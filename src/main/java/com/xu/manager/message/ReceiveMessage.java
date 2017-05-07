package com.xu.manager.message;

import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.serviceImpl.MessageManagerImpl;

public class ReceiveMessage implements Runnable {
	private String channel = "XUGUOQIANG_CHANNEL_TEST";

	public void run() {
		while (true) {
			ressiveMessage();
			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void ressiveMessage() {
		RedisMsgPubSubListener listener = new RedisMsgPubSubListener(new MessageManagerImpl());
		RedisClient.receiveMessage(listener, channel);
	}

}
