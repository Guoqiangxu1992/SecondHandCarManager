/**
 * 
 */
package com.xu.manager.service;

import java.util.List;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.Pagination;

/**
* @author Create By Xuguoqiang
* @date   2016年10月13日--下午11:40:29--
*
*/
/**
 * @author summer
 *
 */
public interface CarInformationService {

	/**
	 * @param carInfoDto
	 * @return
	 */
	public Pagination getCarInformation(CarInformationDto carInfoDto);
	
	public void insertCarInformation(CarInformation carinformation );

}
