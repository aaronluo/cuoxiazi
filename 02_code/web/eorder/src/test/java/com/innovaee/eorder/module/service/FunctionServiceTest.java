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

	private String functionName = "测试";
	private String functionDesc = "测试";
	private String functionPath = "/test/doTest.action";
	private Integer functionParent = 1;
	private String functionOrder = "010500";
	private Boolean functionStatus = true;

	@Test
	public void getAllFunctions() {
		List<Function> allFunctions = functionService.findAllFunctions();
		Assert.assertNotNull(allFunctions);
		for (Function function : allFunctions) {
			System.out.println(function);
		}
	}

	@Test
	public void loadFunction() {
		Integer functionId = 2;
		Function function = functionService.loadFunction(functionId);
		Assert.assertNotNull(function);
		Assert.assertEquals("功能管理", function.getFunctionName());
		System.out.println(function);
	}

	@Test
	public void findFunctionsByParentFunctionId() {
		Integer parentFunctionId = 1;
		List<Function> functionList = functionService
				.findFunctionsByParentFunctionId(parentFunctionId);
		Assert.assertNotNull(functionList);
		for (Function function : functionList) {
			System.out.println(function);
		}
	}

	@Test
	public void saveFunction() {
		Function function = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus,
				createAt);
		Function functionNew = functionService.saveFunction(function);

		// 检查
		Function functionDB = functionService.loadFunction(functionNew
				.getFunctionId());
		Assert.assertNotNull(functionDB);
		Assert.assertEquals("测试", functionDB.getFunctionName());
	}

	@Test
	public void updateFunction() {
		// 先新增一个对象
		Function function = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus);
		Function functionNew = functionService.saveFunction(function);

		// 得到新增后的ID
		Integer functionId = functionNew.getFunctionId();

		// 更新属性
		String newFunctionName = "测试2";
		String newFunctionDesc = "测试2";
		functionNew.setFunctionName(newFunctionName);
		functionNew.setFunctionDesc(newFunctionDesc);
		functionService.updateFunction(functionNew);

		// 检查
		Function functionDB = functionService.loadFunction(functionId);
		Assert.assertNotNull(functionDB);
		Assert.assertEquals("测试2", functionDB.getFunctionName());
	}

	@Test
	public void removeFunction() {
		Function function = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus);
		Function functionNew = functionService.saveFunction(function);
		Integer functionId = functionNew.getFunctionId();
		functionService.removeFunction(functionId);
		// 检查
		Function functionDB = functionService.loadFunction(functionId);
		Assert.assertNull(functionDB);
	}

	@Test
	public void removeFunctions() {
		Function function1 = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus);
		Function function2 = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus);
		Function functionNew1 = functionService.saveFunction(function1);
		Function functionNew2 = functionService.saveFunction(function2);

		String[] functionIds = new String[] {
				functionNew1.getFunctionId().toString(),
				functionNew2.getFunctionId().toString() };
		functionService.removeFunctions(functionIds);

		// 检查
		Function functionDB1 = functionService.loadFunction(functionNew1
				.getFunctionId());
		Assert.assertNull(functionDB1);
		Function functionDB2 = functionService.loadFunction(functionNew2
				.getFunctionId());
		Assert.assertNull(functionDB2);
	}

}