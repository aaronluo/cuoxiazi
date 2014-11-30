package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */
public class GoodsDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String TABLE = "goods"; // 表单名
	public final static String COLUM_ID = "id"; // id
	public final static String COLUM_NAME = "name"; // 名字
	public final static String COLUM_PRICE = "price"; // 价格

	private int id;

	private String name;

	private float price;

	public GoodsDataBean() {
	}

	public GoodsDataBean(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
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

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float all) {
		this.price = all;
	}

}
