/***********************************************
 * Filename        : Category.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.bean;

import com.innovaee.eorder.util.TimestampAdapter;

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

    @Override
    public Serializable getPK() {
        return categoryId;
    }

    // 用户id, 不能为空, 必须唯一
    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    // 分类名称
    @Column(name = "category_name")
    private String categoryName;

    // 分类图片地址
    @Column(name = "category_picture")
    private String categoryPicture;

    // 创建时间
    @Column(name = "create_at")
    private Timestamp createAt;

    // 更新时间
    @Column(name = "update_at")
    private Timestamp updateAt;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId) {
        this.setCategoryId(categoryId);
    }

    public Category(Integer categoryId, String categoryName) {
        this.setCategoryId(categoryId);
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        return this.getCategoryId();
    }

    public Category(Integer categoryId, String categoryName,
            String categoryPicture, Timestamp createAt, Timestamp updateAt) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Category)) {
            return false;
        }
        final Category other = (Category) object;
        
        return this.categoryId == other.categoryId;
    }

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

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName="
                + categoryName + ", categoryPicture=" + categoryPicture
                + ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
    }

}