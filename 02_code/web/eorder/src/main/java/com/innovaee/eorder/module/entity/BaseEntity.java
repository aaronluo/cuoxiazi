/***********************************************
 * Filename        : BaseEntity.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

/**
 * @Title: BaseEntity
 * @Description: 实体基类
 *
 * @version V1.0
 */
public abstract class BaseEntity implements Serializable {

	/** 日志对象 */
	protected static final Logger LOGGER = Logger.getLogger(BaseEntity.class);

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_AT")
	private Timestamp createAt;

	/**
	 * 更新时间
	 */
	@Column(name = "UPDATE_AT")
	private Timestamp updateAt;

	/**
	 * 返回主键
	 * 
	 * @return 主键
	 */
	public abstract Serializable getPK();

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(final Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(final Timestamp updateAt) {
		this.updateAt = updateAt;
	}

}