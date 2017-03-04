package com.quartz;

import com.xu.manager.Dto.BaseDto;

/**
* @author Create By Xuguoqiang
* @date   2016年12月9日--下午6:59:13--
*
*/
public class ScheduleJobDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9222244460238174448L;
	private int status;
	private String group;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

}
