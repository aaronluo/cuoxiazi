/***********************************************
 * Filename		: GoodService.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.service;

/**
 * 菜品信息获取相关接口
 * 
 */
public interface GoodService {
	
	/**
	 * 获取最新的商品信息
	 * @param id 菜品分类id
	 * @param callback 回调函数
	 */
	public abstract <T> void getAllGoods(int id, ICallback<T> callback);
}	