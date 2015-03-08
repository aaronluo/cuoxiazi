/***********************************************
 * Filename        : Constants.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/07/2015
 ************************************************/

package com.innovaee.eorder.support;


/**
 * @Title: Constants
 * @Description: 系统编码中使用的常量集合类
 * 
 * @version V1.0
 */
public class Constants {

    /** 表示成功意义的字符串**/
    public final static String SUCCESS = "success";
    /** 表示失败意义的字符串**/
    public final static String FALSE = "false";
    /**  日期格式化模式 - 年年年年月月日日**/
    public final static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    
    /** 订单的状态值 */
    public final static Integer ORDER_NEW = 100;
    public final static Integer ORDER_SUBMITTED = 101;
    public final static Integer ORDER_PAID = 102;
    
}
