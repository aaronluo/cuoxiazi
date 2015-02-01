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

/**
 * @Title: HibernateUserDao
 * @Description: 用户数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateUserDao extends HibernateBaseDao<User> implements UserDao {

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(String cellphone) {
        String hql = "from User as user where user.cellphone = ?";
        Object[] paras = { cellphone };
        List<User> users = getPage(0, 1, hql, paras);
        if (0 != users.size()) {
            return users.get(0);
        }

        return null;
    }

}