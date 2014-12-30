/***********************************************
 * Filename        : LoggerInterceptor.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.web.interceptor;

import com.innovaee.eorder.module.utils.log.LoggerUtility;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: LoggerInterceptor
 * @Description: 日志拦截器
 *
 * @version V1.0
 */
public class LoggerInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    private static final LoggerUtility LOGGER_UTILITY = LoggerUtility.getInstance();
    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class);

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {

        String bizName = String.format("%s.%s()", actionInvocation.getAction()
                .getClass().getName(), actionInvocation.getProxy().getMethod());
        HttpServletRequest request = ServletActionContext.getRequest();

        LOGGER_UTILITY.startBizProcess(bizName,
                String.valueOf(Thread.currentThread().getId()));
        try {
            String requestInfo = String.format(
                    "URL:[%s://%s:%d%s], servlet path:[%s]",
                    request.getProtocol(), request.getLocalAddr(),
                    request.getLocalPort(), request.getRequestURI(),
                    request.getServletPath());
            LOGGER.info(requestInfo);
            return actionInvocation.invoke();
        } finally {
            LOGGER_UTILITY.endBizProcess();
        }
    }
}
