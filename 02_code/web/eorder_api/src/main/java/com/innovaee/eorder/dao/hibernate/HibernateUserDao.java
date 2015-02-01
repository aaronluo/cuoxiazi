package com.innovaee.eorder.dao.hibernate;

import java.util.List;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.User;

public class HibernateUserDao extends HibernateBaseDao<User> implements UserDao {

    public User getUserByCellphone(String cellphone) {
        String hql = "from User as user where user.cellphone = ?";
        Object[] paras = { cellphone };
        List<User> users = getPage(0, 5, hql, paras);
        if (0 != users.size()) {
            return users.get(0);
        }

        return null;
    }

}