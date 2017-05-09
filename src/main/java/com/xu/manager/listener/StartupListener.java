package com.xu.manager.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.message.GetMessageMain;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午9:47:14--
*初始化监听redis发过来的消息，在app启动后一直监听
*/
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent >{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try{
			if(RedisClient.getIsConnection()){
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("--                                                                                                                                          --");
				System.out.println("--                                                                                                                                          --");
				System.out.println("--                                                                                                                                          --");
				System.out.println("---------------------The app is connection to the Redis!!---------------------------------");
				System.out.println("--                                                                                                                                          --");
				System.out.println("--                                                                                                                                          --");
				System.out.println("------------------------------------------------------------------------------------------------");
			}
			GetMessageMain getMessageMain  =new GetMessageMain();
	    	//getMessageMain.start();
		}catch(Exception e){}
	}

}
