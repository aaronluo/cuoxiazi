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
import javax.xml.bind.annotation.XmlTransient;

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
    private String dishName;

    /** 菜品价格 */
    private float dishPrice;

    /** 菜品图片 */
    private String dishPicture;

    /** 在售状态 */
    private boolean onSell;

    /** 更多信息 */
    @XmlTransient
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
     * @param dishName
     *            菜品名称
     * @param dishPicture
     *            菜品图片地址
     * @param dishPrice
     *            菜品价格
     */
    public Dish(String dishName, String dishPicture, float dishPrice) {
        super();
        this.dishName = dishName;
        this.dishPicture = dishPicture;
        this.dishPrice = dishPrice;
    }

    @Basic
    @Column(name = "DISH_NAME")
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Basic
    @Column(name = "DISH_PRICE")
    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    @Basic
    @Column(name = "DISH_PICTURE")
    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
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