/***********************************************
 * Filename    : Env.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;

import android.os.Environment;


/**
 * 环境，服务器的相关静态定义
 *
 */
public final class Env {
    /**
     * 路径类 所有路径相关的常量都统一放在此处
     * 
     */
    public static final class Path {
        /**
         * sdcard缓存目录
         */
        public static final String SDCARD = Environment
                .getExternalStorageDirectory().getPath() + "/" + "Eorder";

        /**
         * 图片保存地址
         */
        public static final String PHOTO_PATH = SDCARD + "/photo/";
    }

    /**
     * 服务器类　所有服务器相关常量，地址放在此处
     * 
     * 
     */
    public static final class Server {
        /**
         * 获取会员信息URL地址
         */
        public static final String SERVER_GET_USERINFO = "/eorder-ws/rest/users/";

        /**
         * 获取分类列表URL地址
         */
        public static final String SERVIE_GET_CATEGORY = "/eorder-ws/rest/categories";

        /**
         * 获取某分类下菜品列表URL地址
         */
        public static final String SERVIE_GET_DISH = "/eorder-ws/rest/categories/";
        
        /**
         * 获取某个会员的订餐记录URL地址
         */
        public static final String SERVIE_GET_ORDERHESTORY = "/eorder-ws/rest/users/";

        /**
         * 获取某个订单的详情URL地址
         */
        public static final String SERVIE_GET_ORDERINFO = "/eorder-ws/rest/orders/";

        /**
         * 提交订单的URL地址
         */
        public static final String SERVIE_POST_ORDER = "/eorder-ws/rest/orders/";
    }

}