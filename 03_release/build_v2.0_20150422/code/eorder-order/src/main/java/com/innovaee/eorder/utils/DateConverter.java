/***********************************************
 * Filename        : FloatConverter.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/04/2015
 ************************************************/

package com.innovaee.eorder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @Title: FloatConverter
 * @Description: 表单浮点字段转换器
 *
 * @version V1.0
 */
public class DateConverter extends StrutsTypeConverter {

    private static final DateFormat[] ACCEPT_DATE_FORMATS = {
            new SimpleDateFormat("MM/dd/yyyy"),
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyy/MM/dd") }; // 支持转换的日期格式

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertValue(Map context, Object value, Class toType) {
        if (toType == Date.class) { // 浏览器向服务器提交时，进行String to Date的转换
            // Date date = null;
            String dateString = null;
            String[] params = (String[]) value;
            dateString = params[0];// 获取日期的字符串
            for (DateFormat format : ACCEPT_DATE_FORMATS) {
                try {
                    return format.parse(dateString);// 遍历日期支持格式，进行转换
                } catch (Exception e) {
                    continue;
                }
            }
            return null;
        } else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to String的类型转换
            Date date = (Date) value;
            return new SimpleDateFormat("yyyy-MM-dd").format(date);// 输出的格式是yyyy-MM-dd
        }

        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object val) {
        return "" + val;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        return null;
    }

}
