/***********************************************
 * Filename		: FunctionDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.Function;

/**
 * @Title: FunctionDao
 * @Description: 权限数据访问对象
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class FunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Function.class;
	}

	/**
	 * 查找所有功能
	 * 
	 * @return 功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<Function> findAllFunctions() {
		return (List<Function>) super.getHibernateTemplate().find(
				"FROM Function");
	}

	/**
	 * 根据功能ID查找功能
	 * 
	 * @param functionId
	 * @return
	 */
	public Function loadFunction(Integer functionId) {
		return (Function) get(functionId);
	}

	/**
	 * 根据功能名称查找功能
	 * 
	 * @param functionName
	 *            功能名称
	 * @return 功能
	 */
	@SuppressWarnings("unchecked")
	public Function findFunctionByFunctionName(String functionName) {
		List<Function> list = (List<Function>) super.getHibernateTemplate()
				.find("FROM Function f WHERE f.functionName=?", functionName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存功能信息
	 * 
	 * @param function
	 *            待保存的功能
	 * @return 已保存的功能
	 */
	public Function saveFunction(Function function) {
		return (Function) save(function);
	}

	/**
	 * 更新功能
	 * 
	 * @param function
	 *            待更新的功能
	 */
	public void updateFunction(Function function) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		function.setUpdateAt(updateAt);
		update(function);
	}

	/**
	 * 移除功能
	 * 
	 * @param function
	 *            待移除的功能
	 */
	public void removeFunction(Function function) {
		super.getHibernateTemplate().delete(function);
	}

	/**
	 * 根据父功能ID查找功能
	 * 
	 * @param parentFunctionId
	 *            父功能ID
	 * @return 功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<Function> findFunctionsByParentFunctionId(
			Integer parentFunctionId) {
		return (List<Function>) super.getHibernateTemplate().find(
				"FROM Function f WHERE f.functionParent=?", parentFunctionId);
	}

}