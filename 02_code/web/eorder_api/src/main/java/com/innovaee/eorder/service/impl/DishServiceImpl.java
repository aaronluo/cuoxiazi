/***********************************************
 * Filename       : DishServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.DishService;

/**
 * @Title: DishServiceImpl
 * @Description: 菜品服务实现类
 * @version V1.0
 */
public class DishServiceImpl implements DishService {

    /** 菜品数据访问实现类对象 */
    private DishDao dishDao;

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     */
    @Override
    public Dish getDishById(Integer dishId) {
        return dishDao.get(dishId);
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public DishDao getDishDao() {
        return dishDao;
    }
}
