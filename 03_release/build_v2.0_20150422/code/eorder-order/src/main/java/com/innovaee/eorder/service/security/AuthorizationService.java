/***********************************************
 * Filename        : AuthorizationService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.security;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

/**
 * @Title: AuthorizationService
 * @Description: 授权服务
 *
 * @version V1.0
 */
public class AuthorizationService implements
        AccessDecisionManager {

    /** 安全元数据源服务对象 */
    @Resource
    private SecurityMetadataSourceService securityMetadataSourceService;

    /**
     * <pre>
     * AccessDecisionManager 使用方法参数传递所有信息，这好像在认证评估时进行决定。
     * 特别是，在真实的安全方法期望调用的时候，传递安全Object
     * 启用那些参数。比如，让我们假设安全对象是一个MethodInvocation。很容易为任何Customer
     * 参数查询MethodInvocation，，然后在AccessDecisionManager
     * 里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。 如果访问被拒绝，实现将抛 出
     * 一个AccessDeniedException 异常。
     * </pre>
     * 
     * @param authentication
     *            调用者调用的方法（不能为空）
     * 
     * @param object
     *            被调用的对象
     * @param configAttributes
     *            被调用的关联属性
     *
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        if ("anonymousUser".equals(authentication.getName())) {
            throw new InsufficientAuthenticationException("未验证的用户");
        }

        boolean isManagedAttribute = false;
        for (ConfigAttribute ca : securityMetadataSourceService
                .getAllConfigAttributes()) {
            if (requestUrl.equals(ca.getAttribute())) {
                isManagedAttribute = true;
                break;
            }
        }
        if (!isManagedAttribute) {
            return;
        }

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (requestUrl.equals(ga.getAuthority())) {
                return;
            }
        }

        throw new AccessDeniedException("拒绝访问");
    }

    /**
     * <pre>
     * 在启动的时候被AbstractSecurityInterceptor调用， 来决定AccessDecisionManager
     * 是否可以执行传递ConfigAttribute。
     * </pre>
     * 
     * @param attribute
     *            配置属性
     *
     * @return 是否支持
     */

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * <pre>
     * 方法被安全拦截器实现调用， 包含安全拦截器将显示的
     * AccessDecisionManager 支持安全对象的类型。
     * </pre>
     * 
     * @param clazz
     *            查询的类
     *
     * @return 是否支持
     */
    public boolean supports(Class<?> clazz) {
        return true;
    }

}