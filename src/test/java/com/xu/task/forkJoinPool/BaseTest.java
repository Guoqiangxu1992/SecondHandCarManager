package com.xu.task.forkJoinPool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午8:46:09--
*
*/
public class BaseTest {
	public static ApplicationContext context = null;
	static{
		 context = new ClassPathXmlApplicationContext("spring/spring-applicationContext.xml");
	}
}
