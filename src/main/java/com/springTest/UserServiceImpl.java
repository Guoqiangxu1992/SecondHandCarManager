package com.springTest;

/**
* @author Create By Xuguoqiang
* @date   2017年3月16日--下午10:40:27--
*
*/
public class UserServiceImpl implements UserService{
	
	private UserDao u;

	public void setUserDao(UserDao u) {
		this.u = u;
	}
	
	public void save(){
		u.save();
	}

}
