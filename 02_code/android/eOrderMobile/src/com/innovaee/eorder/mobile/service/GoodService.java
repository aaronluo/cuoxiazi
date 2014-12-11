package com.innovaee.eorder.mobile.service;

/**
 * 
 * @author wanglinglong
 * 
 */
public interface GoodService {
	
	// 获取最新的商品信息
	public abstract <T> void getAllGoods(int id, ICallback<T> callback);

	// 获取最新的单个商品的详细信息
	public abstract <T> void findGoodsById(ICallback<T> callback);
}	