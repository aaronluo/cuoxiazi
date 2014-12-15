package com.innovaee.eorder.module.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.vo.FunctionVO;

public class FunctionService extends BaseService {

	@Resource
	private FunctionDao functionDao;

	@Resource
	private FunctionService functionService;

	public List<Function> findAllFunctions() {
		return (List<Function>) functionDao.findAllFunctions();
	}

	/**
	 * 返回所有用户
	 * 
	 * @return
	 */
	public List<FunctionVO> findAllFunctionVOs() {
		List<FunctionVO> functionvos = new ArrayList<FunctionVO>();
		FunctionVO functionVO = null;
		Function parentFunction = null;
		List<Function> functions = functionDao.findAllFunctions();
		for (Function function : functions) {
			functionVO = new FunctionVO();
			BeanUtils.copyProperties(function, functionVO);

			// 设置用户等级名称
			parentFunction = functionDao.loadFunction(function
					.getFunctionParent());
			if (null != parentFunction) {
				functionVO.setFunctionParentName(parentFunction
						.getFunctionName());
			}

			functionvos.add(functionVO);
		}

		return functionvos;
	}

	public List<Function> findFunctionsByParentFunctionId(
			Integer parentFunctionId) {
		return functionDao.findFunctionsByParentFunctionId(parentFunctionId);
	}

	public Function loadFunction(Integer functionId) {
		Function function = (Function) functionDao.get(functionId);

		if (null == function) {
			return null;
		}

		return function;
	}

	public Function saveFunction(Function function) {
		return functionDao.saveFunction(function);
	}

	public void updateFunction(Function function) {
		functionDao.updateFunction(function);
	}

	public void removeFunction(Integer functionId) {
		functionDao.removeFunction(new Function(functionId));
	}

	public void removeFunctions(String[] functionIds) {
		int length = functionIds.length;
		for (int i = 0; i < length; i++) {
			functionDao.removeFunction(new Function(Integer
					.parseInt(functionIds[i])));
		}
	}

	public Function findFunctionByFunctionName(String functionName) {
		return functionDao.findFunctionByFunctionName(functionName);
	}

}