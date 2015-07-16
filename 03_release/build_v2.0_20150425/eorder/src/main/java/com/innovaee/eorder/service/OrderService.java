/***********************************************
 * Filename       : OrderService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import java.util.List;
import java.util.Map;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.OrderItem;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.OrderNotFoundException;
import com.innovaee.eorder.exception.OrderOperationException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.exception.ZeroOrderItemException;
import com.innovaee.eorder.vo.NewOrderVO;

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
     * @throws OrderNotFoundException
     */
    public Order getOrderById(Long orderId) throws OrderNotFoundException;

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
    public Long placeOrder(NewOrderVO newOrder) throws UserNotFoundException,
            DishNotFoundException,
            com.innovaee.eorder.exception.ZeroOrderItemException;

    /**
     * 返回桌号和当前订单的关联关系，如果当前桌号有订单， 则在Map中取得Order；反之，则为NULL
     * 
     * @return
     */
    public Map<Integer, Order> getTableStatus();

    /**
     * 根据查询条件查找订单
     * 
     * @param orderCriteria
     * @param curPage
     * @param pageSize
     * @return
     * @throws InvalidPageSizeException
     * @throws PageIndexOutOfBoundExcpeiton
     */
    public List<Order> queryOrders(final NewOrderVO orderCriteria,
            final int curPage, final int pageSize)
            throws InvalidPageSizeException, PageIndexOutOfBoundExcpeiton;

    /**
     * 根据查询条件查找订单明细
     * 
     * @param order
     * @param curPage
     * @param pageSize
     * @return 返回订单明细分页数据
     * @throws InvalidPageSizeException
     * @throws PageIndexOutOfBoundExcpeiton
     */
    public List<OrderItem> queryOrderItems(Order order, int curPage,
            int pageSize) throws InvalidPageSizeException,
            PageIndexOutOfBoundExcpeiton;

    /**
     * 根据查询条件查找订单分页总数
     * 
     * @param orderCriteria
     * @param pageSize
     * @return
     * @throws InvalidPageSizeException
     */
    public int queryOrdersPageCount(final NewOrderVO orderCriteria,
            final int pageSize) throws InvalidPageSizeException;

    /**
     * 根据查询条件查找订单总数
     * 
     * @param orderCriteria
     * @return
     */
    public int getOrderCount(final NewOrderVO orderCriteria);

    /**
     * 买单
     * 
     * @param orderId
     *            - 订单id
     * @param casherId
     *            - 收银员id
     * @return 更新过结帐状态的订单
     * @throws OrderNotFoundException
     *             订单未找到异常
     * @throws UserNotFoundException
     *             用户未找到异常
     * @throws OrderOperationException
     */
    public Order payTheOrder(Long orderId, Long casherId)
            throws OrderNotFoundException, UserNotFoundException,
            OrderOperationException;

    /**
     * 更新订单信息，这里比较简洁的处理订单的更新，只能更新订单的状态，收银员和会员信息
     * 
     * @param order
     *            返回更新后的Order实体
     * @return
     * @throws OrderNotFoundException
     */
    public Order updateOrder(NewOrderVO order) throws OrderNotFoundException;
}