/***********************************************
 * Filename        : UserService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.impl;

import javax.annotation.Resource;

import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.UserLevelService;

/**
 * @Title: UserService
 * @Description: 用户服务
 *
 * @version V1.0
 */
public class UserLevelServiceImpl implements UserLevelService {

    /** 用户数据访问对象 */
    @Resource
    private UserLevelDao userLevelDao;

     /**
     * 根据用户ID查找用户信息
     * 
     * @param userId
     *            用户ID
     * @return 用户
     */
    public UserLevel loadUserLevel(Long userLevelId) {
        return (UserLevel) userLevelDao.get(userLevelId);
    }

}