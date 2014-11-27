package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.entity.Function;

public class FunctionService extends BaseService {

	@Resource
	private FunctionDao functionDao;

	@Resource
	private FunctionService functionService;

	public List<Function> findAllFunctions() {
		return (List<Function>) functionDao.findAllFunctions();
	}

	public Function findFunctionsByFunctionName(String functionName) {
		return (Function) functionDao.findFunctionsByFunctionName(functionName);
	}

	public void saveFunction(Function function) {
		functionDao.saveFunction(function);
	}

	public void updateFunction(Function function) {
		functionDao.updateFunction(function);
	}

	public void removeFunction(String functionName) {
		functionDao.removeFunction(new Function(functionName));
	}

	public void removeFunctions(String[] functionName) {
		int length = functionName.length;
		for (int i = 0; i < length; i++) {
			functionDao.removeFunction(new Function(functionName[i]));
		}

	}

}