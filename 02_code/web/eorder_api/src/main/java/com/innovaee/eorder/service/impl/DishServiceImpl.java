package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.DishService;

public class DishServiceImpl implements DishService {

    private DishDao dishDao;
    
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
