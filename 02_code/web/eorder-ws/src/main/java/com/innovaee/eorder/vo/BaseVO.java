/***********************************************
 * Filename       : BaseVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.vo;

/**
 * @Title: BaseVO
 * @Description: 值对象基类
 * 
 * @version V1.0
 */
public class BaseVO {

    /** ID */
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseVO() {

    }
}