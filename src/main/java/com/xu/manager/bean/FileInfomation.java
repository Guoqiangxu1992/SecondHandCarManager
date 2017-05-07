package com.xu.manager.bean;

import java.io.Serializable;

/**
* @author Create By Xuguoqiang
* @date   2017年5月4日--下午9:44:51--
*
*/
public class FileInfomation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private Integer status;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
