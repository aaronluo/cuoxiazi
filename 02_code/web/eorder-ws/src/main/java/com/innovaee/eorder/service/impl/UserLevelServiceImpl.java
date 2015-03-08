/***********************************************
 * Filename       : UserLevelServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.UserLevelService;

/**
 * @Title: UserLevelServiceImpl
 * @Description: 用户等级服务实现类
 * @version V1.0
 */
public class UserLevelServiceImpl implements UserLevelService {

    /** 用户数据访问实现类对象 */
    private UserLevelDao userLevelDao;

    /**
     * 根据用户等级ID查找用户等级
     * 
     * @param id
     *            用户等级ID
     * @return 用户等级实体
     */
    @Override
    public UserLevel getUserLevelById(Long userLevelId) {
        return userLevelDao.get(userLevelId);
    }

    public void setUserLevelDao(UserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }

    public UserLevelDao getUserLevelDao() {
        return userLevelDao;
    }
}