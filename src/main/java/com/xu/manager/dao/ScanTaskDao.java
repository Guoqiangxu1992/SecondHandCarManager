package com.xu.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xu.manager.bean.CheckResult;
import com.xu.manager.bean.ScanTaskDto;
import com.xu.manager.bean.ScanTaskVo;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午3:35:38--
*
*/
public interface ScanTaskDao {

	public List<ScanTaskVo> getScanTaskList(@Param("scanTaskDto")ScanTaskDto scanTaskDto);

	public void saveCheckResult(List<CheckResult> resultList);

}
