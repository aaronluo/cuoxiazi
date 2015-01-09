/***********************************************
 * Filename       : UserLevelService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.entity.UserLevel;

/**
 * @Title: UserLevelService
 * @Description: 用户等级服务
 * @version V1.0
 */
public class UserLevelService extends BaseService {

    /** 用户数据访问实现类对象 */
    private UserLevelDao userLevelDao = new UserLevelDao();

    /**
     * 根据用户等级ID查找用户等级
     * 
     * @param id
     *            用户等级ID
     * @return 用户等级实体
     */
    public UserLevel getUserLevelById(String id) {
        return userLevelDao.getUserLevelById(id);
    }
}