package com.innovaee.eorder.mobile.controller;

import android.content.Context;
	
	
/**
 * 
 * @author wanglinglong
 *
 */
public class DataManager {
	private static DataManager sSelf;
	private Context mContext;
		
	private DataManager(Context context) {
		//TODO
		mContext = context.getApplicationContext();
	}

	public synchronized static DataManager getInstance(Context context) {
		if (sSelf == null) {
			sSelf = new DataManager(context);
		}
		return sSelf;
	}	
		
	
	/**
	 * 
	 * <br>类描述: 数据请求监听器
	 * <br>功能详细描述:
	 * 
	 * @author  lichong
	 * @date  [2014年9月24日]
	 */
	public static interface IDataRequestListener<T> {

		/**
		 * <br>功能简述:
		 * <br>功能详细描述:
		 * <br>注意:
		 */
		public void onRequestStart();

		/**
		 * <br>功能简述:
		 * <br>功能详细描述:
		 * <br>注意:
		 * @param data 更新后的缓存数据
		 */
		public void onRequestSuccess(T data);

		/**
		 * <br>功能简述:
		 * <br>功能详细描述:
		 * <br>注意:
		 */
		public void onRequestFailed();
	}
		
}