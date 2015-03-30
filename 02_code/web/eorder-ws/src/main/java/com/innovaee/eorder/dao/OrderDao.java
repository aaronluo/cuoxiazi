/***********************************************
 * Filename       : OrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.vo.NewOrderVO;

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
     * 
     * @param date
     * @return 序号最大值，指定日期没有订单产生，则返回0
     */
    public int getMaxOrderSeqofDate(final Date date);
    /**
     * 查询符合查询条件的订单总数
     * 
     * @param orderCriteria
     *            查询条件，包括：
     *            1. 订单序号 - like查询
     *            2. 服务员ID
     *            3. 收银员ID
     *            4. 会员ID
     *            5. 创建时间
     *            6. 订单状态
     *            7. 订单总价
     * @return 查询记录总数
     */
    public int count(final NewOrderVO orderCriteria);

    /**
     * 查询符合查询条件的订单分页数据
     * @param orderCriteria
     *            查询条件，包括：
     *            1. 订单序号 - like查询
     *            2. 服务员ID
     *            3. 收银员ID
     *            4. 会员ID
     *            5. 创建时间
     *            6. 订单状态
     *            7. 订单总价
     * @param curPage 当前页
     * @param pageSize 分页大小
     * @return
     */
    public List<Order> query(final NewOrderVO orderCriteria, final int curPage, final int pageSize);
}