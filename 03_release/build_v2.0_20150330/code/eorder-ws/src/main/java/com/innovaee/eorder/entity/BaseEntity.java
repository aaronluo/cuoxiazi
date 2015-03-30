/***********************************************
 * Filename        : BaseEntity.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Title: BaseEntity
 * @Description: 实体基类
 * 
 * @version V1.0
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    protected Long id;

    /** 创建日期 */
    protected Date createDate;

    /** 更新日期 */
    protected Date updateDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CREATE_AT")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "UPDATE_AT")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}