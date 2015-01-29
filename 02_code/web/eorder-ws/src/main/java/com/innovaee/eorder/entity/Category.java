/***********************************************
 * Filename        : Category.java
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
 * @Title: Category
 * @Description: 菜品分类的实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_category")
@XmlRootElement
public class Category extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 5028186743184310311L;

    /**
     * 返回主键
     * 
     * @return 主键
     */
    @Override
    public Serializable getPK() {
        return categoryId;
    }

    /** 用户id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    /** 分类名称 */
    @Column(name = "category_name")
    private String categoryName;

    /** 分类图片地址 */
    @Column(name = "category_picture")
    private String categoryPicture;

    /** 创建时间 */
    @Column(name = "create_at")
    private Timestamp createAt;

    /** 更新时间 */
    @Column(name = "update_at")
    private Timestamp updateAt;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
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
        return "Category [categoryId=" + categoryId + ", categoryName="
                + categoryName + ", categoryPicture=" + categoryPicture
                + ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
    }

}