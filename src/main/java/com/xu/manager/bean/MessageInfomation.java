package com.xu.manager.bean;

import java.util.List;

/**
* @author Create By Xuguoqiang
* @date   2017年5月14日--下午4:58:43--
*
*/
public class MessageInfomation {
	private List<Object> messageList;
	private List<TaskResult> taskResult;
	public List<Object> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Object> messageList2) {
		this.messageList = messageList2;
	}
	public List<TaskResult> getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(List<TaskResult> taskResult) {
		this.taskResult = taskResult;
	}

}
