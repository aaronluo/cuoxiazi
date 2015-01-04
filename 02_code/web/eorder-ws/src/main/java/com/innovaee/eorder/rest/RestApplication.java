/***********************************************
 * Filename		: RestApplication.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.rest;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @Title: RestApplication
 * @Description: REST应用入口
 * 
 * @version V1.0
 */
public class RestApplication extends ResourceConfig {
	/**
	 * 构造函数
	 */
	public RestApplication() {
		// 服务类所在的包路径
		packages("com.innovaee.eorder.resources");
		// 注册JSON转换器
		register(JacksonJsonProvider.class);
	}
}