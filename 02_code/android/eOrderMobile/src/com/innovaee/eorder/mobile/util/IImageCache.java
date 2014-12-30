/***********************************************
 * Filename		: IImageCache.java	
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;

import android.graphics.Bitmap;

/**
 * 类描述:图片缓存的接口 功能详细描述:
 * 
 * @date [2012-11-30]
 */
public interface IImageCache {
    /**
     * 设置图片缓存
     * 
     * @param key
     *            指定key
     * @param value
     *            图片对象
     */
    public void set(String key, Bitmap value);

    /**
     * 得到该图片
     * 
     * @param key
     *            指定key
     * @return 需要的图片对象
     */
    public Bitmap get(String key);

    /**
     * 删除该图片
     * 
     * @param key
     *            指定key
     */
    public void remove(String key);

    /**
     * 释放该图片
     * 
     * @param key
     *            指定key
     */
    public void recycle(String key);

    /**
     * 清除
     */
    public void clear();
}
