/***********************************************
 * Filename    : OrderReturnDataBean.java
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

	
/**
 * 订单返回详情信息数据Bean
 * 
 */
public class OrderReturnDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public static final String TABLE = "orderreturn";
    
    // 返回结果定义
    public static final String COLUM_TIME = "result";

    // 返回错误信息定义
    public static final String COLUM_TOTALPRICE = "message";
    
    // 返回结果
    private String result;

    // 返回错误信息
    private String message;

    /**
     * 构造函数
     */
    public OrderReturnDataBean() {
    }

    /**
     * 构造函数
     * @param result 返回结果
     * @param message 返回错误信息
     */
    public OrderReturnDataBean(String result, String message) {
        this.result = result;
        this.message = message;
    }	
    	
    /**
     * 得到返回结果
     * 
     * @return 返回结果
     */
    public String getResult() {
        return this.result;
    }

    /**
     * 设置返回结果
     * 
     * @param result
     *            返回结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取返回错误信息
     * 
     * @return 返回错误信息
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置返回错误信息
     * 
     * @param message
     *            返回错误信息
     */
    public void setMessage(String message) {
        this.message = message;
    }		
}
