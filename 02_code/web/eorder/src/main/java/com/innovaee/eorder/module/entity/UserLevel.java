/***********************************************
 * Filename        : UserLevel.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: UserLevel
 * @Description: 用户等级实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_user_level")
public class UserLevel extends BaseEntity {

	@Override
	public Serializable getPK() {
		return levelId;
	}

	/**
	 * 等级ID
	 */
	@Id
	@Column(name = "LEVEL_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer levelId;

	/**
	 * 等级名称
	 */
	@Column(name = "LEVEL_NAME")
	private String levelName;

	/**
	 * 折扣
	 */
	@Column(name = "DISCOUNT")
	private Float discount;

	/**
	 * 等级积分
	 */
	@Column(name = "level_score")
	private Integer levelScore;

	/**
	 * 等级状态
	 */
	@Column(name = "Level_STATUS")
	private Boolean levelStatus;

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

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getLevelScore() {
		return levelScore;
	}

	public void setLevelScore(Integer levelScore) {
		this.levelScore = levelScore;
	}

	public Boolean getLevelStatus() {
		return levelStatus;
	}

	public void setLevelStatus(Boolean levelStatus) {
		this.levelStatus = levelStatus;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Level [levelId=" + levelId + ", levelName=" + levelName
				+ ", discount=" + discount + ", levelScore=" + levelScore
				+ ", levelStatus=" + levelStatus + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + ", createAt="
				+ super.getCreateAt() + ", updateAt=" + super.getUpdateAt()
				+ "]";
	}

}