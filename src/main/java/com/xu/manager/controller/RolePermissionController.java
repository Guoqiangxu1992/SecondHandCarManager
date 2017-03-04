package com.xu.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xu.manager.bean.RolePermissionTreeVo;
import com.xu.manager.service.RolePermissionService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月4日--下午3:30:58--
*
*/
@RequestMapping("/role")
@Controller
public class RolePermissionController {
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@RequestMapping("/rolePermission.do")
	public ModelAndView initRolePermission(Model model){
		ModelAndView modelAndView = new ModelAndView();
		//查出所以得权限
		List<RolePermissionTreeVo> listTree = rolePermissionService.getRolePermission();
		modelAndView.addObject("allPermissionLst", listTree);
		//查出所有的角色
		List<Map<String, Object>> lstR = rolePermissionService.getRoles();
		modelAndView.addObject("roleLst", lstR);
		modelAndView.setViewName("/system/role/rolePermission");
		return modelAndView;
		
	}

}
