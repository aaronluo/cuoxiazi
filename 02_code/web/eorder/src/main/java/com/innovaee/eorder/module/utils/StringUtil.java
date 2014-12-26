/***********************************************
 * Filename		: StringUtil.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: StringUtil
 * @Description: 字符串工具类
 * @author coderdream@gmail.com
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
	public static List<Integer> stringToIntegerList(String srcStr, String regex) {
		List<Integer> list = new ArrayList<Integer>();
		if (null != srcStr && !"".equals(srcStr.trim())) {
			String[] strArray = srcStr.split(regex);
			int size = strArray.length;
			for (int i = 0; i < size; i++) {
				list.add(Integer.parseInt(strArray[i]));
			}
		}

		return list;
	}
}
