/***********************************************
 * Filename       : Dish.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Title: Dish
 * @Description: 菜品实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_DISH")
public class Dish extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 菜品名称 */
    private String name;

    /** 菜品图片地址 */
    private String picPath;

    /** 菜品价格 */
    private float price;

    /** 在售状态 */
    private boolean onSell;

    /** 其他信息 */
    private String misc;

    /** 分类 */
    private Category category;

    /**
     * 默认构造函数
     */
    public Dish() {
    }

    /**
     * 构造函数
     * 
     * @param name
     *            菜品名称
     * @param picPath
     *            菜品图片地址
     * @param price
     *            菜品价格
     */
    public Dish(String name, String picPath, float price) {
        super();
        this.name = name;
        this.picPath = picPath;
        this.price = price;
    }

    @Basic
    @Column(name = "DISH_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DISH_PICTURE")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Basic
    @Column(name = "DISH_PRICE")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "ON_SELL")
    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    @Basic
    @Column(name = "MISC")
    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}