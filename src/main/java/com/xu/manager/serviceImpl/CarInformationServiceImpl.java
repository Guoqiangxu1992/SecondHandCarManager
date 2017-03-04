/**
 * 
 */
package com.xu.manager.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.Pagination;
import com.xu.manager.bean.Price;
import com.xu.manager.bean.RegisterUser;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.service.CarInformationService;

/**
* @author Create By Xuguoqiang
* @date   2016年10月13日--下午11:42:46--
*
*/
/**
 * @author summer
 *
 */
@Service("carInformationService")
@Scope("prototype")
@Transactional
public class CarInformationServiceImpl implements CarInformationService{
	
	@Autowired
	private CarInformationDao carInformationDao;

	@Override
	public Pagination getCarInformation(CarInformationDto carInfoDto) {
		Pagination pageination = new Pagination();
		List<CarInformation> list = new ArrayList<CarInformation>();
		int count = carInformationDao.countGetCarInformation(carInfoDto);
		list = carInformationDao.getCarInformation(carInfoDto);
		if(CollectionUtils.isNotEmpty(list)){
			pageination.setResultList(list);
			pageination.setTotalCount(count);
		}
		return pageination;
	}

	/* (non-Javadoc)
	 * 保存车辆信息
	 */
	@Override
	public void insertCarInformation(CarInformation carinformation) {
           		Price price = new Price();
           		price.setCarId(carinformation.getCarId());
           		if(carinformation.getPrice()!=null){
           			price.setCarPrice(carinformation.getPrice().getCarPrice());
           		}
           		RegisterUser register = new RegisterUser();
           		carInformationDao.insertCarInformation(carinformation);
           		carInformationDao.insertCarPrice(price);
	}


}
