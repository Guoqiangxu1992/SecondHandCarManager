package com.xu.manager.controller;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Redis.RedisHashOperations;
import com.Redis.RedisListOperations;
import com.Redis.RedisValueOperations;
import com.xu.manager.ClassUtil.JsonUtils;
import com.xu.manager.bean.MessageInfomation;
import com.xu.manager.bean.Server;
import com.xu.manager.bean.TaskResult;

import net.sf.json.JSONObject;

/**
* @author Create By Xuguoqiang
* @date   2017年3月11日--下午5:53:51--
*
*/
@Controller
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private RedisValueOperations redisValueOperations;
	@Autowired
	private RedisListOperations redisListOperations;
	@Autowired
	private RedisHashOperations redisHashOperations;
	
	private String channel1 = "XUGUOQIANG_CHANNEL_TEST";
	@RequestMapping("/initMemory.do")
	public ModelAndView init(){
		return new ModelAndView("/system/information/serverInformation");
	}
	
	@RequestMapping("/getServerInfo.do")
	@ResponseBody
	public String getServerInfo() throws UnknownHostException{
		JSONObject jsonResult = new JSONObject();
		List<Server> list  = new ArrayList<>();
		Server server = new Server();
		Runtime runtime = Runtime.getRuntime();
		 InetAddress addr;
		 addr = InetAddress.getLocalHost();
		 String ip = addr.getHostAddress();
		 server.setIp(ip);
		 server.setTotalMemory((runtime.totalMemory())/(1024*1024));
		 server.setFreeMemory((runtime.freeMemory())/(1024*1024));
		 server.setUsedMemory((runtime.totalMemory()-runtime.freeMemory())/(1024*1024));
		 list.add(server);
		 jsonResult = JsonUtils.toGridJson(2,list);
		 String result1 = jsonResult.toString();
		return result1;
	}
	
	@RequestMapping("/getMessage.do")
	@ResponseBody
	public String getMessage(){
		MessageInfomation messageInfo = new MessageInfomation();
		String hashKey = "count_car_type";
		String key = "XUGUOQIANG_CHECKWORD_RESULT_TASK1";
		Set<String> keys = redisHashOperations.getHashKeys(hashKey);
		List<TaskResult> taskResultList =new ArrayList<>();
		
		Long total = 0l;
		for(String s:keys){
			TaskResult taskResult = new TaskResult();
			taskResult.setName(s);
			taskResult.setValue(redisHashOperations.getHash(hashKey, s).toString());
			taskResultList.add(taskResult);
			total+=Long.valueOf( redisHashOperations.getHash(hashKey, s).toString());
		}
		for(TaskResult task:taskResultList){
			Double rate = new BigDecimal(((Long.valueOf(task.getValue())*1.0/total)*100)).doubleValue();
			task.setRate(rate);
		}
		//List<Object> messageList =redisListOperations.getList(channel1, 0l, -1l);
		//messageInfo.setMessageList(messageList);
		com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
		messageInfo.setTaskResult(taskResultList);
		String json = object.toJSONString(messageInfo);
		System.out.println(json);
		return json.toString();
		
	}

}
