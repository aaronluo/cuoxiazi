/***********************************************
 * Filename        : LoggerUtility.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.utils.log;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.util.Date;
import java.util.Stack;

/**
 * @Title: LoggerUtility
 * @Description: 日志工具
 *
 * @version V1.0
 */
public class LoggerUtility {
    private static final Logger LOGGER = Logger.getLogger(LoggerUtility.class);
    private static final String MDC_KEY = "MDCKey";
    private static LoggerUtility instance = new LoggerUtility();

    public LoggerUtility() {
    }

    public static LoggerUtility getInstance() {
        return instance;
    }

    private String getMethodName(Throwable throwable) {
        if (throwable != null && throwable.getStackTrace().length > 0) {
            StackTraceElement element = throwable.getStackTrace()[0];
            return element.getClassName() + "." + element.getMethodName();
        }

        return "";
    }

    public void startBizProcess(String bizName, String mdcValue) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
                .get(LoggerUtility.class.getSimpleName());
        invokeInfoStack = new Stack<InvokeInfo>();
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(true);
        invokeInfo.setInvokerName(bizName);
        invokeInfo.setLevel(0);
        invokeInfoStack.push(invokeInfo);
        MDC.put(LoggerUtility.class.getSimpleName(), invokeInfoStack);
        MDC.put(MDC_KEY, mdcValue);

        LOGGER.fatal("******************************************");
        LOGGER.fatal("Business Process Name: [" + invokeInfo.getInvokerName()
                + "]");
        LOGGER.fatal("Thread ID: " + Thread.currentThread().getId()
                + ", Thread name: " + Thread.currentThread().getName());
        LOGGER.fatal("Call Time: " + invokeInfo.getDate());
        LOGGER.fatal("-------- detail log info is below --------------------");
        LOGGER.fatal("");
    }

    public void startBizProcess(String bizName) {
        String mdcValue = String.valueOf(Thread.currentThread().getId());
        startBizProcess(bizName, mdcValue);
    }

    public void endBizProcess() {
        try {
            @SuppressWarnings("unchecked")
            Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
                    .get(LoggerUtility.class.getSimpleName());

            InvokeInfo invokeInfo = invokeInfoStack.pop();

            Date now = new Date();
            LOGGER.fatal("");
            LOGGER.fatal("--------  detail log info is finished--------------------");
            LOGGER.fatal("Thread ID: " + Thread.currentThread().getId()
                    + ", Thread name: " + Thread.currentThread().getName());
            LOGGER.fatal("End Time: " + now);
            LOGGER.fatal("Business Process [" + invokeInfo.getInvokerName()
                    + "] is completed, elapsed time: "
                    + (now.getTime() - invokeInfo.getDate().getTime()));
            LOGGER.fatal("******************************************");
        } finally {
            MDC.remove(LoggerUtility.class.getSimpleName());
            MDC.remove(MDC_KEY);
            MDC.remove(PERFORMANCE_LOG_KEY);
        }
    }

    public void startInvoke(String methodName) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
                .get(LoggerUtility.class.getSimpleName());
        if (null == invokeInfoStack) {
            return;
        }

        InvokeInfo invokeInfoParent = invokeInfoStack.peek();

        int level = invokeInfoStack.size();
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(invokeInfoParent.isInTransaction());
        invokeInfo.setInvokerName(methodName);
        invokeInfo.setLevel(level);
        invokeInfoStack.push(invokeInfo);

        LOGGER.fatal(String.format(
                "[Start Local API Invoked][+][lvl:%-2d]-----%s()", level,
                methodName));
    }

    public void startInvoke(Throwable throwable) {
        String methodName = getMethodName(throwable);
        startInvoke(methodName);
    }
    
    public void endInvoke(String methodName) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
                .get(LoggerUtility.class.getSimpleName());
        if (null == invokeInfoStack) {
            return;
        }

        InvokeInfo invokeInfo = invokeInfoStack.pop();

        LOGGER.fatal(String
                .format("[End   Local API Invoked][-][lvl:%-2d]-----%s(), elapsed time: %d",
                        invokeInfo.getLevel(), methodName, new Date().getTime()
                                - invokeInfo.getDate().getTime()));
    }



    public void endInvoke(Throwable throwable) {
        String methodName = getMethodName(throwable);
        endInvoke(methodName);
    }

    private static final String PERFORMANCE_LOG_KEY = "PERFORMANCE_LOG_KEY";

    public void startPerformanceLog(String apiName) {
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(true);
        invokeInfo.setInvokerName(apiName);
        invokeInfo.setLevel(0);
        MDC.put(PERFORMANCE_LOG_KEY, invokeInfo);

        String info = String.format("[Performance Log]---->[%s]---->Start",
                apiName);
        LOGGER.fatal(info);
    }

    public void endPerformanceLog() {
        InvokeInfo invokeInfo = (InvokeInfo) MDC.get(PERFORMANCE_LOG_KEY);
        MDC.remove(PERFORMANCE_LOG_KEY);

        String info = String.format(
                "[Performance Log]---->[%s]---->End, elapsed time: %d",
                invokeInfo.getInvokerName(), new Date().getTime()
                        - invokeInfo.getDate().getTime());
        LOGGER.fatal(info);
    }
}