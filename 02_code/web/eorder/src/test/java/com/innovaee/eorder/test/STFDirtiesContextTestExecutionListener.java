package com.innovaee.eorder.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

public class STFDirtiesContextTestExecutionListener extends DirtiesContextTestExecutionListener {

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		super.afterTestClass(testContext);
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		super.afterTestMethod(testContext);
	}

	@Override
	protected void dirtyContext(TestContext testContext) {
		super.dirtyContext(testContext);
	}

}
