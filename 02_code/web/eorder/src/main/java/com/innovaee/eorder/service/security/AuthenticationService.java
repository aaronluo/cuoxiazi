/***********************************************
 * Filename        : AuthenticationService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.security;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.UserFunctionVO;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: AuthenticationService
 * @Description: 验证服务
 *
 * @version V1.0
 */
public class AuthenticationService implements UserDetailsService {

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
        return null != userDao
                .findUserByUsernameAndPassword(username, password);
    }

    /**
     * 根据用户名称查找用户详细信息
     * 
     * @param username
     *            用户名
     * @return 用户详细信息
     */
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        List<UserFunctionVO> userFunctions = securityMetadataSourceService
                .getUserFunctions(username);
        if (null == userFunctions || 0 == userFunctions.size()) {
            throw new UsernameNotFoundException("用户[" + username + "] 不存在!");
        }
        EOrderUserDetailVO userDetailsVo = new EOrderUserDetailVO();
        userDetailsVo.setUserFunctions(userFunctions);

        return userDetailsVo;
    }
}