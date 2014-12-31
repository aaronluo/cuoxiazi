/***********************************************
 * Filename        : TimestampAdapter.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @Title: TimestampAdapter
 * @Description: 时间适配器
*
 * @version V1.0
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
    public Timestamp unmarshal(Date val) throws Exception {
        return new Timestamp(val.getTime());
    }

    public Date marshal(Timestamp val) throws Exception {
        return new Date(val.getTime());
    }
}