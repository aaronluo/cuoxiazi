/***********************************************
 * Filename		: LoggerUtility.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils.log;

import java.util.Date;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**   
* @Title: LoggerUtility 
* @Description: 日志工具 
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class LoggerUtility {
	private static Logger logger = Logger.getLogger(LoggerUtility.class);
	private static final String MDCKey = "MDCKey";
	private static LoggerUtility instance = new LoggerUtility();

	public LoggerUtility() {
	}

	public static LoggerUtility getInstance() {
		return instance;
	}

	private String getMethodName(Throwable t) {
		if (t != null && t.getStackTrace().length > 0) {
			StackTraceElement element = t.getStackTrace()[0];
			return element.getClassName() + "." + element.getMethodName();
		}

		return "";
	}

	public void startBizProcess(String bizName, String MDCValue) {
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
		MDC.put(MDCKey, MDCValue);

		logger.fatal("********************************************************************************");
		logger.fatal("Business Process Name: [" + invokeInfo.getInvokerName()
				+ "]");
		logger.fatal("Thread ID: " + Thread.currentThread().getId()
				+ ", Thread name: " + Thread.currentThread().getName());
		logger.fatal("Call Time: " + invokeInfo.getDate());
		logger.fatal("--------------------- detail log info is below ---------------------------------");
		logger.fatal("");
	}

	public void startBizProcess(String bizName) {
		String MDCValue = String.valueOf(Thread.currentThread().getId());
		startBizProcess(bizName, MDCValue);
	}

	public void endBizProcess() {
		try {
			@SuppressWarnings("unchecked")
			Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
					.get(LoggerUtility.class.getSimpleName());

			InvokeInfo invokeInfo = invokeInfoStack.pop();

			Date now = new Date();
			logger.fatal("");
			logger.fatal("--------------------- detail log info is finished ------------------------------");
			logger.fatal("Thread ID: " + Thread.currentThread().getId()
					+ ", Thread name: " + Thread.currentThread().getName());
			logger.fatal("End Time: " + now);
			logger.fatal("Business Process [" + invokeInfo.getInvokerName()
					+ "] is completed, elapsed time: "
					+ (now.getTime() - invokeInfo.getDate().getTime()));
			logger.fatal("********************************************************************************");
		} finally {
			MDC.remove(LoggerUtility.class.getSimpleName());
			MDC.remove(MDCKey);
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

		logger.fatal(String.format(
				"[Start Local API Invoked][+][lvl:%-2d]-----%s()", level,
				methodName));
	}

	public void endInvoke(String methodName) {
		@SuppressWarnings("unchecked")
		Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC
				.get(LoggerUtility.class.getSimpleName());
		if (null == invokeInfoStack) {
			return;
		}

		InvokeInfo invokeInfo = invokeInfoStack.pop();

		logger.fatal(String
				.format("[End   Local API Invoked][-][lvl:%-2d]-----%s(), elapsed time: %d",
						invokeInfo.getLevel(), methodName, new Date().getTime()
								- invokeInfo.getDate().getTime()));
	}

	public void startInvoke(Throwable t) {
		String methodName = getMethodName(t);
		startInvoke(methodName);
	}

	public void endInvoke(Throwable t) {
		String methodName = getMethodName(t);
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
		logger.fatal(info);
	}

	public void endPerformanceLog() {
		InvokeInfo invokeInfo = (InvokeInfo) MDC.get(PERFORMANCE_LOG_KEY);
		MDC.remove(PERFORMANCE_LOG_KEY);

		String info = String.format(
				"[Performance Log]---->[%s]---->End, elapsed time: %d",
				invokeInfo.getInvokerName(), new Date().getTime()
						- invokeInfo.getDate().getTime());
		logger.fatal(info);
	}
}