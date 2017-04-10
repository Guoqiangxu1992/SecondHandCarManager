package com.xu.manager.log;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
* @author Create By Xuguoqiang
* @date   2017年4月10日--下午9:39:47--
*
*/
public class AfterLogAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
        System.out.println("*********now the"+arg1.getName()+"has been finish!!!!");		
	}

}
