package com.xu.manager.log;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
* @author Create By Xuguoqiang
* @date   2017年4月10日--下午9:37:36--
*
*/
public class BeforeLogAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("**********now start the "+arg0.getName()+"method!!");
	}

}
