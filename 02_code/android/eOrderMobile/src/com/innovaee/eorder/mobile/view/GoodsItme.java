/***********************************************
 * Filename		: GoodsItem.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.view;

/**
 * 菜品列表显示Item类
 * @author wanglinglong
 * 
 */
class GoodsItem {
	private String name;

	private String imageUrl;

	private String time;

	public GoodsItem() {
		super();
	}

	public GoodsItem(String name, String imageUrl, String time) {
		super();

		this.name = name;

		this.imageUrl = imageUrl;

		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public String getName() {
		return name;
	}

	public String getImageId() {
		return imageUrl;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImageId(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
