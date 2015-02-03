/***********************************************
 * Filename    : MyApplication.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.application;

import android.app.Application;


/**
 * 该类主要是为了方便其他工具类， 服务类通过Application 获取全局context
 * 
 * 
 */
public class MyApplication extends Application {
    //MyApplication自身
    private static MyApplication instance;

    /**
     * 单例
     * @return 单例自身
     */
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}