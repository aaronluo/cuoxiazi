/***********************************************
 * Filename		: AuthorizationService.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.service.security;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.innovaee.eorder.module.service.BaseService;
import com.innovaee.eorder.module.vo.UserDetailsVo;

/**   
* @Title: AuthorizationService 
* @Description: 授权服务 
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class AuthorizationService extends BaseService implements AccessDecisionManager {

	private static final Logger logger = Logger.getLogger(AuthorizationService.class);

	@Resource
	private SecurityMetadataSourceService securityMetadataSourceService;

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		String requestUrl = ((FilterInvocation) object).getRequestUrl();

		if ("anonymousUser".equals(authentication.getName())) {
			logger.info("authentication is default value, user has not login! requestUrl[" + requestUrl + "]");
			throw new InsufficientAuthenticationException("un-authenticated user");
		}

		boolean isManagedAttribute = false;
		for (ConfigAttribute ca : securityMetadataSourceService.getAllConfigAttributes()) {
			if (requestUrl.equals(ca.getAttribute())) {
				logger.debug("request url [" + requestUrl + "] has been managed.");
				isManagedAttribute = true;
				break;
			}
		}
		if (!isManagedAttribute) {
			logger.debug("request url [" + requestUrl + "] is not a managed attribute.");
			return;
		}

		UserDetailsVo userDetailsVo = (UserDetailsVo) authentication.getPrincipal();

		logger.debug("AuthorizationService.decide()=================>Authentication Info Start");
		logger.debug("AuthorizationService.decide()=================>Name[" + authentication.getName() + "]");
		logger.debug("AuthorizationService.decide()=================>Principal[" + userDetailsVo + "]");
		logger.debug("AuthorizationService.decide()=================>Authorities[" + authentication.getAuthorities() + "]");
		logger.debug("AuthorizationService.decide()=================>Credentials[" + authentication.getCredentials() + "]");
		logger.debug("AuthorizationService.decide()=================>Details[" + authentication.getDetails() + "]");
		logger.debug("AuthorizationService.decide()=================>Authentication Info End");

		for (GrantedAuthority ga : authentication.getAuthorities()) {
			if (requestUrl.equals(ga.getAuthority())) {
				logger.debug(String.format("attribute[%s] has been granted to user[%s], roles[%s]", requestUrl, authentication.getName(),
						userDetailsVo.getRolesName()));
				return;
			}
		}

		logger.debug(String.format("attribute[%s] has not been granted to user[%s], roles[%s]", requestUrl, authentication.getName(),
				userDetailsVo.getRolesName()));
		throw new AccessDeniedException("Access Denied");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		logger.debug("AuthorizationService.supports(ConfigAttribute attribute), supported attribute is: " + attribute.getAttribute());
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		logger.debug("AuthorizationService.supports(Class<?> clazz), supported class is: " + clazz.getName());
		return true;
	}

}