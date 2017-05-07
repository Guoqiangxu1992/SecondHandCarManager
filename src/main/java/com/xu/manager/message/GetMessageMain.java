package com.xu.manager.message;

public class GetMessageMain {

	public static void start() {
		new Thread(new ReceiveMessage()).start();
		//PublishMessage.start();
	}

}
