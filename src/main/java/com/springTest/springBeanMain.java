package com.springTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
* @author Create By Xuguoqiang
* @date   2017年3月16日--下午10:46:00--
*
*/
public class springBeanMain {

	public static void main(String[] args) {
		/*ApplicationContext cx = new ClassPathXmlApplicationContext("spring-test.xml");
		UserService userService = (UserService) cx.getBean("userService");
		userService.save();
		System.out.println();*/
		
		@SuppressWarnings("deprecation")
		BeanFactory beanactory = new XmlBeanFactory(new ClassPathResource("spring-test.xml"));
	
		springAction spring = (springAction) beanactory.getBean("springAction");
		spring.saveAction();
		}

}
