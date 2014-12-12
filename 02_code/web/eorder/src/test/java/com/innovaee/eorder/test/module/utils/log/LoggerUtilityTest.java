package com.innovaee.eorder.test.module.utils.log;

import org.junit.Test;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

class LoggerThread extends Thread {

	private static final LoggerUtility loggerUtility = LoggerUtility.getInstance();

	public void tier2() {
		loggerUtility.startInvoke("tier2");
		loggerUtility.endInvoke("tier2");
	}

	@Override
	public void run() {
		loggerUtility.startBizProcess("testLoggerUtility");
		loggerUtility.startInvoke("run");
		tier2();
		try {
			loggerUtility.startPerformanceLog("Thread.sleep");
			Thread.sleep(2 * 1000L);
			loggerUtility.endPerformanceLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
		loggerUtility.endInvoke("run");
		loggerUtility.endBizProcess();

		synchronized (this) {
			notifyAll();
		}
	}

}

public class LoggerUtilityTest {
	@Test
	public void testLoggerUtiliy() {
		Thread thread1 = new LoggerThread();
		Thread thread2 = new LoggerThread();

		thread1.start();
		thread2.start();

		synchronized (thread1) {
			try {
				thread1.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
