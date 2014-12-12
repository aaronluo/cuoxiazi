package com.innovaee.eorder.util;

import java.util.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
	public Timestamp unmarshal(Date val) throws Exception {
		return new Timestamp(val.getTime());
	}

	public Date marshal(Timestamp val) throws Exception {
		return new Date(val.getTime());
	}
}