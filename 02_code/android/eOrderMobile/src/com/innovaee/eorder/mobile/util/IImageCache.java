/***********************************************
 * Filename		: IImageCache.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;

import android.graphics.Bitmap;

/**
 * 类描述:图片缓存的接口 
 * 功能详细描述:
 * 
 * @author wanglinglong
 * @date [2012-11-30]
 */
public interface IImageCache {
	public void set(String key, Bitmap value);

	public Bitmap get(String key);

	public void remove(String key);

	public void recycle(String key);

	public void clear();
}
