
package com.innovaee.eorder.test.module.utils.log;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

import org.junit.Test;

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
    
    
    static class LoggerThread extends Thread {

        private static final LoggerUtility LOGGER_UTIILTY = LoggerUtility
                .getInstance();

        public void tier2() {
            LOGGER_UTIILTY.startInvoke("tier2");
            LOGGER_UTIILTY.endInvoke("tier2");
        }

        @Override
        public void run() {
            LOGGER_UTIILTY.startBizProcess("testLoggerUtility");
            LOGGER_UTIILTY.startInvoke("run");
            tier2();
            try {
                LOGGER_UTIILTY.startPerformanceLog("Thread.sleep");
                Thread.sleep(2 * 1000L);
                LOGGER_UTIILTY.endPerformanceLog();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER_UTIILTY.endInvoke("run");
            LOGGER_UTIILTY.endBizProcess();

            synchronized (this) {
                notifyAll();
            }
        }
    }
}
