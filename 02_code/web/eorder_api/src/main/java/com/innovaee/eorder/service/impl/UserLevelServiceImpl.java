package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.model.UserLevel;
import com.innovaee.eorder.service.UserLevelService;

public class UserLevelServiceImpl implements UserLevelService {

    private UserLevelDao userLevelDao;

    public void setUserLevelDao(UserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }

    public UserLevelDao getUserLevelDao() {
        return userLevelDao;
    }

    @Override
    public UserLevel getUserLevelById(Integer userLevelId) {
        return userLevelDao.get(userLevelId);
    }
}