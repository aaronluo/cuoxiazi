/***********************************************
 * Filename    : TableInfoDataBean.java                                             
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;


/**
 * 提交订单开台信息数据Bean
 * 
 */
public class TableInfoDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public static final String TABLE = "table";

    // 开台id定义
    public static final String COLUM_ID = "tableId";

    // 会员电话号码定义
    public static final String COLUM_CELLPHONE = "cellphone";

    // 员工工号定义
    public static final String COLUM_SERVANTID = "servantId";

    // 总价定义
    public static final String COLUM_DISHPRICE = "dishPrice";
    
    // 总人数
    public static final String COLUM_ATTENDEENUMBER = "attendeeNumber";
    
    // 开台id
    private int tableId;

    // 会员电话号码
    private String cellphone;

    // 员工工号
    private String servantId;

    // 总价
    private String dishPrice;
    
    // 总人数
    private int attendeeNumber;
    
    //构造函数
    public TableInfoDataBean() {
    }
    
    /**
     * 构造函数
     * @param tableId 开台id
     * @param cellphone 开台电话号码
     * @param servantId 员工工号
     * @param dishPrice 总价
     */
    public TableInfoDataBean(int tableId, String cellphone, String servantId,
    		String dishPrice, int attendeeNumber) {
        this.tableId = tableId;
        this.cellphone = cellphone;
        this.servantId = servantId;
        this.dishPrice = dishPrice;
        this.attendeeNumber = attendeeNumber;
    }	

    /**
     * 获取开台id
     * 
     * @return 开台id
     */
    public int getTableId() {
        return this.tableId;
    }

    /**
     * 设置开台id
     * 
     * @param tableId
     *            开台id
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }	

    /**
     * 获取开台电话号码
     * 
     * @return 开台电话号码
     */
    public String getCellphone() {
        return this.cellphone;
    }

    /**
     * 设置开台电话号码
     * 
     * @param cellphone
     *            开台电话号码
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * 得到员工工号
     * 
     * @return 员工工号
     */
    public String getServantId() {
        return this.servantId;
    }

    /**
     * 设置员工工号
     * 
     * @param servantId
     *            员工工号
     */
    public void setServantId(String servantId) {
        this.servantId = servantId;
    }

    /**
     * 获取开台总价
     * 
     * @return 总价
     */
    public String getDishPrice() {
        return this.dishPrice;
    }

    /**
     * 设置开台总价
     * 
     * @param dishPrice
     *            总价
     */
    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }
    
    /**
     * 获取总人数
     * 
     * @return 总人数
     */
    public int getAttendeeNumber() {
        return this.attendeeNumber;
    }
    
    /**
     * 设置总人数
     * 
     * @param attendeeNumber
     *            总人数
     */
    public void setAttendeeNumber(int attendeeNumber) {
        this.attendeeNumber = attendeeNumber;
    }	
}
