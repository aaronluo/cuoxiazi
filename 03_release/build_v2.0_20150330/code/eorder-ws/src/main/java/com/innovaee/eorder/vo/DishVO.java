/***********************************************
 * Filename       : DishVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @Title: DishVO
 * @Description: 菜品值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class DishVO extends BaseVO {

    /** 菜品名称 */
    private String name;

    /** 菜品价格 */
    private float price;

    /** 菜品图片 */
    private String picPath;

    /** 在售状态 */
    private boolean onSell;

    /** 分类id */
    @XmlTransient
    private Long categoryId;
    
    /** 更多信息 */
    @XmlTransient
    private String misc;
    /**
     * 默认构造函数
     */
    public DishVO() {
        super();
    }

    /**
     * 构造函数
     * 
     * @param id
     *            ID
     * @param name
     *            菜品名称
     * @param price
     *            菜品价格
     * @param picPath
     *            菜品图片
     * @param onSell
     *            在售状态
     */
    public DishVO(Long id, String name, float price, String picPath,
            boolean onSell) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.picPath = picPath;
        this.onSell = onSell;
    }

    
    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

}
