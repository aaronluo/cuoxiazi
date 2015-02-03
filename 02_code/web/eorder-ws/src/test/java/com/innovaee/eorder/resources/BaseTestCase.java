package com.innovaee.eorder.resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * @Title: BaseTestCase
 * @Description: 测试基类
 * @version V1.0
 */
public class BaseTestCase {

    /** 日志对象 */
    protected static final Logger LOGGER = LoggerFactory
            .getLogger(BaseTestCase.class);

    /** 链接 */
    public static String SERVER_URI = "http://localhost:8080/eorder-ws/rest";

    /** 客户端 */
    protected Client client;

    /** 请求的目标 */
    protected WebResource target;

    /** 返回的响应 */
    protected Response response;

    /**
     * 初始化客户端
     */
    @Before
    public void setUp() {
        client = Client.create();
        // client =
        // ClientBuilder.newClient().register(JacksonJsonProvider.class);
    }

    /**
     * 关闭返回的响应流
     */
    @After
    public void tearDown() {
        // response.close();
    }
}