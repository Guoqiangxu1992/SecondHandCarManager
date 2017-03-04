/**
 * 
 */
package com.xu.manager.Dto;

import java.sql.Date;

/**
* @author Create By Xuguoqiang
* @date   2016年10月13日--下午11:33:14--
*
*/
public class CarInformationDto  extends BaseDto{
       private String carName;
       private Integer variableBox;
       private Date startTime;
       private Date endTime;
       private Long ownerId;
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Integer getVariableBox() {
		return variableBox;
	}
	public void setVariableBox(Integer variableBox) {
		this.variableBox = variableBox;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
