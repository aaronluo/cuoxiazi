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
	
	
}