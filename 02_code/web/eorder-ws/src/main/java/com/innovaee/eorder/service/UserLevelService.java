/***********************************************
 * Filename       : UserLevelService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

/**
 * @Title: UserLevelService
 * @Description: 用户等级服务接口
 * @version V1.0
 */
import com.innovaee.eorder.entity.UserLevel;

public interface UserLevelService {

    /**
     * 根据用户等级ID查找用户等级
     * 
     * @param id
     *            用户等级ID
     * @return 用户等级实体
     */
    public UserLevel getUserLevelById(Long userLevelId);
}