/***********************************************
 * Filename       : ApplicationConextProvider.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Title: ApplicationConextProvider
 * @Description: 为获取Spring ApplicationContext对象定义的助手类
 * 
 * @version V1.0
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    /**Spring AppliationContext对象实例 **/
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext appContext)
            throws BeansException {

        if(context == null) {
            ApplicationContextProvider.context = appContext;
        } else {
            throw new IllegalStateException("The application context provider has already initialized. ");
        }

    }

    public static ApplicationContext getContext() {
        if(context != null) {
            return context;
        } else {
            throw new IllegalStateException("The application context is not avaiable. ");
        }
    }
}
