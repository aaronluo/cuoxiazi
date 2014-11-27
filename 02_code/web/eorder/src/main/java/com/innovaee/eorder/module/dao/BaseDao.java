/*
 * Copyright Declaration
 */

package com.innovaee.eorder.module.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.innovaee.eorder.module.entity.BaseEntity;

/**
 * The base class for all of Dao.
 * </br>
 * All Dao should extend from this class
 *
 * @author Jacky Zhu
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao extends HibernateDaoSupport {
	@SuppressWarnings("rawtypes")
	abstract protected Class getEntityClass();

	/**
	 * 
	 * @param pk
	 * @return
	 */
	public BaseEntity get(Serializable pk) {
		return (BaseEntity)getHibernateTemplate().get(getEntityClass(), pk);
	}
	
	public BaseEntity remove(Serializable pk) {
		BaseEntity o = (BaseEntity)getHibernateTemplate().load(getEntityClass(), pk);
		getHibernateTemplate().delete(o);
		return o;
	}

	public BaseEntity update(BaseEntity entity) {
		getHibernateTemplate().update(entity);
		return entity;
	}
	
	public BaseEntity save(BaseEntity entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}
}
