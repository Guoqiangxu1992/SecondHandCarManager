package com.xu.task;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import com.xu.manager.bean.ScanTaskVo;
import com.xu.task.forkJoinPool.BaseTest;

/**
* @author Create By Xuguoqiang
* @date   2017年5月10日--下午11:04:17--
*
*/
public class TestTaskTest extends BaseTest{

	private TestTask testTask;
	@Before
	public void setUp() throws Exception {
		testTask = (TestTask) context.getBean("testTask");
	}

	@Test
	public void testScanTask() {
		ScanTaskVo scanTaskVo = new ScanTaskVo();
		try {
			testTask.scanTask(scanTaskVo);
		} catch (InterruptedException | ExecutionException | ParseException e) {
			e.printStackTrace();
		}
	}

}
