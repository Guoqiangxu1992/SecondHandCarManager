/**
 * 
 */
package com.xu.manager.bean;

import java.io.Serializable;
import java.util.Date;

import com.xu.manager.bean.RegisterUser;

/**
* @author Create By Xuguoqiang
* @date   2016年10月13日--下午11:22:53--
*
*/

public class CarInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8497379673305602473L;
	

	private Long id;
	private Long carId;
	private String carName;
	private String carType;
	private Long carTypeId;
	private Long carNameId;
	private Long travelAge;
	private Integer variableBox;//变速箱
	private Double displaceMent;//排量
	private String color;
	private String country;
	private Date carDate;//上牌日期
	private Integer carAge;
	private Integer status;
	private Long ownerId;//拥有者id
	private Price price;
	private RegisterUser registerUser;
	private String carInfoDetail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public Long getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
	public Long getCarNameId() {
		return carNameId;
	}
	public void setCarNameId(Long carNameId) {
		this.carNameId = carNameId;
	}
	public Long getTravelAge() {
		return travelAge;
	}
	public void setTravelAge(Long travelAge) {
		this.travelAge = travelAge;
	}
	public Integer getVariableBox() {
		return variableBox;
	}
	public void setVariableBox(Integer variableBox) {
		this.variableBox = variableBox;
	}
	public Double getDisplaceMent() {
		return displaceMent;
	}
	public void setDisplaceMent(Double displaceMent) {
		this.displaceMent = displaceMent;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getCarDate() {
		return carDate;
	}
	public void setCarDate(Date carDate) {
		this.carDate = carDate;
	}
	public Integer getCarAge() {
		return carAge;
	}
	public void setCarAge(Integer carAge) {
		this.carAge = carAge;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	public String getCarInfoDetail() {
		return carInfoDetail;
	}
	public void setCarInfoDetail(String carInfoDetail) {
		this.carInfoDetail = carInfoDetail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
