/***********************************************
 * Filename        : OrderItemResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.entity.OrderItem;
import com.innovaee.eorder.service.DishService;
import com.innovaee.eorder.service.OrderItemService;
import com.innovaee.eorder.vo.OrderItemVO;

/**
 * @Title: OrderItemResource
 * @Description: 订单明细资源
 * @version V1.0
 */
@Path("/orderitems")
public class OrderItemResource extends AbstractBaseResource {

    /** 订单明细服务类对象 */
    private OrderItemService orderItemService = new OrderItemService();

    /** 菜品服务类对象 */
    private DishService dishService = new DishService();

    /**
     * 根据orderId查询订单明细
     * 
     * @param orderId
     *            订单ID
     * @return 订单明细
     */
    @GET
    @Path("/myorderitems/{orderId}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<OrderItemVO>> getOrderItemsByOrderId(
            @PathParam("orderId") Integer orderId) {
        List<OrderItemVO> orderItemVOs = new ArrayList<OrderItemVO>();
        Dish dish = null;
        OrderItemVO orderItemVO = null;
        List<OrderItem> orderItems = orderItemService
                .getOrderItemsByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {

            orderItemVO = new OrderItemVO();
            try {
                BeanUtils.copyProperties(orderItemVO, orderItem);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage());
            }

            if (null != orderItem) {
                dish = dishService.getDishById(orderItem.getDishId());
                orderItemVO.setDishName(dish.getDishName());
                orderItemVO.setDishPicture(dish.getDishPicture());
                orderItemVO.setDishPrice(dish.getDishPrice());
                orderItemVOs.add(orderItemVO);
            }
        }

        Map<String, List<OrderItemVO>> result = new HashMap<String, List<OrderItemVO>>();
        result.put("orderitems", orderItemVOs);

        return result;
    }

}