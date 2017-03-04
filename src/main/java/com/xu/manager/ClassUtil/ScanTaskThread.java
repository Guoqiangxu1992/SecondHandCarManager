package com.xu.manager.ClassUtil;

import com.xu.manager.bean.ScanTaskVo;

/**
* @author Create By Xuguoqiang
* @date   2016年12月18日--下午5:08:32--
*
*/
public class ScanTaskThread extends Thread{
	
	
	private ScanTaskHelper scanTaskHelper;
	
	private ScanTaskVo scanTaskVo;
	
	public ScanTaskThread(ScanTaskHelper scanTaskHelper){
		this.scanTaskHelper = scanTaskHelper;
	}
	public void run(){
		System.out.println("启动"+scanTaskVo.getCarName());
		scanTaskHelper.scanTask(scanTaskVo);
	}
	
	
	
	
	
	
	public ScanTaskHelper getScanTaskHelper() {
		return scanTaskHelper;
	}
	public void setScanTaskHelper(ScanTaskHelper scanTaskHelper) {
		this.scanTaskHelper = scanTaskHelper;
	}
	public ScanTaskVo getScanTaskVo() {
		return scanTaskVo;
	}
	public void setScanTaskVo(ScanTaskVo scanTaskVo) {
		this.scanTaskVo = scanTaskVo;
	}

}
