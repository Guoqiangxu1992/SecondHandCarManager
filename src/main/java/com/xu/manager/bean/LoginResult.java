package com.xu.manager.bean;

import java.util.List;
import java.util.Map;

/**
* @author Create By Xuguoqiang
* @date   2016年11月20日--下午3:25:49--
*
*/
public class LoginResult {
	private LoginUser loginUser;
	private List<Map<String, MenuBean>> lst;
	public LoginUser getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	public List<Map<String, MenuBean>> getLst() {
		return lst;
	}
	public void setLst(List<Map<String, MenuBean>> lst) {
		this.lst = lst;
	}

}
