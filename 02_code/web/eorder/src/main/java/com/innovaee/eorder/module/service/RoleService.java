package com.innovaee.eorder.module.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.RoleFunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.vo.RoleVO;

public class RoleService extends BaseService {

	@Resource
	private RoleDao roleDao;

	@Resource
	private FunctionDao functionDao;

	@Resource
	private RoleFunctionDao roleFunctionDao;

	public List<Role> findAllRoles() {
		return (List<Role>) roleDao.findAllRoles();
	}

	/**
	 * 返回所有用户
	 * 
	 * @return
	 */
	public List<RoleVO> findAllRoleVOs() {
		List<RoleVO> rolevos = new ArrayList<RoleVO>();

		List<RoleFunction> roleFunctions = null;
		List<String> functionNames = null;
		// RoleFunction roleFunction = null;
		Function function = null;

		List<Role> roles = roleDao.findAllRoles();
		for (Role role : roles) {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(role, roleVO);

			roleFunctions = roleFunctionDao.findRoleFunctionsByRoleId(role
					.getRoleId());
			functionNames = new ArrayList<String>();
			for (RoleFunction roleFunction : roleFunctions) {
				function = functionDao.loadFunction(roleFunction
						.getFunctionId());
				if (0 != function.getFunctionParent()) {
					functionNames.add(function.getFunctionName());
				}
			}
			roleVO.setFunctionName(functionNames.toString());

			rolevos.add(roleVO);
		}

		return rolevos;
	}

	public Role loadRole(Integer roleId) {
		return (Role) roleDao.get(roleId);
	}

	public Role findRolesByRoleName(String roleName) {
		return (Role) roleDao.findRolesByRoleName(roleName);
	}

	public Role saveRole(Role role) {
		return roleDao.saveRole(role);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void removeRole(Integer roleId) {
		// 1. 先删除功能
		List<RoleFunction> roleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		for (RoleFunction roleFunction : roleFunctions) {
			roleFunctionDao.remove(roleFunction.getRoleFunctionId());
		}

		// 2. 后删除角色
		roleDao.removeRole(new Role(roleId));
	}

	public void removeRoles(String[] roleIds) {
		int length = roleIds.length;
		for (int i = 0; i < length; i++) {
			roleDao.removeRole(new Role(Integer.parseInt(roleIds[i])));
		}
	}
}