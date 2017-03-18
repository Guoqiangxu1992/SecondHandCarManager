package com.xu.manager.ClassUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author Create By Xuguoqiang
* @date   2017年3月18日--下午3:22:38--
* @deprecated 线程安全的日期转换格式
*/
public class ThreadLocalDateUtil {
	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(); 
 
    public static DateFormat getDateFormat()   
    {  
        DateFormat df = threadLocal.get();  
        if(df==null){  
            df = new SimpleDateFormat(date_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  
 
    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }
 
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }   

}
