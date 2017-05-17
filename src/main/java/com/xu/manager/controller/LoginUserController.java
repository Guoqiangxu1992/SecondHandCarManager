/**
 * 
 */
package com.xu.manager.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Redis.RedisHashOperations;
import com.Redis.RedisListOperations;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.bean.MessageInfomation;
import com.xu.manager.bean.TaskResult;
import com.xu.manager.framework.SessionBagImpl;
import com.xu.manager.framework.XgqSessionBag;
import com.xu.manager.service.LoginUserService;

/**
* @author Create By Xuguoqiang
* @date   2016年7月27日--上午10:50:40--
*
*/
/**
 * @author summer
 *
 */
@Controller
@RequestMapping("login")
@Scope("prototype")
public class LoginUserController {
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private RedisHashOperations redisHashOperations;
	@Autowired
	private RedisListOperations redisListOperations;

	@SuppressWarnings("unused")
	@RequestMapping("/loginAjax.do")
	@ResponseBody
	public int info(@Param("username") String username, @Param("password") String password, Model model,
			HttpSession session, HttpServletRequest request) {
		try {
			String sessionId = session.getId();
			Cookie[] cookies = request.getCookies();

			String appName = "SECOND_HAND_CAR_MANAGER";
			String RequestId = appName + sessionId;
			String jsonId = appName + cookies[0].getValue();
			LoginUser user = new LoginUser();
			user = loginUserService.findUserByUsername(username);
			Boolean flage = RedisClient.getIsConnection();
			if (flage == true) {
				RedisClient.setObject(RequestId, user, 1800);
				RedisClient.setObject(jsonId, user, 1800);
			}
			session.setAttribute("SESSION_LOGIN_USER", user);
			Subject subject = SecurityUtils.getSubject();
			subject.login(new UsernamePasswordToken(username, password));
			if (subject.isAuthenticated()) {
				return 1;
			} else {
				return 0;// 密码或者用户名错误
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@RequestMapping("/tologin.do")
	@ResponseBody
	public ModelAndView login(Model model) {
		ModelAndView modelAndView = new ModelAndView("/system/index");
		if (RedisClient.getIsConnection()) {
			System.out.println(getReport());
			modelAndView.addObject("messageList", getReport());
			modelAndView.addObject("ReportListLength",  getReport().size());
			modelAndView.addObject("messageList2", getMessage());
			modelAndView.addObject("messageSize",  getMessage().size());
		}
		return modelAndView;
	}

	public List<TaskResult> getReport() {
		MessageInfomation messageInfo = new MessageInfomation();
		String hashKey = "count_car_type";
		String key = "XUGUOQIANG_CHECKWORD_RESULT_TASK1";
		Set<String> keys = redisHashOperations.getHashKeys(hashKey);
		List<TaskResult> taskResultList = new ArrayList<>();
		Long total = 0l;
		for (String s : keys) {
			TaskResult taskResult = new TaskResult();
			taskResult.setName(s);
			taskResult.setValue(redisHashOperations.getHash(hashKey, s).toString());
			taskResultList.add(taskResult);
			total += Long.valueOf(redisHashOperations.getHash(hashKey, s).toString());
		}
		for (TaskResult task : taskResultList) {
			Double rate = new BigDecimal(((Long.valueOf(task.getValue()) * 1.0 / total) * 100))
					.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			task.setRate(rate);
		}
		return taskResultList;

	}
	
	public List<String> getMessage(){
		String key = "XUGUOQIANG_CHANNEL_TEST";
		List<String> messageList2 = RedisClient.getAllValueByKey(key);
		return messageList2;
		
	}

	@RequestMapping("/loginOut.do")
	public void loginOut(Model model) {
		
	}
}
