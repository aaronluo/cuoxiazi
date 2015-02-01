/***********************************************
 * Filename       : UserDao.java
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
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(String cellphone);

}