package com.springTest;

/**
* @author Create By Xuguoqiang
* @date   2017年3月16日--下午11:22:24--
*
*/
public class springAction {
	private UserService userService;
	
	//构造器注入
	/*public springAction(UserService userService){
		this.userService = userService;
		
	}*/
	
	//set方法注入
	
	
	
		public void saveAction(){
			userService.save();
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}
}
