package com.xu.manager.bean;

/**
 * @author Create By Xuguoqiang
 * @date 2017年3月11日--下午6:03:34--
 *
 */
public class Server {
	private String ip;
	private Long totalMemory;
	private Long usedMemory;
	private Long freeMemory;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Long getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(Long totalMemory) {
		this.totalMemory = totalMemory;
	}
	public Long getUsedMemory() {
		return usedMemory;
	}
	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}
	public Long getFreeMemory() {
		return freeMemory;
	}
	public void setFreeMemory(Long freeMemory) {
		this.freeMemory = freeMemory;
	}

	
}
