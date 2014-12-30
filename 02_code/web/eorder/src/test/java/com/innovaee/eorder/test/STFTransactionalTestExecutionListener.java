
package com.innovaee.eorder.test;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

public class STFTransactionalTestExecutionListener extends
        TransactionalTestExecutionListener {
    private static final LoggerUtility LOGGER_UTILITY = LoggerUtility
            .getInstance();

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        LOGGER_UTILITY.endBizProcess();
        super.afterTestMethod(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);
        LOGGER_UTILITY.startBizProcess(testContext.getTestMethod().getName());
    }

    @Override
    protected void runAfterTransactionMethods(TestContext testContext)
            throws Exception {
        super.runAfterTransactionMethods(testContext);
    }

    @Override
    protected void runBeforeTransactionMethods(TestContext testContext)
            throws Exception {
        super.runBeforeTransactionMethods(testContext);
    }

}
