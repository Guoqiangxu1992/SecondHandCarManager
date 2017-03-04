package com.xu.manager.Dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author Create By Xuguoqiang
* @date   2016年11月5日--上午10:39:55--
*
*/
public class DownLoadDto {
		private int taskType;//1,下载用户信息：2下载车辆信息
		private Long id;
		private String taskId;
		private Long operatorId;
		private String fileName;
		private HttpServletResponse response;
		private HttpServletRequest request;
		public int getTaskType() {
			return taskType;
		}
		public void setTaskType(int taskType) {
			this.taskType = taskType;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public Long getOperatorId() {
			return operatorId;
		}
		public void setOperatorId(Long operatorId) {
			this.operatorId = operatorId;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public HttpServletResponse getResponse() {
			return response;
		}
		public void setResponse(HttpServletResponse response) {
			this.response = response;
		}
		public HttpServletRequest getRequest() {
			return request;
		}
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}
}
