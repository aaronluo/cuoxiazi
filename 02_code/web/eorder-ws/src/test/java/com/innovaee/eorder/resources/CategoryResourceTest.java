
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: CategoryResourceTest
 * @Description: 分类资源测试类
 * @version V1.0
 */
public class CategoryResourceTest extends BaseTestCase {

    @Test
    public void getAllCategories() {
        target = client.target(SERVER_URI + "/categories");
        response = target.request().get();
        String categories = response.readEntity(String.class);
        LOGGER.debug("categories: " + categories);
    }

}