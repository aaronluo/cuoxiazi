/***********************************************
 * Filename       : OrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.Order;

import java.util.Date;
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
    public List<Order> getOrdersByMemberId(final Long memberId);

    /**
     * 获得指定日期订单最大序号，则新订单以这个序号增1
     * @param date
     * @return 序号最大值，指定日期没有订单产生，则返回0
     */
    public int getMaxOrderSeqofDate(final Date date);
}