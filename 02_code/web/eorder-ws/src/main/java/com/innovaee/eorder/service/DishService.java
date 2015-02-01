/***********************************************
 * Filename       : DishService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Dish;

/**
 * @Title: DishService
 * @Description: 菜品服务
 * @version V1.0
 */
public class DishService extends BaseService {

    /** 菜品数据访问实现类对象 */
    private DishDao dishDao = new DishDao();

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     */
    public Dish getDishById(Integer dishId) {
        return dishDao.getDishById(dishId);
    }

}