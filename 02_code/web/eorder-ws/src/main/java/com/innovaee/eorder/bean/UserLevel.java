/***********************************************
 * Filename		: UserLevel.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**   
* @Title: UserLevel 
* @Description: 用户等级
* @author coderdream@gmail.com   
* @version V1.0   
*/
@Entity
@Table(name = "t_user_level")
@XmlRootElement
public class UserLevel extends BaseEntity {

	@Override
	public Serializable getPK() {
		return levelId;
	}

	// 用户id, 不能为空, 必须唯一
	@Id
	@Column(name = "level_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer levelId;

	// 名称
	@Column(name = "level_name")
	private String levelName;

	// 折扣
	@Column(name = "discount")
	private float discount;

	// 等级积分
	@Column(name = "level_score")
	private Integer levelScore;

	// 用户状态
	@Column(name = "level_status")
	private Boolean levelStatus;

	// 创建时间
	@Column(name = "create_at")
	private Timestamp createAt;

	// 更新时间
	@Column(name = "update_at")
	private Timestamp updateAt;

	public UserLevel() {
	}

	public UserLevel(Integer levelId, String levelName, String password,
			float discount, Integer levelScore, Boolean levelStatus,
			Timestamp createAt, Timestamp updateAt) {
		super();
		this.levelId = levelId;
		this.levelName = levelName;
		this.discount = discount;
		this.levelScore = levelScore;
		this.levelStatus = levelStatus;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

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

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
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
		return "UserLevel [levelId=" + levelId + ", levelName=" + levelName
				+ ", discount=" + discount + ", levelScore=" + levelScore
				+ ", levelStatus=" + levelStatus + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + "]";
	}

}