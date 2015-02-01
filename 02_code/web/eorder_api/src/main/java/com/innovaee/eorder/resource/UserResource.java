/***********************************************
 * Filename        : UserResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.vo.OrderVO;
import com.innovaee.eorder.vo.UserVO;

/**
 * @Title: UserResource
 * @Description: 用户资源
 * @version V1.0
 */
@Path("/users")
public class UserResource {

    private Logger logger = Logger.getLogger(this.getClass());

    /** 用户服务类对象 */
    private UserService userService;

    /**
     * 根据手机号码查询用户信息
     * 
     * @param cellphone
     *            手机号码
     * @return 用户值对象
     */
    @GET
    @Path("/{cellphone}")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, UserVO> getUserByCellphone(
            @PathParam("cellphone") String cellphone) {
        logger.info("[REST_CALL= getUserById, cellphone=" + cellphone + "]");
        User user = userService.getUserByCellphone(cellphone);
        UserLevel userLevel = user.getUserLevel();
        UserVO userVO = new UserVO();
        if (null != user) {
            userVO.setId(user.getId());
            userVO.setUsername(user.getUsername());
            userVO.setCellphone(user.getCellphone());
        }

        if (null != userLevel) {
            // 设置用户等级名称
            userVO.setLevelName(userLevel.getLevelName());
            // 设置用户折扣信息
            userVO.setDiscount(userLevel.getDiscount());
        }

        Map<String, UserVO> result = new HashMap<String, UserVO>();
        result.put("user", userVO);

        return result;
    }

    /**
     * 根据手机号码查询该用户的订单信息
     * 
     * @param cellphone
     *            手机号码
     * @return 订单值对象列表
     */
    @GET
    @Path("/{cellphone}/orders")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<OrderVO>> getOrderesByCellphone(
            @PathParam("cellphone") String cellphone) {
        // 1. 通过手机号码查找用户信息
        User user = userService.getUserByCellphone(cellphone);
        List<OrderVO> orderVOs = new ArrayList<OrderVO>();
        // 2. 根据用户ID查找用户的订单信息
        List<Order> orders = user.getOrders();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            // 将订单对象的信息复制到订单值对象中，用于返回给客户端
            // BeanUtils.copyProperties(orderVO, order);
            orderVO.setId(order.getId());
            orderVO.setTotalPrice(order.getTotalPrice());
            orderVO.setCreateAt(order.getCreateDate());
            orderVOs.add(orderVO);
        }

        // 构造返回Map
        Map<String, List<OrderVO>> result = new HashMap<String, List<OrderVO>>();
        result.put("orders", orderVOs);
        return result;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserResource() {
        super();
    }
}