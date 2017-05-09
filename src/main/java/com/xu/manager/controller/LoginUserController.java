/**
 * 
 */
package com.xu.manager.controller;

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

import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.bean.LoginUser;
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

	@SuppressWarnings("unused")
	@RequestMapping("/loginAjax.do")
	@ResponseBody
	public int info(@Param("username") String username, @Param("password") String password, Model model,
			HttpSession session) {
		try {
			String sessionId = session.getId();
			String appName = "SECOND_HAND_CAR_MANAGER";
			String RequestId = appName + sessionId;
			LoginUser user = new LoginUser();
			user = loginUserService.findUserByUsername(username);
			Boolean flage = RedisClient.getIsConnection();
			if(flage==true){
				RedisClient.setObject(RequestId, user, 1800);
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
		return new ModelAndView("/system/index");
	}

	@RequestMapping("/loginOut.do")
	public void loginOut(Model model) {

	}
}
