/***********************************************
 * Filename		: AuthenticationService.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.BaseService;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;

/**   
* @Title: AuthenticationService 
* @Description: 验证服务
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class AuthenticationService extends BaseService implements
		UserDetailsService {

	@Resource
	private UserDao userDao;

	@Resource
	private SecurityMetadataSourceService securityMetadataSourceService;

	public User getUserInfo(String username) {
		return (User) userDao.get(username);
	}

	public boolean checkUserPassword(String username, String password) {
		return null != userDao.getUserByPassword(username, password);
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		List<UserFunctionVo> userFunctions = securityMetadataSourceService
				.getUserFunctions(username);
		if (null == userFunctions || 0 == userFunctions.size()) {
			throw new UsernameNotFoundException("user[" + username
					+ "] is not found!");
		}
		UserDetailsVo userDetailsVo = new UserDetailsVo();
		userDetailsVo.setUserFunctions(userFunctions);
		
		return userDetailsVo;
	}
}