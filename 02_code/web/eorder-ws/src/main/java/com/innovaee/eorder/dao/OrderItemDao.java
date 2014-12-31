/***********************************************
 * Filename        : OrderItemDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.OrderItem;

import java.util.List;

/**
 * @Title: OrderItemDao
 * @Description: 订单明细明细数据访问对象接口
 * @author coderdream@gmail.com
 * @version V1.0
 */
public interface OrderItemDao {

    /**
     * 根据订单明细ID查找订单明细
     * 
     * @param id
     *            订单明细ID
     * @return 订单明细实体
     */
    public OrderItem getOrderItemById(String id);

    /**
     * 根据订单明细ID删除订单明细
     * 
     * @param id
     *            订单明细实体
     * @return 订单明细是否删除成功
     */
    public boolean deleteOrderItemById(String id);

    /**
     * 创建订单明细
     * 
     * @param category
     *            待创建的订单明细
     * @return 订单明细是否创建成功
     */
    public boolean createOrderItem(OrderItem orderItem);

    /**
     * 更新订单明细
     * 
     * @param category
     *            待更新的订单明细
     * @return 订单明细是否更新成功
     */
    public boolean updateOrderItem(OrderItem orderItem);

    /**
     * 获取所有订单明细
     * 
     * @return 所有订单明细列表
     */
    public List<OrderItem> getAllOrderItems();

    /**
     * 根据订单ID得到订单明细列表
     * 
     * @param orderId
     *            订单ID
     * @return 订单明细列表
     */
    public List<OrderItem> getOrderItemsByOrderId(String orderId);
}