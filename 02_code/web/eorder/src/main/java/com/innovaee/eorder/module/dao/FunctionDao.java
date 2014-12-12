package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.entity.Function;

public class FunctionDao extends BaseDao {

	@Resource
	private FunctionDao functionDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Function.class;
	}

	@SuppressWarnings("unchecked")
	public List<Function> findAllFunctions() {
		return (List<Function>) super.getHibernateTemplate().find(
				"FROM Function");
	}

	public Function loadFunction(Integer functionId) {
		return (Function) get(functionId);
	}

	public Function saveFunction(Function function) {
		return (Function) save(function);
	}

	public void updateFunction(Function function) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		function.setUpdateAt(updateAt);
		update(function);
	}

	public void removeFunction(Function function) {
		super.getHibernateTemplate().delete(function);
	}

	@SuppressWarnings("unchecked")
	public List<Function> findFunctionsByParentFunctionId(
			Integer parentFunctionId) {
		return (List<Function>) super.getHibernateTemplate().find(
				"FROM Function f WHERE f.functionParent=?", parentFunctionId);
	}

}