package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class FunctionServiceTest extends BaseSpringTestCase {

	@Autowired
	private FunctionService functionService;

	@Test
	public void getAllFunctions() {
		List<Function> allFunctions = functionService.findAllFunctions();
		Assert.assertNotNull(allFunctions);
		for (Function function : allFunctions) {
			System.out.println(function);
		}
	}

	@Test
	public void findFunctionsByFunctionName() {
		String functionName = "doSystemSettings";
		Function function = functionService.findFunctionsByFunctionName(functionName);
		Assert.assertNotNull(function);
	}

	@Test
	public void saveFunction() {
		Function function = new Function("abcd", "abcd", "abcd", "abcd", "abcd", "abcd", false);
		functionService.saveFunction(function);
	}

	@Test
	public void updateFunction() {
		Function function = new Function("abcd", "abcdxxx", "abcd", "abcd", "abcd", "abcd", false);
		functionService.updateFunction(function);
	}

}
