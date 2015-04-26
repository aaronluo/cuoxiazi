/***********************************************
 * Filename        : KeyValue.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

/**
 * @Title: UserVO
 * @Description: 用户值对象
 *
 * @version V1.0
 */
public class KeyValue {

    /** 键 */
    private Object key;

    /** 值 */
    private Object value;

    public KeyValue() {
    }

    public KeyValue(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}