/***********************************************
 * Filename       : BaseException.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.exception;

/**
 * @Title: BaseException
 * @Description: 系统异常类的基类
 * 
 * @version V1.0
 */

public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;
    /** 系统异常体系中的异常类标识符**/
    protected static String exceptionKey;
        
}
