package com.innovaee.eorder.module.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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

public class SecurityMetadataSourceService extends BaseService implements
		FilterInvocationSecurityMetadataSource {

	private static final Logger logger = Logger
			.getLogger(SecurityMetadataSourceService.class);

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

	private static Collection<ConfigAttribute> allConfigAttributes;

	public List<Function> getAllFunctions() {
		return functionDao.findAllFunctions();
	}

	public List<UserRole> getUserRoles(String username) {
		User user = userDao.findUsersByUserName(username);
		return userRoleDao.findUserRolesByUserId(user.getUserId());
	}

	public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
		return roleFunctionDao.findRoleFunctionsByRoleId(roleId);
	}

	public List<UserFunctionVo> getUserFunctions(String username) {
		List<UserFunctionVo> userFunctions = new ArrayList<UserFunctionVo>();
		User user = (User) userDao.findUsersByUserName(username);
		Iterator<UserRole> itUserRole = securityMetadataSourceService
				.getUserRoles(username).iterator();
		while (itUserRole.hasNext()) {
			UserRole userRole = itUserRole.next();
			Role role = (Role) roleDao.get(userRole.getRoleId());
			Iterator<RoleFunction> itRoleFunction = securityMetadataSourceService
					.findRoleFunctionsByRoleId(userRole.getRoleId()).iterator();
			while (itRoleFunction.hasNext()) {
				RoleFunction roleFunction = itRoleFunction.next();

				Function function = (Function) functionDao.get(roleFunction
						.getFunctionId());

				UserFunctionVo userFunctionVo = new UserFunctionVo();
				userFunctionVo.setUser(user);
				userFunctionVo.setRole(role);
				userFunctionVo.setFunction(function);

				// Add one Item
				userFunctions.add(userFunctionVo);
			}
		}

		return userFunctions;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		Collection<ConfigAttribute> calist = new ArrayList<ConfigAttribute>();
		calist.add(new SecurityConfig(requestUrl));
		return calist;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (null != allConfigAttributes) {
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

	public boolean supports(Class<?> clazz) {
		logger.debug("SecurityMetadataSourceService.supports(Class<?> clazz), supported class is: "
				+ clazz.getName());
		return true;
	}
}