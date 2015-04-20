/***********************************************
 * Filename        : SecurityMetadataSourceService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.innovaee.eorder.dao.FunctionDao;
import com.innovaee.eorder.dao.RoleDao;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.vo.UserFunctionVO;

/**
 * @Title: SecurityMetadataSourceService
 * @Description: 安全元数据源服务
 *
 * @version V1.0
 */
public class SecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    /** 用户数据访问对象 */
    @Resource
    private UserDao userDao;

    /** 功能数据访问对象 */
    @Resource
    private FunctionDao functionDao;

    /** 角色数据访问对象 */
    @Resource
    private RoleDao roleDao;

    /** 安全元数据服务类对象 */
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
        return functionDao.loadAll();
    }

    /**
     * 根据用户名称返回用户角色列表
     * 
     * @param username
     *            用户名称
     * @return 用户角色列表
     */
    public Set<Role> getUserRoles(String username) {
        User user = userDao.findUserByUserName(username);
        if (null != user) {
            return user.getRoles();
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
    // public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
    // if (LOGGER.isDebugEnabled()) {
    // LOGGER.debug("根据用户角色ID查找角色功能列表");
    // }
    // return roleFunctionDao.findRoleFunctionsByRoleId(roleId);
    // }

    /**
     * 根据用户名称查找用户功能值对象列表
     * 
     * @param username
     *            用户名称
     * @return 用户功能值对象列表
     */
    public List<UserFunctionVO> getUserFunctions(String username) {
        List<UserFunctionVO> userFunctions = new ArrayList<UserFunctionVO>();
        User user = userDao.findUserByUserName(username);

        Set<Role> roleList = securityMetadataSourceService
                .getUserRoles(username);
        
        for (Role role : roleList) {
            Set<Function> functions = role.getFunctions();
            for (Function function : functions) {
                UserFunctionVO userFunctionVo = new UserFunctionVO();
                userFunctionVo.setUser(user);
                userFunctionVo.setRole(role);
                userFunctionVo.setFunction(function);

                // Add one Item
                userFunctions.add(userFunctionVo);
            }
        }

        return userFunctions;
    }

    /**
     * 根据传入对象查找属性
     * 
     * @param object
     *            传入对象
     * 
     * @return 属性集合
     *
     */
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        Collection<ConfigAttribute> calist = new ArrayList<ConfigAttribute>();
        calist.add(new SecurityConfig(requestUrl));
        return calist;
    }

    /**
     * 得到所有配置属性集合
     * 
     * @return 属性集合
     *
     */
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