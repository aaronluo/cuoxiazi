package com.innovaee.eorder.vo;

import java.io.Serializable;

public class CategoryVO implements Serializable {

	// id, 不能为空, 必须唯一
	private Integer categoryId;

	// 名称
	private String categoryName;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}