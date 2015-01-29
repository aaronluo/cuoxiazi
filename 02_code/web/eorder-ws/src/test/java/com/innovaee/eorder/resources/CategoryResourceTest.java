
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: CategoryResourceTest
 * @Description: 分类资源测试类
 * @version V1.0
 */
public class CategoryResourceTest extends BaseTestCase {

    /**
     * 获取所有分类
     */
    @Test
    public void getAllCategories() {
        target = client.target(SERVER_URI + "/categories");
        response = target.request().get();
        String categories = response.readEntity(String.class);
        LOGGER.debug("categories: " + categories);
    }
    
    /**
     * 根据categoryId查询菜品列表
     */
    @Test
    public void getDishesByCategoryId() {
        target = client.target(SERVER_URI + "/categories/6/dishes");
        response = target.request().get();
        String dishes = response.readEntity(String.class);
        LOGGER.debug("dishes: " + dishes);
    }

}