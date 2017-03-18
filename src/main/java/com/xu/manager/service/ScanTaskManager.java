package com.xu.manager.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.bean.ScanTaskVo;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.dao.ScanTaskDao;

/**
 * @author Create By Xuguoqiang
 * @date 2017年3月18日--下午10:10:51--
 *
 */
@Component
public class ScanTaskManager {
	@Resource(name = "carInformationDao")
	private CarInformationDao carInformationDao;
	@Resource(name = "sensitiveWordService")
	private SensitiveWordService sensitiveWordService;
	@Resource(name = "scanTaskDao")
	private ScanTaskDao scanTaskDao;
	private static Log logger = LogFactory.getLog(ScanTaskManager.class);

	public boolean scanTask(ScanTaskVo scanTaskVo) {
		try {
			if (scanTaskVo != null) {
				System.out.println("扫描任务开启--->" + scanTaskVo.getCarName());
				CarInformationDto carInfoDto = new CarInformationDto();
				List<CheckResult> resultList = new ArrayList<CheckResult>();
				carInfoDto.setCarName(scanTaskVo.getCarName());
				List<CarInformation> carInfoList = carInformationDao.getCarInformation1(carInfoDto);
				for (CarInformation car : carInfoList) {
					ResultVo r = sensitiveWordService.checkSensitiveWord(car.getCarInfoDetail());
					if (r.isHasSensitive()) {
						CheckResult checkResult = new CheckResult();
						checkResult.setCarId(car.getCarId());
						checkResult.setCarName(car.getCarName());
						StringBuffer stringBuffer = new StringBuffer();
						for (String s : r.getWordList()) {
							stringBuffer.append(s);
							stringBuffer.append("&");
						}
						checkResult.setResult(stringBuffer.toString());
						resultList.add(checkResult);
					}
				}
				scanTaskDao.saveCheckResult(resultList);
				return true;
			}
		} catch (Exception e) {
			logger.error("任务执行失败，方法ScanTaskManager.scanTask", e);
		}
		return false;
	}

}
