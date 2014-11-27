package com.innovaee.eorder.module.utils.interceptor;

import org.aspectj.lang.JoinPoint;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

public class LoggerInterceptor {
	private static final LoggerUtility loggerUtility = LoggerUtility.getInstance();

	public void startInvoke(JoinPoint joinPoint) {
		String methodName = String.format("%s.%s"
				, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
		loggerUtility.startInvoke(methodName);
	}
	
	public void endInvoke(JoinPoint joinPoint) {
		String methodName = String.format("%s.%s"
				, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
		loggerUtility.endInvoke(methodName);
	}
}
