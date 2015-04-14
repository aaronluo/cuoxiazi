/***********************************************
 * Filename        : OrderServiceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/27/2015
 ************************************************/

package com.innovaee.eorder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.OrderNotFoundException;
import com.innovaee.eorder.exception.OrderOperationException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.vo.NewOrderVO;

/**
 * @Title: OrderServiceTest
 * @Description: 订单服务测试类
 * 
 * @version V1.0
 */
public class OrderServiceTest extends BaseSpringTestCase {

    @Resource
    private OrderService orderService;

    // @Test
    public void orderCountTest() {
        // 测试无任何查询条件的订单总数
        assertEquals(orderService.getOrderCount(null), 12);
        // 订单序号查询
        String partOrderSeq = "20150328";
        NewOrderVO orderCriteria = new NewOrderVO();
        orderCriteria.setOrderSeq(partOrderSeq);
        assertEquals(10, orderService.getOrderCount(orderCriteria));

        long serventId = 1l;
        orderCriteria.setServentId(serventId);
        assertEquals(2, orderService.getOrderCount(orderCriteria));
        orderCriteria.setOrderSeq(null);
        assertEquals(4, orderService.getOrderCount(orderCriteria));

        long memberId = 1l;
        orderCriteria.setMemberId(memberId);
        assertEquals(2, orderService.getOrderCount(orderCriteria));

        float minTotalPrice = 0.0f;
        float maxTotalPrice = 100.0f;

        orderCriteria.setTotalPriceMin(minTotalPrice);
        orderCriteria.setTotalPriceMax(maxTotalPrice);

        assertEquals(2, orderService.getOrderCount(orderCriteria));
        orderCriteria.setTotalPriceMax(minTotalPrice);
        assertEquals(2, orderService.getOrderCount(orderCriteria));

        orderCriteria = new NewOrderVO();

        orderCriteria.setStatus(Constants.ORDER_PAID);
        assertEquals(0, orderService.getOrderCount(orderCriteria));

        orderCriteria.setStatus(Constants.ORDER_NEW);
        assertEquals(10, orderService.getOrderCount(orderCriteria));
    }

    /**
     * 单元测试 - 查询Order分页测试
     * 
     * @throws InvalidPageSizeException
     * @throws PageIndexOutOfBoundExcpeiton
     */
    @Test
    public void queryOrderByPageTest() throws InvalidPageSizeException,
            PageIndexOutOfBoundExcpeiton {
        int curPage = 1;
        int pageSize = 5;

        // List<Order> orders = orderService.queryOrders(null, curPage,
        // pageSize);
        //
        // assertNotNull(orders);
        // assertTrue(12L == orders.get(0).getId());
        // assertEquals("2015032800010", orders.get(0).getOrderSeq());

        NewOrderVO orderCriteria = new NewOrderVO();

        orderCriteria.setMemberId(1L);

        List<Order> orders = orderService.queryOrders(orderCriteria, curPage,
                pageSize);

        assertNotNull(orders);
        assertEquals(5, orders.size());
        assertEquals("钻石会员", orders.get(0).getMember().getMemberShip()
                .getLevel().getLevelName());
    }

    /**
     * 单元测试 - 获取台桌和订单关系
     */
    @Test
    public void getTableStatusTest() {
        Map<Integer, Order> tables = orderService.getTableStatus();

        assertNotNull(tables);
    }

    /**
     * 单元测试 - 买单
     * 
     * @throws UserNotFoundException
     * @throws OrderNotFoundException
     * @throws OrderOperationException
     */
    @Test
    public void payTheOrderTest() throws OrderNotFoundException,
            UserNotFoundException, OrderOperationException {
        Order order = orderService.payTheOrder(12L, 1L);

        assertNotNull(order);

        assertEquals(order.getOrderStatus(), Constants.ORDER_PAID);
    }
}
