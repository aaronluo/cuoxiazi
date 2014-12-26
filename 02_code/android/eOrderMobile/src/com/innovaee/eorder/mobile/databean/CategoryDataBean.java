/***********************************************
 * Filename		: CategoryDataBean.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 菜品分类数据Bean
 * 
 * @author wanglinglong
 * 
 */
public class CategoryDataBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//表单名
	public final static String TABLE = "category"; 
	
	//分类id
	public final static String COLUM_ID = "id"; 
	
	//分类名字
	public final static String COLUM_NAME = "name"; 
	
	//分类背景图片
	public final static String COLUM_BITMAPUIL = "url"; 

	private int id;

	private String name;

	private String bitmapUrl;

	public CategoryDataBean() {
	}

	public CategoryDataBean(int id, String name, String bitmapUrl) {
		this.id = id;
		this.name = name;
		this.bitmapUrl = bitmapUrl;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBitmapUrl() {
		return this.bitmapUrl;
	}

	public void setBitmapUrl(String bitmapUrl) {
		this.bitmapUrl = bitmapUrl;
	}
}
