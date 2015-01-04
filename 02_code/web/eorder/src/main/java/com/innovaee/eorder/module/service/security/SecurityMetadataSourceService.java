/***********************************************
 * Filename        : SecurityMetadataSourceService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.RoleFunctionDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.service.BaseService;
import com.innovaee.eorder.module.vo.UserFunctionVo;

/**
 * @Title: SecurityMetadataSourceService
 * @Description: 安全元数据源服务
 *
 * @version V1.0
 */
public class SecurityMetadataSourceService extends BaseService implements
		FilterInvocationSecurityMetadataSource {

	/** 用户数据访问对象 */
	@Resource
	private UserDao userDao;

	@Resource
	private FunctionDao functionDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleFunctionDao roleFunctionDao;

	@Resource
	private SecurityMetadataSourceService securityMetadataSourceService;

	/** 所有配置属性集合 */
	private Collection<ConfigAttribute> allConfigAttributes;

	/**
	 * 返回所有功能列表
	 * 
	 * @return 功能列表
	 */
	public List<Function> getAllFunctions() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("返回所有功能列表");
		}
		return functionDao.findAllFunctions();
	}

	/**
	 * 根据用户名称返回用户角色列表
	 * 
	 * @param username
	 *            用户名称
	 * @return 用户角色列表
	 */
	public List<UserRole> getUserRoles(String username) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据用户名称返回用户角色列表");
		}
		User user = userDao.findUserByUserName(username);
		if (null != user) {
			return userRoleDao.findUserRolesByUserId(user.getUserId());
		}
		return null;
	}

	/**
	 * 根据用户角色ID查找角色功能列表
	 * 
	 * @param roleId
	 *            功能ID
	 * @return 角色功能列表
	 */
	public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据用户角色ID查找角色功能列表");
		}
		return roleFunctionDao.findRoleFunctionsByRoleId(roleId);
	}

	/**
	 * 根据用户名称查找用户功能值对象列表
	 * 
	 * @param username
	 *            用户名称
	 * @return 用户功能值对象列表
	 */
	public List<UserFunctionVo> getUserFunctions(String username) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("查找所有用户功能");
		}
		List<UserFunctionVo> userFunctions = new ArrayList<UserFunctionVo>();
		User user = userDao.findUserByUserName(username);

		List<UserRole> userRoleList = securityMetadataSourceService
				.getUserRoles(username);

		if (null != userRoleList && 0 < userRoleList.size()) {
			Iterator<UserRole> itUserRole = userRoleList.iterator();

			while (itUserRole.hasNext()) {
				UserRole userRole = itUserRole.next();
				Role role = (Role) roleDao.loadRole(userRole.getRoleId());
				Iterator<RoleFunction> itRoleFunction = securityMetadataSourceService
						.findRoleFunctionsByRoleId(userRole.getRoleId())
						.iterator();
				while (itRoleFunction.hasNext()) {
					RoleFunction roleFunction = itRoleFunction.next();

					Function function = (Function) functionDao
							.loadFunction(roleFunction.getFunctionId());

					UserFunctionVo userFunctionVo = new UserFunctionVo();
					userFunctionVo.setUser(user);
					userFunctionVo.setRole(role);
					userFunctionVo.setFunction(function);

					// Add one Item
					userFunctions.add(userFunctionVo);
				}
			}
		}

		return userFunctions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#getAttributes
	 * (java.lang.Object)
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("查找所有属性");
		}
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		Collection<ConfigAttribute> calist = new ArrayList<ConfigAttribute>();
		calist.add(new SecurityConfig(requestUrl));
		return calist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.access.SecurityMetadataSource#
	 * getAllConfigAttributes()
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("查找所有配置属性");
		}
		if (!allConfigAttributes.isEmpty()) {
			return allConfigAttributes;
		}

		allConfigAttributes = new HashSet<ConfigAttribute>();
		for (Function func : securityMetadataSourceService.getAllFunctions()) {
			if (!StringUtils.isEmpty(func.getFunctionPath())) {
				ConfigAttribute ca = new SecurityConfig(func.getFunctionPath());
				allConfigAttributes.add(ca);
			}
		}

		return allConfigAttributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#supports(java
	 * .lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SecurityMetadataSourceService.supports(Class<?> clazz), supported class is: "
					+ clazz.getName());
		}
		return true;
	}
}