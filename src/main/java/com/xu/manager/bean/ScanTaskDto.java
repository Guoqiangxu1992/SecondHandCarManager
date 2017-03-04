package com.xu.manager.bean;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午3:28:10--
*
*/
public class ScanTaskDto extends BaseDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8809687151900461448L;
	private String carName;
	private Integer status;
	private Long taskId;
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
