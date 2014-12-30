/***********************************************
 * Filename        : AuthorizationService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service.security;

import com.innovaee.eorder.module.service.BaseService;
import com.innovaee.eorder.module.vo.UserDetailsVo;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

import javax.annotation.Resource;

/**
 * @Title: AuthorizationService
 * @Description: 授权服务
 *
 * @version V1.0
 */
public class AuthorizationService extends BaseService implements
        AccessDecisionManager {

    private static final Logger LOGGER = Logger
            .getLogger(AuthorizationService.class);

    @Resource
    private SecurityMetadataSourceService securityMetadataSourceService;

    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        if ("anonymousUser".equals(authentication.getName())) {
            LOGGER.info("authentication is default value, user has not login! requestUrl["
                    + requestUrl + "]");
            throw new InsufficientAuthenticationException(
                    "un-authenticated user");
        }

        boolean isManagedAttribute = false;
        for (ConfigAttribute ca : securityMetadataSourceService
                .getAllConfigAttributes()) {
            if (requestUrl.equals(ca.getAttribute())) {
                LOGGER.debug("request url [" + requestUrl
                        + "] has been managed.");
                isManagedAttribute = true;
                break;
            }
        }
        if (!isManagedAttribute) {
            LOGGER.debug("request url [" + requestUrl
                    + "] is not a managed attribute.");
            return;
        }

        UserDetailsVo userDetailsVo = (UserDetailsVo) authentication
                .getPrincipal();

        LOGGER.debug("AuthorizationService.decide()=================>Authentication Info Start");
        LOGGER.debug("AuthorizationService.decide()=================>Name["
                + authentication.getName() + "]");
        LOGGER.debug("AuthorizationService.decide()=================>Principal["
                + userDetailsVo + "]");
        LOGGER.debug("AuthorizationService.decide()=================>Authorities["
                + authentication.getAuthorities() + "]");
        LOGGER.debug("AuthorizationService.decide()=================>Credentials["
                + authentication.getCredentials() + "]");
        LOGGER.debug("AuthorizationService.decide()=================>Details["
                + authentication.getDetails() + "]");
        LOGGER.debug("AuthorizationService.decide()=================>Authentication Info End");

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (requestUrl.equals(ga.getAuthority())) {
                LOGGER.debug(String
                        .format("attribute[%s] has been granted to user[%s], roles[%s]",
                                requestUrl, authentication.getName(),
                                userDetailsVo.getRolesName()));
                return;
            }
        }

        LOGGER.debug(String.format(
                "attribute[%s] has not been granted to user[%s], roles[%s]",
                requestUrl, authentication.getName(),
                userDetailsVo.getRolesName()));
        throw new AccessDeniedException("Access Denied");
    }

    public boolean supports(ConfigAttribute attribute) {
        LOGGER.debug("AuthorizationService.supports(ConfigAttribute attribute), "
                + "supported attribute is: "
                + attribute.getAttribute());
        return true;
    }

    public boolean supports(Class<?> clazz) {
        LOGGER.debug("AuthorizationService.supports(Class<?> clazz), supported class is: "
                + clazz.getName());
        return true;
    }

}