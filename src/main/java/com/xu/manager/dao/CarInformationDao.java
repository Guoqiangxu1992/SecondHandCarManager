/**
 * 
 */
package com.xu.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.Pagination;
import com.xu.manager.bean.PictureInformation;
import com.xu.manager.bean.Price;

/**
* @author Create By Xuguoqiang
* @date   2016年10月13日--下午11:45:47--
*
*/
/**
 * @author summer
 *
 */
public interface CarInformationDao {

	/**
	 * @param carInfoDto
	 * @return
	 */
	public List<CarInformation> getCarInformation(@Param("carInfoDto") CarInformationDto carInfoDto);

	/**
	 * @param carInfoDto
	 * @return
	 */
	public int countGetCarInformation(CarInformationDto carInfoDto);

	/**
	 * @param carinformation
	 */
	public void insertCarInformation(CarInformation carinformation);

	/**
	 * @param price
	 */
	public void insertCarPrice(Price price);

	/**
	 * @param picInfo
	 */
	public void savePictureInfo(PictureInformation picInfo);

	public List<String> queryBaseWord();
	
	public List<CarInformation> getCarInformation1(@Param("carInfoDto") CarInformationDto carInfoDto);

}
