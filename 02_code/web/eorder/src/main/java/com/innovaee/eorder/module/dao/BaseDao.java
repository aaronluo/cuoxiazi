/***********************************************
 * Filename		: BaseEntity.java																								
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.innovaee.eorder.module.entity.BaseEntity;

/**
 * @Title: BaseDao
 * @Description: 所有DAO的基类
 * @author coderdream@gmail.com
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao extends HibernateDaoSupport {
	@SuppressWarnings("rawtypes")
	abstract protected Class getEntityClass();

	/**
	 * 获取实体
	 * 
	 * @param pk
	 *            主键
	 * @return
	 */
	public BaseEntity get(Serializable pk) {
		return (BaseEntity) getHibernateTemplate().get(getEntityClass(), pk);
	}

	/**
	 * 删除实体
	 * 
	 * @param pk
	 *            主键
	 * @return
	 */
	public BaseEntity remove(Serializable pk) {
		BaseEntity o = (BaseEntity) getHibernateTemplate().load(
				getEntityClass(), pk);
		getHibernateTemplate().delete(o);
		return o;
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体
	 * @return 更新后的实体
	 */
	public BaseEntity update(BaseEntity entity) {
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体
	 * @return 新增的实体
	 */
	public BaseEntity save(BaseEntity entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}
}