/***********************************************
 * Filename        : OrderResource.java
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

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.vo.OrderVO;

/**
 * @Title: OrderResource
 * @Description: 订单资源
 * @version V1.0
 */
@Path("/orders")
public class OrderResource extends AbstractBaseResource {

    /** 订单数据访问实现类对象 */
    private OrderService orderService = new OrderService();

    /** 用户数据访问实现类对象 */
    private UserService userService = new UserService();

    /**
     * 根据手机号码查询该用户的订单信息
     * 
     * @param cellphone
     *            手机号码
     * @return 订单值对象
     */
    @GET
    @Path("/myorders/{cellphone}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<OrderVO>> getOrderesById(
            @PathParam("cellphone") String cellphone) {
        // 1. 通过手机号码查找用户信息
        User user = userService.getUserByCellphone(cellphone);
        List<OrderVO> orderVOs = new ArrayList<OrderVO>();
        // 2. 根据用户ID查找用户的订单信息
        List<Order> orders = orderService.getOrdersByMemberId(user.getUserId());
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            try {
                // 将用户对象的信息复制到用户值对象中，用于返回给客户端
                BeanUtils.copyProperties(orderVO, order);
                orderVOs.add(orderVO);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage());
            }
        }

        // 构造返回Map
        Map<String, List<OrderVO>> result = new HashMap<String, List<OrderVO>>();
        result.put("orders", orderVOs);
        return result;
    }

}