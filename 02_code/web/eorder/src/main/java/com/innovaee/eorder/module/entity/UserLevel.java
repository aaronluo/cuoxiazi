package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_level")
public class UserLevel extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return levelId;
	}

	@Id
	@Column(name = "LEVEL_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer levelId;
	@Column(name = "LEVEL_NAME")
	private String levelName;
	@Column(name = "DISCOUNT")
	private Float discount;
	@Column(name = "level_score")
	private Integer levelScore;
	@Column(name = "Level_STATUS")
	private Boolean levelStatus;
	@Column(name = "CREATE_AT")
	private Timestamp createAt;
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
				+ ", updateAt=" + updateAt + "]";
	}

}