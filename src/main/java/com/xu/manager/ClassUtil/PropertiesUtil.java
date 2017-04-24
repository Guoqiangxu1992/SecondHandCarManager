package com.xu.manager.ClassUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * @author Create By Xuguoqiang
 * @date 2017年4月24日--下午10:50:06--
 *
 */
public class PropertiesUtil {

	public static Map redProperties()  {
		Properties prop = new Properties();
		Map<String, String> hashMap = new HashMap<>();
		try {
			ClassLoader classLoader = PropertiesUtil.class.getClassLoader(); 
			InputStream resourceAsStream = classLoader.getResourceAsStream("properties/base.properties");
			prop.load(resourceAsStream); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value =  prop.getProperty(key);
				hashMap.put(key, value);
			}
			resourceAsStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	
	public static String getName(){
		String name = (String) PropertiesUtil.redProperties().get("name");
		return name;
		
	}

}
