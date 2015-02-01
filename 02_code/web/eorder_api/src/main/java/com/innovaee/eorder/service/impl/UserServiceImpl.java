package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.get(userId);
    }

    @Override
    public User getUserByCellphone(String cellphone) {
        return userDao.getUserByCellphone(cellphone);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}