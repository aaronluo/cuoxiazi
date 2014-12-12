package com.innovaee.eorder.rest;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * 应用
 * 
 */
public class RestApplication extends ResourceConfig {
	public RestApplication() {
		// 服务类所在的包路径
		packages("com.innovaee.eorder.resources");
		// 注册JSON转换器
		register(JacksonJsonProvider.class);
	}
}