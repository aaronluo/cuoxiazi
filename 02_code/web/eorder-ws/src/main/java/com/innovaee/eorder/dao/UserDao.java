/***********************************************
 * Filename        : UserDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.User;

import java.util.List;

/**
 * @Title: UserDao
 * @Description: 用户数据访问对象接口
*
 * @version V1.0
 */
public interface UserDao {

    /**
     * 根据用户ID查找用户
     * 
     * @param id
     *            用户ID
     * @return 用户实体
     */
    public User getUserById(String id);

    /**
     * 根据用户ID删除用户
     * 
     * @param id
     *            用户实体
     * @return 用户是否删除成功
     */
    public boolean deleteUserById(String id);

    /**
     * 创建用户
     * 
     * @param category
     *            待创建的用户
     * @return 用户是否创建成功
     */
    public boolean createUser(User user);

    /**
     * 更新用户
     * 
     * @param category
     *            待更新的用户
     * @return 用户是否更新成功
     */
    public boolean updateUser(User user);

    /**
     * 获取所有用户
     * 
     * @return 所有用户列表
     */
    public List<User> getAllUseres();

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(String cellphone);

}