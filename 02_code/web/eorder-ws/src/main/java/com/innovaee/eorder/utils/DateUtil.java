/***********************************************
 * Filename       : DateUtil.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 08/03/2015  
 ************************************************/

package com.innovaee.eorder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DateUtil
 * @Description: 系统中日期处理工具类
 * 
 * @version V1.0
 */
public class DateUtil {
    private static Map<String, DateFormat> dateFormatters = new HashMap<String, DateFormat>();

    /**
     * 返回指定日期当天的最开始时间
     * 
     * @param date
     * @return
     */
    public static Date getFirstTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 返回指定日期当天的最后时间
     * 
     * @param date
     * @return
     */
    public static Date getLastTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 根据指定的格式化模式，将传入的日期转换成字符串
     * 
     * @param pattern
     *            - 日期格式化模式
     * @param date
     *            - 指定日期
     * @return 按照格式化模式格式化后的日期字符串
     */
    public static String formatDate(String pattern, Date date) {
        /**
         * <pre>
         * 因为DataFormat的对象构造是比较消耗JVM性能的，并且
         * 日期格式化在系统编码中是会被多次调用的，不同之处仅仅
         * 在于格式化日期的模式，所以一般系统编码会将不同格式
         * 的DateFormat对象实例放入一个Map中保存，再次调用相同
         * 格式的日期格式化请求时候，就可以直接取用
         * </pre>
         */
        DateFormat dateFormatter = null;

        if (dateFormatters.containsKey(pattern)) {
            dateFormatter = dateFormatters.get(pattern);
        } else {
            dateFormatter = new SimpleDateFormat(pattern);
            dateFormatters.put(pattern, dateFormatter);
        }

        return dateFormatters.get(pattern).format(date);
    }
}
