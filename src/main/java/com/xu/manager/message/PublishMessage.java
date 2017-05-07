package com.xu.manager.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.xu.manager.ClassUtil.RedisClient;

public class PublishMessage {
	//private static String channel = "XUGUQIANG_CHANNEL_TEST";
	public static void start() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String message = null;
			try {
				message = bufferReader.readLine();
				String value1 = JSON.toJSONString(message);
				if (!"quit".equals(message)) {
					RedisClient.publishMessage("XUGUOQIANG_CHANNEL_TEST", value1);
				} else {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
