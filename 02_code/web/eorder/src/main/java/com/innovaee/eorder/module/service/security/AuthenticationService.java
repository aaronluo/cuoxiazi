/***********************************************
 * Filename        : AuthenticationService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.service.BaseService;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;

/**
 * @Title: AuthenticationService
 * @Description: 验证服务
 *
 * @version V1.0
 */
public class AuthenticationService extends BaseService implements
		UserDetailsService {

	/** 用户数据访问对象 */
	@Resource
	private UserDao userDao;

	/** 安全元数据源服务对象 */
	@Resource
	private SecurityMetadataSourceService securityMetadataSourceService;

	/**
	 * 检查用户名密码是否匹配
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 匹配结果
	 */
	public boolean checkUserPassword(String username, String password) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("检查用户名密码是否匹配");
		}
		return null != userDao
				.findUserByUsernameAndPassword(username, password);
	}

	/**
	 * 根据用户名称查找用户详细信息
	 * 
	 * @param username
	 *            用户名
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据用户名称查找用户详细信息");
		}

		List<UserFunctionVo> userFunctions = securityMetadataSourceService
				.getUserFunctions(username);
		if (null == userFunctions || 0 == userFunctions.size()) {
			LOGGER.debug("用户[" + username + "] 不存在!");
			throw new UsernameNotFoundException("用户[" + username + "] 不存在!");
		}
		UserDetailsVo userDetailsVo = new UserDetailsVo();
		userDetailsVo.setUserFunctions(userFunctions);

		return userDetailsVo;
	}
}