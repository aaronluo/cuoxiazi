/***********************************************
 * Filename       : HibernateOrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;

import java.util.List;

/**
 * @Title: HibernateOrderDao
 * @Description: 订单数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateOrderDao extends HibernateBaseDao<Order> implements
        OrderDao {

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(Integer memberId) {
        String hql = "from Order as order where order.member.id = ?";
        Object[] paras = { memberId };
        List<Order> users = getPage(0, 999, hql, paras);
        return users;
    }
}