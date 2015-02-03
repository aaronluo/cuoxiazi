/***********************************************
 * Filename       : OrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.Order;

import java.util.List;

/**
 * @Title: OrderDao
 * @Description: 订单数据访问对象接口
 * 
 * @version V1.0
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(Integer memberId);
}