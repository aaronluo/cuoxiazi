/***********************************************
 * Filename       : BaseVo.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * @Title: BaseVo
 * @Description: 值对象基类
 *
 * @version V1.0
 */
public class BaseVO implements Serializable {

    /** 功能ID */
    protected Long id;

    /** 功能ID */
    protected String stringId;

    /** 对象序列化ID */
    private static final long serialVersionUID = -5877424597115890819L;

    /** 日志对象 */
    protected static final Logger LOGGER = Logger.getLogger(BaseVO.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

}