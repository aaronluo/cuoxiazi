/***********************************************
 * Filename		: GoodService.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.service;

/**
 * 菜品信息获取相关接口
 * @author wanglinglong
 * 
 */
public interface GoodService {
	
	// 获取最新的商品信息
	public abstract <T> void getAllGoods(int id, ICallback<T> callback);

	// 获取最新的单个商品的详细信息
	public abstract <T> void findGoodsById(ICallback<T> callback);
}	