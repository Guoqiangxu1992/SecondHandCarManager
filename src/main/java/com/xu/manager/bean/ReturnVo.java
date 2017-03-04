package com.xu.manager.bean;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午4:46:24--
*
*/
public class ReturnVo {
	private   int ResultCode = 0;
	public  int getResultCode() {
		return ResultCode;
	}
	public  void setResultCode(int resultCode) {
		ResultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	
}
