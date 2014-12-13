package com.innovaee.eorder.mobile.view;

/**
 * 
 * @author wanglinglong
 * 
 */
class CategoryItem {
	private String name;

	private String imageUrl;

	private String time;

	public CategoryItem() {
		super();
	}

	public CategoryItem(String name, String imageUrl, String time) {
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
