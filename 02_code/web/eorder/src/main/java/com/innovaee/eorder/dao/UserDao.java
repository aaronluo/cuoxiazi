/***********************************************
 * Filename        : UserDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.User;

/**
 * @Title: UserDao
 * @Description: 用户数据访问对象接口
 *
 * @version V1.0
 */
public interface UserDao extends BaseDao<User> {

    /**
     * 通过用户名查找用户
     * 
     * @param username
     *            用户名
     * @return 用户
     */
    public User findUserByUserName(final String username);

    /**
     * 通过手机号码查找用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User findUserByCellphone(String cellphone);

    /**
     * 通过用户名密码查找用户
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 用户
     */
    public User findUserByUsernameAndPassword(String username, String password);
}
