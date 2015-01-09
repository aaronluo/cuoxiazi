/***********************************************
 * Filename       : UserService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.User;

/**
 * @Title: UserService
 * @Description: 用户服务
 * @version V1.0
 */
public class UserService extends BaseService {

    /** 用户数据访问实现类对象 */
    private UserDao userDao = new UserDao();

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(String cellphone) {
        return userDao.getUserByCellphone(cellphone);
    }

}