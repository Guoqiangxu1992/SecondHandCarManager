package com.xu.manager.dao;

import java.util.List;
import java.util.Map;

import com.xu.manager.bean.RolePermissionTreeVo;

/**
* @author Create By Xuguoqiang
* @date   2016年12月4日--下午4:03:54--
*
*/
public interface RolePermissionDao {

	public List<RolePermissionTreeVo> getRolePermission();

	public List<Map<String, Object>> getRoles();

}
