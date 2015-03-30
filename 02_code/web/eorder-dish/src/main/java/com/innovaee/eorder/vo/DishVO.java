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

    /** 对象序列化ID */
    private static final long serialVersionUID = 144984591914099776L;

    /** 菜品名称 */
    private String dishName;

    /** 菜品价格 */
    private float dishPrice;

    /** 菜品图片 */
    private String dishPicture;

    /** 在售状态 */
    private boolean onSell;

    /** 分类id */
    @XmlTransient
    private Long categoryId;

    /** 分类名称 */
    @XmlTransient
    private String categoryName;

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
     * @param dishName
     *            菜品名称
     * @param dishPrice
     *            菜品价格
     * @param dishPicture
     *            菜品图片
     * @param onSell
     *            在售状态
     */
    public DishVO(Long id, String dishName, float dishPrice,
            String dishPicture, boolean onSell) {
        super();
        this.id = id;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishPicture = dishPicture;
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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
