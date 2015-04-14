/***********************************************
 * Filename       : OrderServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.dao.OrderItemDao;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.entity.MemberShip;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.OrderItem;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.OrderNotFoundException;
import com.innovaee.eorder.exception.OrderOperationException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.exception.ZeroOrderItemException;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.support.MessageUtil;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.DateUtil;
import com.innovaee.eorder.vo.NewOrderItemVO;
import com.innovaee.eorder.vo.NewOrderVO;

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
     * @throws OrderNotFoundException
     */
    @Override
    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        Order order = orderDao.get(orderId);

        if (order == null) {
            throw new OrderNotFoundException(MessageUtil.getMessage("order_id",
                    "" + orderId));
        }

        return order;
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
            throw new UserNotFoundException(MessageUtil.getMessage(
                    "employee_id", "" + newOrder.getServentId()));
        } else {
            order.setServent(servent);
        }
        // 下单客户是会员，获取折扣信息
        float discount = 1.0f;
        if (null != newOrder.getMemberId() && newOrder.getMemberId() > 0) {
            User member = userDao.get(newOrder.getMemberId());
            if (null != member) {
                order.setMember(member);
                discount = member.getMemberShip() == null ? 1.0f : member
                        .getMemberShip().getLevel().getDiscount() / 10f;
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

    /**
     * 返回桌号和当前订单的关联关系，如果当前桌号有订单， 则在Map中取得Order；反之，则为NULL
     * 这里设定一共有20个桌子，所以Map的key值是从1到20
     * 
     * @return
     */
    @Override
    public Map<Integer, Order> getTableStatus() {
        Map<Integer, Order> result = new HashMap<Integer, Order>();

        // 1. 获取状态为New的订单
        NewOrderVO orderCriteria = new NewOrderVO();
        orderCriteria.setStatus(Constants.ORDER_NEW);

        List<Order> orders = orderDao
                .query(orderCriteria, 1, Integer.MAX_VALUE);
        // 2. 进行桌号对比
        for (int tableNum = 1; tableNum <= 20; tableNum++) {
            boolean foundOrder = false;
            for (Order order : orders) {
                if (tableNum == order.getTableNumber()) {
                    result.put(tableNum, order);
                    foundOrder = true;
                    break;
                }
            }

            if (!foundOrder) {
                result.put(tableNum, null);
            }

        }

        return result;
    }

    /**
     * 根据查询条件查找订单
     * 
     * @param orderCriteria
     * @param curPage
     * @param pageSize
     * @return 返回订单分页数据，这里有一个issue就是订单的明细没有获取， 需要再次调用getOrderById来获取完备的明细
     * @throws InvalidPageSizeException
     * @throws PageIndexOutOfBoundExcpeiton
     */
    @Override
    public List<Order> queryOrders(NewOrderVO orderCriteria, int curPage,
            int pageSize) throws InvalidPageSizeException,
            PageIndexOutOfBoundExcpeiton {
        int pageCount = this.queryOrdersPageCount(orderCriteria, pageSize);

        if (curPage < 1 || curPage > pageCount) {
            throw new PageIndexOutOfBoundExcpeiton(pageCount, curPage);
        }

        List<Order> orders = orderDao.query(orderCriteria, curPage, pageSize);

        return orders;
    }

    /**
     * 根据查询条件查找订单分页总数
     * 
     * @param orderCriteria
     * @param pageSize
     * @return
     * @throws InvalidPageSizeException
     */
    @Override
    public int queryOrdersPageCount(NewOrderVO orderCriteria, int pageSize)
            throws InvalidPageSizeException {

        if (pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }

        int orderCount = this.getOrderCount(orderCriteria);

        return orderCount % pageSize == 0 ? orderCount / pageSize : orderCount
                / pageSize + 1;
    }

    /**
     * 根据查询条件查找订单总数
     * 
     * @param orderCriteria
     * @return
     */
    @Override
    public int getOrderCount(NewOrderVO orderCriteria) {
        int orderCount = 0;

        if (null == orderCriteria || orderCriteria.isEmpty()) {
            orderCount = orderDao.count();
        } else {
            orderCount = orderDao.count(orderCriteria);
        }

        return orderCount;
    }

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
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Order payTheOrder(Long orderId, Long casherId)
            throws OrderNotFoundException, UserNotFoundException,
            OrderOperationException {
        Order order = this.getOrderById(orderId);

        // 1. 判断Order是否为非Paid状态
        if (order.getOrderStatus() == Constants.ORDER_PAID) {
            throw new OrderOperationException(MessageUtil.getMessage(
                    "can_not_proceed_payment", order.getOrderSeq()));
        }

        User casher = userDao.get(casherId);
        if (null == casher) {
            throw new UserNotFoundException(MessageUtil.getMessage("user_id",
                    "" + casherId));
        }

        order.setCasher(casher);
        order.setOrderStatus(Constants.ORDER_PAID);
        order.setUpdateDate(new Date());
        orderDao.update(order);

        return order;
    }

    /**
     * 更新订单信息，这里比较简洁的处理订单的更新，只能更新订单的状态，收银员和会员信息
     * 
     * @param order
     *            返回更新后的Order实体
     * @return
     * @throws OrderNotFoundException
     */
    @Override
    public Order updateOrder(NewOrderVO orderVO) throws OrderNotFoundException {
        Order order = orderDao.get(orderVO.getId());

        if (null == order) {
            throw new OrderNotFoundException(MessageUtil.getMessage("order_id",
                    "" + orderVO.getId()));
        }

        order.setOrderStatus(orderVO.getStatus());
        order.setCasher(userDao.get(orderVO.getCashierId()));
        User member = userDao.get(orderVO.getMemberId());
        if (null != member) {
            order.setMember(member);
            MemberShip memberShip = member.getMemberShip();
            if (null != memberShip) {
                order.getMember().setMemberShip(memberShip);
                UserLevel level = memberShip.getLevel();
                if (null != level) {
                    order.getMember().getMemberShip().setLevel(level);
                    order.setDiscountPrice(order.getTotalPrice()
                            * level.getDiscount());
                }
            }
        }
        order.setUpdateDate(new Date());
        orderDao.update(order);

        return order;
    }
}