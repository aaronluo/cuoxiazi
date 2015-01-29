/***********************************************
 * Filename        : Dish.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.innovaee.eorder.util.TimestampAdapter;

/**
 * @Title: Dish
 * @Description: 菜品实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_dish")
@XmlRootElement
public class Dish extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 168429325729656547L;

    /**
     * 返回主键
     * 
     * @return 主键
     */
    @Override
    public Serializable getPK() {
        return dishId;
    }

    /** 菜品id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "dish_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dishId;

    /** 分类ID */
    @Column(name = "category_id")
    private Integer categoryId;

    /** 菜品名称 */
    @Column(name = "dish_name")
    private String dishName;

    /** 菜品图片地址 */
    @Column(name = "dish_picture")
    private String dishPicture;

    /** 菜品价格 */
    @Column(name = "dish_price")
    private float dishPrice;

    /** 在售状态 */
    @Column(name = "on_sell")
    private boolean onSell;

    /** 其他信息 */
    @Column(name = "misc")
    private String misc;

    /** 创建时间 */
    @Column(name = "create_at")
    private Timestamp createAt;

    /** 更新时间 */
    @Column(name = "update_at")
    private Timestamp updateAt;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "Dish [dishId=" + dishId + ", categoryId=" + categoryId
                + ", dishName=" + dishName + ", dishPicture=" + dishPicture
                + ", dishPrice=" + dishPrice + ", onSell=" + onSell + ", misc="
                + misc + ", createAt=" + createAt + ", updateAt=" + updateAt
                + "]";
    }

}