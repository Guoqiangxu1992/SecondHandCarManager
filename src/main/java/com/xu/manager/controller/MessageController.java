package com.xu.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xu.manager.bean.Channel;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.framework.XgqSessionBag;
import com.xu.manager.message.GetMessageMain;
import com.xu.manager.service.MessageService;

import net.sf.json.JSONObject;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午1:13:40--
*
*/
@Controller
@Scope
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/initChannel.do")
	public ModelAndView initChannel(){
		return new ModelAndView("/system/message/channel");
	}
	
	
	@RequestMapping("/getChannelList.do")
	@ResponseBody
	public String getChannelList(){
		JSONObject jsonResult = new JSONObject();
		List<Channel> list = messageService.getChannelList();
		jsonResult = com.xu.manager.ClassUtil.JsonUtils.toGridJson(11,list);
		return jsonResult.toString();
	}
	
	@RequestMapping("/startGetMessage.do")
	public void startGetMessage(){
		LoginUser loginUser1 = XgqSessionBag.getSessionBag().getLoginUser();
	}

}
