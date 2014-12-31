/***********************************************
 * Filename        : OrderResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resources;

import com.innovaee.eorder.bean.Order;
import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.dao.impl.OrderDaoImpl;
import com.innovaee.eorder.dao.impl.UserDaoImpl;
import com.innovaee.eorder.vo.OrderVO;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @Title: OrderResource
 * @Description: 订单资源
 * @author coderdream@gmail.com
 * @version V1.0
 */
@Path("/orders")
public class OrderResource {
    private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();

    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    /**
     * 根据手机号码查询该用户的订单信息
     * 
     * @param cellphone
     *            手机号码
     * @return 订单值对象
     */
    @GET
    @Path("/myorders/{cellphone}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<OrderVO>> getOrderesById(
            @PathParam("cellphone") String cellphone) {
        User user = userDaoImpl.getUserByCellphone(cellphone);
        List<OrderVO> orderVOs = new ArrayList<OrderVO>();
        List<Order> orders = orderDaoImpl.getOrdersByMemberId(user.getUserId()
                .toString());
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            try {
                BeanUtils.copyProperties(orderVO, order);
                orderVOs.add(orderVO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        Map<String, List<OrderVO>> result = new HashMap<String, List<OrderVO>>();
        result.put("orders", orderVOs);
        return result;
    }

}