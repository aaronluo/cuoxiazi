
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: OrderItemResourceTest
 * @Description: 订单明细资源测试类
 * @version V1.0
 */
public class OrderItemResourceTest extends BaseTestCase {

    /**
     * 
     */
    @Test
    public void getOrderItemsByOrderId() {
        target = client.target(SERVER_URI + "/orders/myorders/13912340003");
        response = target.request().get();
        String orderitems = response.readEntity(String.class);
        LOGGER.debug("orderitems: " + orderitems);
    }

}