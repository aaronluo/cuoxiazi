/***********************************************
 * Filename		: BaseEntity.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: BaseEntity
 * @Description: 实体类的基类
 * @author coderdream@gmail.com
 * @version V1.0
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Serializable getPK();

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public void copy(Object orig) {
		try {
			BeanUtils.copyProperties(this, orig);
		} catch (InvocationTargetException e) {
			System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
	}
}