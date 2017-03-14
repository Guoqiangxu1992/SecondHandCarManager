package com.xu.manager.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xu.manager.ClassUtil.JsonUtils;
import com.xu.manager.bean.Server;

import net.sf.json.JSONObject;

/**
* @author Create By Xuguoqiang
* @date   2017年3月11日--下午5:53:51--
*
*/
@Controller
@RequestMapping("/server")
public class ServerController {
	
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

}
