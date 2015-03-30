/***********************************************
 * Filename    : CategoryService.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.service;

/**
 * 获取分类相关接口
 * 
 */
public interface CategoryService {

    /**
     * 获取最新的分类信息
     * 
     * @param callback
     *            回调函数
     */
    public abstract <T> void getAllCategory(ICallback<T> callback);

}