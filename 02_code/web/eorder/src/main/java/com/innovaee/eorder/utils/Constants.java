/***********************************************
 * Filename        : Constants.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.utils;

/**
 * @Title: Constants
 * @Description: 常量类
 *
 * @version V1.0
 */
public class Constants {

    /** 分隔符 */
    public static final String REGEX = ",";

    /** 默认角色：普通用户 */
    public static final Long DEFAULT_ROLE = 2L;

    /** 默认等级：普通会员 */
    public static final Long DEFAULT_LEVEL = 4L;

    /** 默认等级：普通会员 */
    public static final Long ROOT_FUNCTION = 0L;

    /** 最大ID值 */
    public static final Long MAX_ID = 99999999L;

    /** 最大记录条数 */
    public static final Integer MAX_RECORD = 99999999;

    /** 每页记录条数 */
    public static final Integer PAGE_SIZE = 5;

    /** 就餐桌数 */
    public static final Integer TABLE_NUMBER = 20;

    /** 表示成功意义的字符串 **/
    public final static String SUCCESS = "success";
    /** 表示失败意义的字符串 **/
    public final static String FALSE = "false";
    /** 日期格式化模式 - 年年年年月月日日 **/
    public final static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    /** 日期格式化模式 - 年年年年月月日日时时分分秒秒 **/
    public final static String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** 订单的状态值 */
    public final static Integer ORDER_NEW = 100;
    public final static Integer ORDER_SUBMITTED = 101;
    public final static Integer ORDER_PAID = 102;

    /** 默认菜单分类 */
    public static final String DEFAULT_CATEGORY = "默认";

    /** 默认会员分类 */
    public static final String DEFAULT_USR_LEVEL = "非会员";

    /** 默认会员分类 */
    public static final String CASHIER = "收银员";

    /** 默认会员分类 */
    public static final String SERVENT = "点餐员";

    /** 默认菜单分类图片 */
    public static final String DEFAULT_CATEGORY_PIC = "/default_category.png";

    /** 默认菜单分类图片 */
    public static final String DEFAULT_DISH_PIC = "/default_dish.png";

    /** 对用户等级进行升级操作 */
    public static final int USER_LEVEL_UP = 1;
    /** 对用户等级进行降级操作 */
    public static final int USER_LEVEL_DOWN = 0;
}
