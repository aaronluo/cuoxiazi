/***********************************************
 * Filename        : UserResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

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

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.service.UserLevelService;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.vo.OrderVO;
import com.innovaee.eorder.vo.UserVO;

/**
 * @Title: UserResource
 * @Description: 用户资源
 * @version V1.0
 */
@Path("/users")
public class UserResource extends AbstractBaseResource {

    /** 用户服务类对象 */
    private UserService userService = new UserService();

    /** 用户等级服务类对象 */
    private UserLevelService userLevelService = new UserLevelService();

    /** 订单服务类对象 */
    private OrderService orderService = new OrderService();

    /**
     * 根据手机号码查询用户信息
     * 
     * @param cellphone
     *            手机号码
     * @return 用户值对象
     */
    @GET
    @Path("/{cellphone}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, UserVO> getUserById(
            @PathParam("cellphone") String cellphone) {
        User user = userService.getUserByCellphone(cellphone);
        UserVO userVO = new UserVO();

        if (null != user) {
            try {
                BeanUtils.copyProperties(userVO, user);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage());
            }
            UserLevel userLevel = null;
            userLevel = userLevelService.getUserLevelById(user.getLevelId()
                    .toString());
            userVO.setDiscount(userLevel.getDiscount());
            userVO.setLevelName(userLevel.getLevelName());
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
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<OrderVO>> getOrderesByCellphone(
            @PathParam("cellphone") String cellphone) {
        // 1. 通过手机号码查找用户信息
        User user = userService.getUserByCellphone(cellphone);
        List<OrderVO> orderVOs = new ArrayList<OrderVO>();
        // 2. 根据用户ID查找用户的订单信息
        List<Order> orders = orderService.getOrdersByMemberId(user.getUserId());
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            try {
                // 将订单对象的信息复制到订单值对象中，用于返回给客户端
                BeanUtils.copyProperties(orderVO, order);
                orderVOs.add(orderVO);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage());
            }
        }

        // 构造返回Map
        Map<String, List<OrderVO>> result = new HashMap<String, List<OrderVO>>();
        result.put("orders", orderVOs);
        return result;
    }

}