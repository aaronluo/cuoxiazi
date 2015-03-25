/***********************************************
 * Filename       : UserLevelService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.UserLevel;

/**
 * @Title: UserLevelService
 * @Description: 用户等级服务接口
 *
 * @version V1.0
 */
public interface UserLevelService {

    /**
     * 根据用户ID查找用户信息
     * 
     * @param userLevelId
     *            用户的等级ID
     * @return 用户等级
     */
    public UserLevel loadUserLevel(Long userLevelId);

}