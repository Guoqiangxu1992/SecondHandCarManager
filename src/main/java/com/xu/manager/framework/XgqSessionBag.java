package com.xu.manager.framework;


/**
* @author Create By Xuguoqiang
* @date   2016年12月6日--下午8:46:46--
*
*/
public class XgqSessionBag {
private static final ThreadLocal<SessionBagImpl> sessionBagThread = new ThreadLocal<SessionBagImpl>();
	
    public static SessionBagImpl getSessionBag() {
        return sessionBagThread.get();
    }

    public static void setSessionBag(SessionBagImpl s){
    	sessionBagThread.set(s);
    }
    
    
    public static void clear(){
    	sessionBagThread.set(null);
    }

}
