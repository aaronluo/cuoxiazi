/***********************************************
 * Filename       : OrderService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.vo.NewOrderVO;

import java.util.List;

/**
 * @Title: OrderService
 * @Description: 订单服务
 * @version V1.0
 */
public interface OrderService {

    /**
     * 根据订单ID得到订单
     * 
     * @param orderId
     *            订单ID
     * @return 订单
     */
    public Order getOrderById(Long orderId);

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(Long memberId);
    
    /**
     * 创建新订单
     * 
     * @param newOrder
     *            新订单信息
     * @return 如果创建成功，返回新订单id；如果失败，返回-1
     * @throws UserNotFoundException 
     * @throws ZeroOrderItemException 
     * @throws DishNotFoundException 
     */  
    public Long placeOrder(NewOrderVO newOrder) throws UserNotFoundException,  DishNotFoundException, com.innovaee.eorder.exception.ZeroOrderItemException;
}