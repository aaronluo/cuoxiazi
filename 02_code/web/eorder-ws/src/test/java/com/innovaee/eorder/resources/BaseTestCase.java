package com.innovaee.eorder.resources;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	protected static String SERVER_URI = "http://localhost:8080/eorder-ws/rest";

	/** 客户端 */
	protected Client client;

	/** 请求的目标 */
	protected WebTarget target;

	/** 返回的响应 */
	protected Response response;

	/**
	 * 初始化客户端
	 */
	@Before
	public void setUp() {
		client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
	}

	/**
	 * 关闭返回的响应流
	 */
	@After
	public void tearDown() {
		response.close();
	}
}