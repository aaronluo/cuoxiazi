/***********************************************
 * Filename        : LoggerUtilInterceptor.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.utils.interceptor;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

import org.aspectj.lang.JoinPoint;

/**
 * @Title: LoggerUtilInterceptor
 * @Description: 日志工具类拦截器
 *
 * @version V1.0
 */
public class LoggerUtilInterceptor {

    private static final LoggerUtility LOGGER_UTILITY = LoggerUtility
            .getInstance();

    /**
     * 日志调用入口
     * 
     * @param joinPoint
     */
    public void startInvoke(JoinPoint joinPoint) {
        String methodName = String.format("%s.%s", joinPoint.getSignature()
                .getDeclaringTypeName(), joinPoint.getSignature().getName());
        LOGGER_UTILITY.startInvoke(methodName);
    }

    /**
     * 日志调用出口
     * 
     * @param joinPoint
     */
    public void endInvoke(JoinPoint joinPoint) {
        String methodName = String.format("%s.%s", joinPoint.getSignature()
                .getDeclaringTypeName(), joinPoint.getSignature().getName());
        LOGGER_UTILITY.endInvoke(methodName);
    }
}
