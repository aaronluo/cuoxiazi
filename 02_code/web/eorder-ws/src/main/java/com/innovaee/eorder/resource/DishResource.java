/***********************************************
 * Filename        : DishResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.DishService;
import com.innovaee.eorder.vo.DishVO;

/**
 * @Title: DishResource
 * @Description: 菜品资源
 * @version V1.0
 */
@Path("/dishes")
public class DishResource extends AbstractBaseResource {

    /** 菜品服务类对象 */
    private DishService dishService = new DishService();

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     */
    @GET
    @Path("/{dishId}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, DishVO> getDishById(@PathParam("dishId") Integer dishId) {
        Dish dish = dishService.getDishById(dishId);

        DishVO dishVO = new DishVO();
        try {
            // 将菜品对象的信息复制到菜品值对象中，用于返回给客户端
            BeanUtils.copyProperties(dishVO, dish);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }

        Map<String, DishVO> result = new HashMap<String, DishVO>();
        result.put("dishes", dishVO);

        return result;
    }

}