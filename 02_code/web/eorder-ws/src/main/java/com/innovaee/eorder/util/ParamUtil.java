package com.innovaee.eorder.util;

import java.util.HashMap;
import java.util.Map;

public class ParamUtil {
	public static Map<String, String> parse(String paramString) {
		Map<String, String> params = new HashMap<String, String>();
		String[] paramPairs = paramString.split("&");
		for (String param : paramPairs) {
			String[] key_value = param.split("=");
			params.put(key_value[0], key_value[1]);
		}
		return params;
	}

	public static Map<Integer, String> parseInteger(String paramString) {
		Map<Integer, String> params = new HashMap<Integer, String>();
		String[] paramPairs = paramString.split("&");
		for (String param : paramPairs) {
			String[] key_value = param.split("=");
			params.put(Integer.parseInt(key_value[0]), key_value[1]);
		}
		return params;
	}
}