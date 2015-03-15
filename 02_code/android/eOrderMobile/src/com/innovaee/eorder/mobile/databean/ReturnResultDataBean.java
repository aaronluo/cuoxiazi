/***********************************************
 * Filename    : ReturnResultDataBean.java
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;
	
	
/**
 * 返回信息数据Bean
 * 
 */	
public class ReturnResultDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;
    
    // 返回结果
    private boolean result;
    			
    // 返回信息
    private String message;

    /**
     * 构造函数
     */
    public ReturnResultDataBean() {
    }

    /**
     * 构造函数
     * @param result 返回结果
     * @param message 返回错误信息
     */
    public ReturnResultDataBean(boolean result, String message) {
        this.result = result;
        this.message = message;
    }	
    	
    /**
     * 得到返回结果
     * 
     * @return 返回结果
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     * 设置返回结果
     * 
     * @param result
     *            返回结果
     */
    public void setResult(boolean result) {
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
