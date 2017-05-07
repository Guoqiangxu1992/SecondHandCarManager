package com.xu.task.forkJoinPool;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xu.manager.bean.ScanTaskVo;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午8:51:22--
*
*/
public class ForkJoinScanJobManagerTest extends BaseTest{
	private ForkJoinScanJobManagerImpl  forkJoinScanJobManagerImpl  ;


	@Before
	public void setUp() throws Exception {
		forkJoinScanJobManagerImpl= (ForkJoinScanJobManagerImpl) context.getBean("forkJoinScanJobManagerImpl");
	}

	@Test
	public void testScanTaskJob() {
		try{
			ScanTaskVo scanTaskVo = new ScanTaskVo();
			scanTaskVo.setCarName("本田");
			forkJoinScanJobManagerImpl.scanTaskJob(scanTaskVo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
