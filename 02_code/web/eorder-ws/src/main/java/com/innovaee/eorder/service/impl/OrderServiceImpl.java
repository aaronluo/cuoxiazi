/***********************************************
 * Filename       : OrderServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.dao.OrderItemDao;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.OrderItem;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.exception.ZeroOrderItemException;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.support.Constants;
import com.innovaee.eorder.support.DateUtil;
import com.innovaee.eorder.support.MessageUtil;
import com.innovaee.eorder.vo.NewOrderItemVO;
import com.innovaee.eorder.vo.NewOrderVO;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Title: OrderServiceImpl
 * @Description: 订单服务实现类
 * @version V1.0
 */
public class OrderServiceImpl implements OrderService {

    /** 订单数据访问实现类对象 */
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    /** 菜品数据访问对象 */
    private DishDao dishDao;
    /** 用户数据访问对象 */
    private UserDao userDao;

    /** 类属性setter/getter函数组 */
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public OrderItemDao getOrderItemDao() {
        return orderItemDao;
    }

    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    /**
     * 根据订单ID得到订单
     * 
     * @param orderId
     *            订单ID
     * @return 订单
     */
    @Override
    public Order getOrderById(Long orderId) {
        return orderDao.get(orderId);
    }

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    @Override
    public List<Order> getOrdersByMemberId(Long memberId) {
        return orderDao.getOrdersByMemberId(memberId);
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Long placeOrder(NewOrderVO newOrder) throws UserNotFoundException,
            ZeroOrderItemException, DishNotFoundException {
        // 1. 组合OrderSeq值，格式为yyMMdd00001
        Date currentDay = Calendar.getInstance().getTime();
        Integer maxOrderSeq = orderDao.getMaxOrderSeqofDate(currentDay);
        String curOrderSeq = String
                .format("%s%05d", DateUtil.formatDate(
                        Constants.DATE_FORMAT_YYYYMMDD, currentDay),
                        maxOrderSeq.intValue() + 1);

        // 2. 创建Order对象
        Order order = new Order();
        order.setOrderSeq(curOrderSeq);
        order.setTableNumber(newOrder.getTableNumber());
        order.setAttendeeNumber(newOrder.getAttendeeNumber());
        order.setOrderStatus(Constants.ORDER_NEW);
        order.setCreateDate(currentDay);
        order.setUpdateDate(currentDay);
        User servent = userDao.get(newOrder.getServentId());

        if (null == servent) {
            throw new UserNotFoundException("employeeId: "
                    + newOrder.getServentId());
        } else {
            order.setServent(servent);
        }
        // 下单客户是会员，获取折扣信息
        float discount = 1.0f;
        if (newOrder.getMemberId() > 0) {
            User member = userDao.get(newOrder.getMemberId());
            if (null != member) {
                order.setMember(member);
                discount = member.getMemberShip() == null ? 1.0f : member.getMemberShip().getLevel().getDiscount() / 10f;
            }
        }

        Long orderId = orderDao.save(order);
        order = orderDao.get(orderId);
        // 3. 创建订单详情
        Float totalPrice = 0.0f;
        if (null != newOrder.getItems() && newOrder.getItems().size() > 0) {
           

            for (NewOrderItemVO item : newOrder.getItems()) {
                OrderItem orderItem = new OrderItem();
                Dish dish = dishDao.get(item.getDishId());

                if (null != dish) {
                    orderItem.setDish(dish);
                    orderItem.setDishAmount(item.getDishAmount());
                } else {
                    throw new DishNotFoundException(MessageUtil.getMessage(
                            "dish_id", item.getDishId() + ""));
                }

                orderItem.setCreateDate(currentDay);
                orderItem.setUpdateDate(currentDay);
                orderItem.setOrder(order);
                orderItemDao.save(orderItem);

                totalPrice += dish.getPrice() * orderItem.getDishAmount();

                order.getOrderItems().add(orderItem);
            }

            order.setTotalPrice(totalPrice);
            order.setDiscountPrice(totalPrice * discount);

            orderDao.update(order);
        } else {
            // 当订单中没有菜品明细的时候，抛出异常
            throw new ZeroOrderItemException();
        }

        return orderId;
    }
}