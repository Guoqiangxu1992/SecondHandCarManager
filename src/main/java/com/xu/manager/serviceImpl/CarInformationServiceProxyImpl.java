package com.xu.manager.serviceImpl;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.Pagination;
import com.xu.manager.service.CarInformationService;

/**
* @author Create By Xuguoqiang
* @date   2017年4月10日--下午9:25:00--
*
*/
public class CarInformationServiceProxyImpl implements CarInformationService{
	private CarInformationService carInformationService;
	
	public CarInformationServiceProxyImpl(CarInformationService carInformationService){
		this.carInformationService = carInformationService;
	}

	@Override
	public Pagination getCarInformation(CarInformationDto carInfoDto) {
		System.out.println("**********getCarInformation method start ！！！");
		carInformationService.getCarInformation(carInfoDto);
		System.out.println("**********getCarInformation method end ！！！");
		return null;
	}

	@Override
	public void insertCarInformation(CarInformation carinformation) {
		carInformationService.insertCarInformation(carinformation);
	}

}
