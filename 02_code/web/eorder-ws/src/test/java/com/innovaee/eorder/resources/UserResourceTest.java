
package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: UserResourceTest
 * @Description: 用户资源测试类
 * 
 * @version V1.0
 */
public class UserResourceTest extends BaseTestCase {

    /**
     * 根据手机号码查询用户信息
     */
    @Test
    public void getUseresById() {
        String cellphone = "13912345671";
        target = client.target(SERVER_URI + "/users/myuser/" + cellphone);
        response = target.request().get();
        String user = response.readEntity(String.class);
        LOGGER.debug("user: " + user);
    }

}