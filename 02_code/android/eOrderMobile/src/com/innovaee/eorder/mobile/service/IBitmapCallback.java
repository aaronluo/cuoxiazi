package com.innovaee.eorder.mobile.service;

import java.util.List;
	
/**
 * 
 * <br>
 * 类描述: 图片加载回调接口 <br>
 * 功能详细描述:
 * 
 * @author wanglinglong
 * @param <T>
 *            成功回调返回的数据类型
 * @date [2014年9月23日]
 */
public interface IBitmapCallback<T> {
	
	public void onSuccess(String error);

	public void onFailed(String error);
}
