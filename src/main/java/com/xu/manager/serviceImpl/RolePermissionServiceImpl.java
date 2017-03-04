package com.xu.manager.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.manager.bean.RolePermissionTreeVo;
import com.xu.manager.dao.RolePermissionDao;
import com.xu.manager.service.RolePermissionService;

/**
* @author Create By Xuguoqiang
* @date   2016年12月4日--下午4:02:08--
*
*/
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService{
     
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Override
	public List<RolePermissionTreeVo> getRolePermission() {
		return rolePermissionDao.getRolePermission();
	}

	@Override
	public List<Map<String, Object>> getRoles() {
		return rolePermissionDao.getRoles();
	}

}
