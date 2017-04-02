package com.xu.test.logTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @author Create By Xuguoqiang
* @date   2017年3月25日--下午5:21:23--
*
*/
public class Log4J {
    private static Log logger = LogFactory.getLog(Log4J.class);
	public static void main(String[] args) {
		try{
			int a = 9/0;
		}catch(Exception e){
			logger.error("yichang", e);
		}
		System.out.println(111111);
		
	}

}
