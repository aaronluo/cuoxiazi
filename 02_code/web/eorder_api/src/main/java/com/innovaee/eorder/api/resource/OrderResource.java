package com.innovaee.eorder.api.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.innovaee.eorder.model.Order;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.vo.OrderVO;

@Path("/orderes")
public class OrderResource {
    private Logger logger = Logger.getLogger(this.getClass());
    private OrderService orderService;

    @GET
    @Path("/{orderId}")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, OrderVO> getOrderById(
            @PathParam("orderId") Integer orderId) {
        logger.info("[REST_CALL= getOrderById, orderId=" + orderId + "]");
        Order order = orderService.getOrderById(orderId);
        OrderVO orderVO = new OrderVO();

        if (null != order) {
            orderVO.setId(order.getId());
            orderVO.setTotalPrice(order.getTotalPrice());
            orderVO.setCreateAt(order.getCreateDate());
        }

        Map<String, OrderVO> result = new HashMap<String, OrderVO>();
        result.put("order", orderVO);

        return result;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResource() {
        super();
    }
}