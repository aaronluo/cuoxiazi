/***********************************************
 * Filename        : CallResult.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/07/2015
 ************************************************/

package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: CallResult
 * @Description: Web-Service调用返回结果值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class CallResult {

    /** 调用结果 **/
    private String result;
    /** 调用返回消息 **/
    private String message;

    public CallResult() {
        super();
    }

    /** 有参数构造函数 **/
    public CallResult(String result, String message) {
        super();
        this.result = result;
        this.message = message;
    }

    /** 下面为该对象属性setter/getter函数组 **/
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}