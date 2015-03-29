/***********************************************
 * Filename       : HibernateUserDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import java.util.List;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.utils.Constants;

/**
 * @Title: HibernateUserDao
 * @Description: 用户数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateUserDao extends HibernateBaseDao<User> implements UserDao {

    /**
     * 通过用户名查找用户
     * 
     * @param username
     *            用户名
     * @return 用户
     */
    public User findUserByUserName(final String username) {
        final String hql = "from User as user where user.username = ?";
        Object[] paras = { username };
        List<User> users = getPage(0, Constants.MAX_RECORD, hql, paras);

        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 通过手机号码查找用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User findUserByCellphone(String cellphone) {
        final String hql = "from User as user where user.cellphone = ?";
        Object[] paras = { cellphone };
        List<User> users = getPage(0, Constants.MAX_RECORD, hql, paras);

        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 通过用户名密码查找用户
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 用户
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        final String hql = "from User as user where user.username = ? and user.password = ?";
        Object[] paras = { username, password };
        List<User> users = getPage(0, Constants.MAX_RECORD, hql, paras);

        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}