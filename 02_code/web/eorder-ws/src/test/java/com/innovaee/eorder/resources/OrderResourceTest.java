
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: OrderResourceTest
 * @Description: 订单资源测试类
 * @version V1.0
 */
public class OrderResourceTest extends BaseTestCase {

    /**
     * 根据手机号码查询该用户的订单信息
     */
    @Test
    public void getOrderesById() {
        target = client.target(SERVER_URI + "/orderitems/myorderitems/1");
        response = target.request().get();
        String orders = response.readEntity(String.class);
        LOGGER.debug("orders: " + orders);
    }

}