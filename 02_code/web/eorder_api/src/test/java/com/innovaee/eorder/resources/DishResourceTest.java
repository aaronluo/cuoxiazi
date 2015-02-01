
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: DishResourceTest
 * @Description: 菜品资源测试类
 * @version V1.0
 */
public class DishResourceTest extends BaseTestCase {

    /**
     * 根据菜品ID查找菜品
     */
    @Test
    public void getDishById() {
        target = client.resource(SERVER_URI + "/dishes/6");
        String dishVO = target.get(String.class);
        LOGGER.debug("dish: " + dishVO);
    }

}