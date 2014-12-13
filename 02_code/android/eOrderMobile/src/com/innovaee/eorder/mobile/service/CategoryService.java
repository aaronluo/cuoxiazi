package com.innovaee.eorder.mobile.service;

/**
 * 
 * @author wanglinglong
 * 
 */
public interface CategoryService {
		
	// 获取最新的分类信息
	public abstract <T> void getAllCategory(ICallback<T> callback);

}