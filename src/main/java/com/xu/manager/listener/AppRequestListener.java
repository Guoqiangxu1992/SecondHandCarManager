package com.xu.manager.listener;

import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.nlpcn.commons.lang.util.CollectionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xu.manager.ClassUtil.SpringUtils;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.framework.SessionBagImpl;
import com.xu.manager.framework.XgqSessionBag;
import com.xu.manager.service.LoginUserService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月6日--下午9:50:13--
*
*/
public class AppRequestListener implements ServletRequestListener{
	

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
			XgqSessionBag.clear();
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		SessionBagImpl sessionBag = XgqSessionBag.getSessionBag();
		String username = request.getParameter("username");
		
		if(StringUtils.isNotBlank(username)&&sessionBag==null){
			sessionBag = new SessionBagImpl();
			LoginUserService loginUserService = SpringUtils.getBean("loginUserService");
			LoginUser loginUser = loginUserService.findUserByUsername(username);
			System.out.println(loginUser.getEmail());
			if(loginUser!=null){
				sessionBag.setLoginUser(loginUser);
				XgqSessionBag.setSessionBag(sessionBag);
				LoginUser loginUser1 = XgqSessionBag.getSessionBag().getLoginUser();
				System.out.println(loginUser1.getEmail());
			} 
		}
	}


}
