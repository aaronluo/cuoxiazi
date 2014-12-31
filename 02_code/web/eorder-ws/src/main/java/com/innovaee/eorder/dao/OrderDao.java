/***********************************************
 * Filename        : OrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.Order;

import java.util.List;

/**
 * @Title: OrderDao
 * @Description: 订单数据访问对象接口
 * @author coderdream@gmail.com
 * @version V1.0
 */
public interface OrderDao {

    /**
     * 根据订单ID查找订单
     * 
     * @param id
     *            订单ID
     * @return 订单实体
     */
    public Order getOrderById(String id);

    /**
     * 根据订单ID删除订单
     * 
     * @param id
     *            订单实体
     * @return 订单是否删除成功
     */
    public boolean deleteOrderById(String id);

    /**
     * 创建订单
     * 
     * @param category
     *            待创建的订单
     * @return 订单是否创建成功
     */
    public boolean createOrder(Order order);

    /**
     * 更新订单
     * 
     * @param category
     *            待更新的订单
     * @return 订单是否更新成功
     */
    public boolean updateOrder(Order order);

    /**
     * 获取所有订单
     * 
     * @return 所有订单列表
     */
    public List<Order> getAllOrders();

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(String memberId);

}