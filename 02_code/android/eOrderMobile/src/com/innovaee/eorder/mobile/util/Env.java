package com.innovaee.eorder.mobile.util;

import android.os.Environment;

public final class Env {
	/**
	 * 路径类 所有路径相关的常量都统一放在此处
	 * 
	 * @author
	 */
	public static final class Path {
		/**
		 * sdcard缓存目录
		 */
		public final static String SDCARD = Environment
				.getExternalStorageDirectory().getPath() + "/" + "Eorder";

		/**
		 * 图片保存地址
		 */
		public static final String PHOTO_PATH = SDCARD + "/photo/";
	}

	/**
	 * 服务器类　所有服务器相关常量，地址放在此处
	 * 
	 * @author wanglinglong
	 * 
	 */
	public static final class Server {
		/**
		 * 获取会员信息URL地址
		 */	
		public static final String SERVER_GET_USERINFO = "http://192.168.1.11:8080/eorder-ws/rest/users?cellphone=";
		public static final String SERVER_GET_USERINFO_TEST = "http://localhost:8080/eorder-ws/rest/users/myuser/";
		
		/**
		 * 获取分类列表URL地址
		 */
		public static final String SERVIE_GET_CATEGORY = "http://192.168.1.11:8080/eorder-ws/rest/categories";
		public static final String SERVIE_GET_CATEGORY_TEST = "http://localhost:8080/eorder-ws/rest/categories";
		
		/**
		 * 获取某分类下菜品列表URL地址
		 */	
		public static final String SERVIE_GET_DISH = "http://192.168.1.11:8080/eorder-ws/rest/dishes/mydishes/";
		public static final String SERVIE_GET_DISH_TEST = "http://localhost:8080/eorder-ws/rest/dishes/mydishes/";
		
		/**
		 * 获取某个会员的订餐记录URL地址
		 */
		public static final String SERVIE_GET_ORDERHESTORY = "http://192.168.1.11:8080/eorder-ws/rest/orders?cellphone=";
		public static final String SERVIE_GET_ORDERHESTORY_TEST = "http://localhost:8080/eorder-ws/rest/orders/myorders/";
		
		/**
		 * 获取某个订单的详情URL地址
		 */
		public static final String SERVIE_GET_ORDERINFO = "http://192.168.1.11:8080/eorder-ws/rest/ordertitems /myordertitems/";
		public static final String SERVIE_GET_ORDERINFO_TEST = "http://localhost:8080/eorder-ws/rest/orderitems/myorderitems/";
		
		/**
		 * 提交订单的URL地址
		 */
		public static final String SERVIE_POST_ORDER = "http://192.168.1.11:8080/eorder-ws/rest/orders/";						
	}			

}