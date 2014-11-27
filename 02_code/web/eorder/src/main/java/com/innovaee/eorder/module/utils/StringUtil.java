package com.innovaee.eorder.module.utils;

import java.util.ArrayList;
import java.util.List;

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
}
