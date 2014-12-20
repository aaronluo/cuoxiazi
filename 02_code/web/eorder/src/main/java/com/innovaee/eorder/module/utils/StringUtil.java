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
