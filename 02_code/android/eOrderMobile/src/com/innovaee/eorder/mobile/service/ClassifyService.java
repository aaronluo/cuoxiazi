package com.innovaee.eorder.mobile.service;

/**
 * 
 * @author wanglinglong
 * 
 */
public interface ClassifyService {
		
	// 获取最新的分类信息
	public abstract <T> void getAllClassify(ICallback<T> callback);

}