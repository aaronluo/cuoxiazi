/***********************************************
 * Filename        : StringUtil.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: StringUtil
 * @Description: 字符串工具类
 *
 * @version V1.0
 */
public class StringUtil {

    /**
     * 将字符串转换成字符串列表，以regex作为分隔符
     * 
     * @param srcStr
     *            待转换的字符串
     * @param regex
     *            匹配
     * @return 字符串列表
     */
    public static List<String> stringToList(String srcStr, String regex) {
        List<String> list = new ArrayList<String>();
        if (null != srcStr && !"".equals(srcStr.trim())) {
            String[] strArray = srcStr.split(regex);
            int size = strArray.length;
            for (int i = 0; i < size; i++) {
                list.add(strArray[i]);
            }
        }

        return list;
    }

    /**
     * 将字符串转换成整型列表，以regex作为分隔符
     * 
     * @param srcStr
     *            待转换的字符串
     * @param regex
     *            匹配
     * @return 字符串列表
     */
    public static List<Long> stringToLongList(String srcStr, String regex) {
        List<Long> list = new ArrayList<Long>();
        if (null != srcStr && !"".equals(srcStr.trim())) {
            String[] strArray = srcStr.split(regex);
            int size = strArray.length;
            for (int i = 0; i < size; i++) {
                list.add(Long.parseLong(strArray[i]));
            }
        }

        return list;
    }

    /**
     * 将整型列表串转成字符串，中间加逗号，末尾不加
     * 
     * @param list
     *            整型列表
     * @return
     */
    public static String longValueListToString(List<Long> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Long longValue : list) {
            String longValueStr = longValue.toString();
            stringBuffer.append(longValueStr);
            stringBuffer.append(Constants.REGEX);
        }

        // 去掉末尾的逗号
        if (null != stringBuffer && !"".equals(stringBuffer.toString().trim())
                && 0 < stringBuffer.toString().trim().length()) {
            return stringBuffer.toString().substring(0,
                    stringBuffer.length() - 1);
        }

        return "";
    }

    /**
     * 判断是否为手机号码
     * 
     * @param cellphone
     * @return
     */
    public static boolean isMobileNO(String cellphone) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(cellphone);
        return matcher.matches();
    }

}