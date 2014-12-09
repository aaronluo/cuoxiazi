package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */
public class GoodsDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String TABLE = "goods"; 		// 表单名
	public final static String COLUM_ID = "id"; 		// id
	public final static String COLUM_NAME = "name"; 	// 名字
	public final static String COLUM_PRICE = "price"; 	// 价格
	public final static String COLUM_BITMAPURL = "url";	// 图片url地址
	public final static String COLUM_COUNT = "count";   // 购买数量

	private int id;

	private String name;

	private Double price;
	
	private String bitmapUrl;
	
	private int count;

	public GoodsDataBean() {
	}

	public GoodsDataBean(int id, String name, Double price, String url) {
		this.id = id;	
		this.name = name;
		this.price = price;
		this.bitmapUrl = url;
			
		//count默认为1
		this.count = 1;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double all) {
		this.price = all;
	}	
	
	public String getBitmapUrl() {
		return this.bitmapUrl;
	}
	
	public void setBitmapUrl(String url) {
		this.bitmapUrl = url;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}		
}
