package com.innovaee.eorder.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

public class STFDependencyInjectionTestExecutionListener extends DependencyInjectionTestExecutionListener {

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		super.beforeTestMethod(testContext);
	}

	@Override
	protected void injectDependencies(TestContext testContext) throws Exception {
		super.injectDependencies(testContext);
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		super.prepareTestInstance(testContext);
	}

}