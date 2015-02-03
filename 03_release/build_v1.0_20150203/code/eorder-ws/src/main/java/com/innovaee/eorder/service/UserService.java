/***********************************************
 * Filename       : UserService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.User;

/**
 * @Title: UserService
 * @Description: 用户服务接口
 * @version V1.0
 */
public interface UserService {

    /**
     * 根据用户ID查找用户
     * 
     * @param id
     *            用户ID
     * @return 用户实体
     */
    public User getUserById(Integer userId);

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(String cellphone);
}