package com.innovaee.eorder.resource;

import com.innovaee.eorder.entity.Dish;
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

@Path("/dishes")
public class DishResource {
    private Logger logger = Logger.getLogger(this.getClass());
    private DishService dishService;

    @GET
    @Path("/{dishId}")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, DishVO> getDishById(@PathParam("dishId") Integer dishId) {
        logger.info("[REST_CALL= getDishById, dishId=" + dishId + "]");
        Dish dish = dishService.getDishById(dishId);
        DishVO dishVO = new DishVO();
        
        if (null != dish) {
            dishVO.setId(dish.getId());
            dishVO.setName(dish.getName());
            dishVO.setPicPath(dish.getPicPath());
            dishVO.setPrice(dish.getPrice());
            dishVO.setOnSell(dish.isOnSell());
        }
        
        Map<String, DishVO> result = new HashMap<String, DishVO>();
        result.put("dish", dishVO);
        
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
