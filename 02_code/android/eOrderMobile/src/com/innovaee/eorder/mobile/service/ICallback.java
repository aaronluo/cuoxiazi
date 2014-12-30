/***********************************************
 * Filename		: ICallback.java	
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.service;

import java.util.List;

/**
 * 类描述: 下载数据回调接口 功能详细描述:
 * 
 * @param <T>
 *            成功回调返回的数据类型
 */
public interface ICallback<T> {

    /**
     * 开始回调函数
     */
    public void onStarted();

    /**
     * 成功回调函数，返回泛型list数据
     * 
     * @param response
     */
    public void onSuccess(List<T> response);

    /**
     * 成功回调函数，返回泛型数据
     * 
     * @param response
     */
    public void onSuccessT(T response);

    /**
     * 失败回调函数，返回自定义错误码
     * 
     * @param error
     */
    public void onFailed(String error);
}
