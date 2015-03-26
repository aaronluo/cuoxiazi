/***********************************************
 * Filename    : GoodsDataBean.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;


/**
 * 商品信息数据Bean
 * 
 */
public class GoodsDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;

    //表单名定义
    public static final String TABLE = "goods";

    //商品id定义
    public static final String COLUM_ID = "id";

    //名字定义
    public static final String COLUM_NAME = "name";

    //价格定义
    public static final String COLUM_PRICE = "price";

    //图片url地址定义
    public static final String COLUM_BITMAPURL = "url";

    //购买数量定义
    public static final String COLUM_COUNT = "count";

    //商品id
    private int id;
    
    //名字
    private String name;
    
    //价格
    private Double price;
    
    //图片url地址
    private String bitmapUrl;
        
    //购买数量
    private int count;

    /*
     * 构造函数
     */
    public GoodsDataBean() {
    }

    /**
     * 构造函数
     * @param id 商品id
     * @param name 商品名
     * @param price 商品价格
     * @param url 商品图片url地址
     */
    public GoodsDataBean(int id, String name, Double price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bitmapUrl = url;

        // count默认为1
        this.count = 1;
    }

    /**
     * 构造函数
     * @param id 商品id
     * @param name 商品名
     * @param price 商品价格
     * @param url 商品图片url地址
     * @param count 商品数量
     */	
    public GoodsDataBean(int id, String name, Double price, String url, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bitmapUrl = url;
        this.count = count;
        
        // count默认为1
        this.count = 1;
    }
    
    /**
     * 获取商品id
     * @return 商品id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置商品id
     * @param id 商品id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取商品名
     * @return 商品名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置商品名
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取价格
     * @return 价格
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * 设置价格
     * @param all 价格
     */
    public void setPrice(Double all) {
        this.price = all;
    }

    /**
     * 获取图片地址
     * @return 图片地址
     */
    public String getBitmapUrl() {
        return this.bitmapUrl;
    }

    /**
     * 设置商品图片地址
     * @param url 图片地址
     */
    public void setBitmapUrl(String url) {
        this.bitmapUrl = url;
    }

    /**
     * 获取数量
     * @return 数量
     */
    public int getCount() {
        return this.count;
    }

    /**
     * 设置数量
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
