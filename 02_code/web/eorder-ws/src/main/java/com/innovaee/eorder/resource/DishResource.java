/***********************************************
 * Filename       : DishResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.resource;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.service.DishService;
import com.innovaee.eorder.vo.DishVO;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @Title: DishResource
 * @Description: 菜品资源
 * @version V1.0
 */
@Path("/dishes")
public class DishResource {
    private Logger logger = Logger.getLogger(this.getClass());

    /** 菜品服务类对象 */
    private DishService dishService;

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     */
    @GET
    @Path("/{dishId}")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getDishById(@PathParam("dishId") Long dishId) {
        logger.info("[REST_CALL= getDishById, dishId=" + dishId + "]");
        
        Map<String, Object> result = new HashMap<String, Object>();
        Dish dish = null;

        try {
            dish = dishService.getDishById(dishId);
        } catch (DishNotFoundException exception) {
            result.put("exception", exception.getMessage());
        }

        if (null != dish) {
            DishVO dishVO = new DishVO();
            dishVO.setId(dish.getId());
            dishVO.setName(dish.getName());
            dishVO.setPicPath(dish.getPicPath());
            dishVO.setPrice(dish.getPrice());
            dishVO.setOnSell(dish.isOnSell());
            result.put("dish", dishVO);
        }

        return result;
    }

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public DishResource() {
        super();
    }
}
