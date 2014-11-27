package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.Function;

public class FunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Function.class;
	}

	@SuppressWarnings("unchecked")
	public List<Function> findAllFunctions() {
		return (List<Function>) super.getHibernateTemplate().find("FROM Function");
	}

	public void saveFunction(Function function) {
		save(function);
	}

	public void updateFunction(Function function) {
		update(function);
	}

	public void removeFunction(Function function) {
		super.getHibernateTemplate().delete(function);
	}

	@SuppressWarnings("unchecked")
	public Function findFunctionsByFunctionName(String functionName) {
		List<Function> list = (List<Function>) super.getHibernateTemplate().find("FROM Function f WHERE f.functionName=?", functionName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}
	
}