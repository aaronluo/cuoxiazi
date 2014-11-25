package com.innovaee.eorder.mobile.application;

import android.app.Application;
	
/**
 * 
 * @author wanglinglong
 *
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}	