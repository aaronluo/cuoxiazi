/***********************************************
 * Filename        : TimestampAdapter.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.util;

import java.util.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @Title: TimestampAdapter
 * @Description: 时间适配器
 * @version V1.0
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {

    /**
     * 将日期格式转换成时间格式
     * 
     * @param val
     *            日期
     * @return 时间
     */
    public Timestamp unmarshal(Date val) throws Exception {
        return new Timestamp(val.getTime());
    }

    /**
     * 将时间格式转换成日期格式
     * 
     * @param val
     *            时间
     * @return 日期
     */
    public Date marshal(Timestamp val) throws Exception {
        return new Date(val.getTime());
    }
}