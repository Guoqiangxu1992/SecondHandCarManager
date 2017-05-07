package com.xu.manager.bean;

import java.io.Serializable;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午1:28:43--
*
*/
public class Channel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3481678957819618018L;
	private Long id;
	private Long channelId;
	private String channelName;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
