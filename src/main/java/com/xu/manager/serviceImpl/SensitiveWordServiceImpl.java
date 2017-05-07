package com.xu.manager.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.nlpcn.commons.lang.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.bean.ResultVo;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.service.SensitiveWordService;

/**
 * @author Create By Xuguoqiang
 * @date 2016年12月14日--下午8:30:02--
 *
 */
@Service("sensitiveWordService")
public class SensitiveWordServiceImpl implements SensitiveWordService {

	@Autowired
	private CarInformationDao carInformationDao;

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo checkSensitiveWord(String content) {
		boolean flag = false;
		List<String> basewordList = new ArrayList<String>();
		ResultVo resultVo = new ResultVo();
		String key = "BASE_WORD_KEY";
		try{
			if (StringUtils.isNotEmpty(content)) {
				/*EhCacheUtil ehcacheUtil = EhCacheUtil.getInstace();
				basewordList = ehcacheUtil.getRuleWordInCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD);*/
				 basewordList  =RedisClient.getObject(key, List.class);
				if (CollectionUtils.isEmpty(basewordList)) {
					basewordList = carInformationDao.queryBaseWord();
					RedisClient.setObject(key, basewordList);
				}
				String contentString = ToAnalysis.parse(content).toStringWithOutNature();
				String[] contentList = contentString.split(",");
				List<String> contenList1 = new ArrayList<String>();
				if (contentList != null && contentList.length > 0) {
					for (int i = 0; i < contentList.length; i++) {
						contenList1.add(contentList[i]);
					}
				}

				if (CollectionUtils.isNotEmpty(contenList1)) {
					contenList1.retainAll(basewordList);
					if (CollectionUtils.isNotEmpty(contenList1)) {
						flag = true;
					}
					resultVo.setHasSensitive(flag);
					resultVo.setWordList(contenList1);
				}
				return resultVo;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return resultVo;
	}
}
