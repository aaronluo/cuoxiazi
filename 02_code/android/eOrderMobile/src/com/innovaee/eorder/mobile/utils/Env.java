package com.innovaee.eorder.mobile.utils;

import android.os.Environment;


public final class Env{	
	/**
	 * 路径类 所有路径相关的常量都统一放在此处
	 * 
	 * @author 
	 */
	public static final class Path {		
		/**
		 * sdcard缓存目录
		 */
		public final static String SDCARD = Environment.getExternalStorageDirectory().getPath() + "/" + "Eorder";
								
		/**
		 * 图片保存地址
		 */	
		public static final String PHOTO_PATH = SDCARD + "/photo/";		
	}		
	
	/**
	 * 服务器类　所有服务器相关常量，地址放在此处
	 * @author wanglinglong
	 *
	 */
	public static final class Server {
		/**
		 * 测试服务器地址
		 */
		public static final String SERVER_TEST = "http://192.168.4.32:8080/shop/csdn/listNewsGoods.action";
		
		/**	
		 * 正式服务器地址
		 */
		public static final String SERVER_TRUE = "http://192.168.4.32:8080/shop/csdn/listNewsGoods.action";		
	}
	
	
}