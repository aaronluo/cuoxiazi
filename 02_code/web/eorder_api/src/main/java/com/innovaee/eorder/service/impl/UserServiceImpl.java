package com.innovaee.eorder.service.impl;

import java.util.List;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.model.User;
import com.innovaee.eorder.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.get(userId);
    }

    @Override
    public User getUserByCellphone(String cellphone) {
        String hql = "from User as user where user.cellphone = ?";
        Object[] paras = { cellphone };
        List<User> users = userDao.getPage(0, 5, hql, paras);
        if (0 != users.size()) {
            return users.get(0);
        }

        return null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}