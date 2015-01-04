/***********************************************
 * Filename        : RoleService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.RoleFunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.vo.RoleVO;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: RoleService
 * @Description: 角色服务
 *
 * @version V1.0
 */
public class RoleService extends BaseService {

	/** 角色数据访问对象 */
	@Resource
	private RoleDao roleDao;

	/** 功能数据访问对象 */
	@Resource
	private FunctionDao functionDao;

	/** 角色功能数据访问对象 */
	@Resource
	private RoleFunctionDao roleFunctionDao;

	/**
	 * 返回所有用户值对象
	 * 
	 * @return 角色值对象列表
	 */
	public List<RoleVO> findAllRoleVOs() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("返回所有角色值对象");
		}
		List<RoleVO> rolevos = new ArrayList<RoleVO>();

		List<RoleFunction> roleFunctions = null;
		List<String> functionNames = null;
		Function function = null;

		List<Role> roles = roleDao.findAllRoles();
		for (Role role : roles) {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(role, roleVO);

			roleFunctions = roleFunctionDao.findRoleFunctionsByRoleId(role
					.getRoleId());
			functionNames = new ArrayList<String>();
			for (RoleFunction roleFunction : roleFunctions) {
				// 查找功能（权限）名称
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

	/**
	 * 通过角色ID查找角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色
	 */
	public Role loadRole(Integer roleId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("通过角色ID查找角色");
		}
		return (Role) roleDao.loadRole(roleId);
	}

	/**
	 * 根据角色名称查找角色
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色
	 */
	public Role findRoleByRoleName(String roleName) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据角色名称查找角色");
		}
		return (Role) roleDao.findRoleByRoleName(roleName);
	}

	/**
	 * 保存角色
	 * 
	 * @param role
	 *            待保存的角色
	 * @return 被保存的角色
	 */
	public Role saveRole(Role role) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("保存角色");
		}
		return roleDao.saveRole(role);
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            待更新的角色
	 */
	public void updateRole(Role role) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("更新角色");
		}
		roleDao.updateRole(role);
	}

	/**
	 * 根据角色ID移除角色
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public void removeRole(Integer roleId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据角色ID移除角色");
		}
		// 1. 先删除功能
		List<RoleFunction> roleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		for (RoleFunction roleFunction : roleFunctions) {
			roleFunctionDao.remove(roleFunction.getRoleFunctionId());
		}

		// 2. 后删除角色
		roleDao.removeRole(new Role(roleId));
	}

}