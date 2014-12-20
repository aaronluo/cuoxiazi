/***********************************************
 * Filename		: LoggerUtilInterceptor.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils.interceptor;

import org.aspectj.lang.JoinPoint;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

/**   
* @Title: LoggerUtilInterceptor 
* @Description: 日志工具类拦截器
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class LoggerUtilInterceptor {

	private static final LoggerUtility loggerUtility = LoggerUtility
			.getInstance();

	public void startInvoke(JoinPoint joinPoint) {
		String methodName = String.format("%s.%s", joinPoint.getSignature()
				.getDeclaringTypeName(), joinPoint.getSignature().getName());
		loggerUtility.startInvoke(methodName);
	}

	public void endInvoke(JoinPoint joinPoint) {
		String methodName = String.format("%s.%s", joinPoint.getSignature()
				.getDeclaringTypeName(), joinPoint.getSignature().getName());
		loggerUtility.endInvoke(methodName);
	}
}
