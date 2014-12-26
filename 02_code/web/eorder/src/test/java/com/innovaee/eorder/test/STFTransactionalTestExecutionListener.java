package com.innovaee.eorder.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.innovaee.eorder.module.utils.log.LoggerUtility;

public class STFTransactionalTestExecutionListener extends TransactionalTestExecutionListener {
	private static final LoggerUtility loggerUtility = LoggerUtility.getInstance();
	
	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		loggerUtility.endBizProcess();
		super.afterTestMethod(testContext);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		super.beforeTestMethod(testContext);
		loggerUtility.startBizProcess(testContext.getTestMethod().getName() );
	}

	@Override
	protected void runAfterTransactionMethods(TestContext testContext) throws Exception {
		super.runAfterTransactionMethods(testContext);
	}

	@Override
	protected void runBeforeTransactionMethods(TestContext testContext)
			throws Exception {
		super.runBeforeTransactionMethods(testContext);
	}

}
