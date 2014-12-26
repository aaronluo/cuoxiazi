/***********************************************
 * Filename		: Md5Util.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * @Title: Md5Util
 * @Description: MD5工具类
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class Md5Util {
	private static final Logger logger = Logger.getLogger(Md5Util.class);

	/**
	 * 获取MD5编码
	 * 
	 * @param str
	 *            带编码的字符串
	 * @return MD5编码结果
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public static void main(String[] args) {
		// Md5Util util = new Md5Util();
		// String src = "admin{admin}";
		// String src = "abcd{abcd}";
		String src = "test{test}";
		// 889255f1c9c8f12a353be255f78a848b
		// 889255f1c9c8f12a353be255f78a848b
		// String src = "12345{admin}";
		// ceb4f32325eda6142bd65215f4c0f371
		// ceb4f32325eda6142bd65215f4c0f371
		// ceb4f32325eda6142bd65215f4c0f371
		// a3a7302d124d78d471902bf168259beb
		// cf3b0ef29697910a4e5c387dfc02ba66
		// cf3b0ef29697910a4e5c387dfc02ba66
		// String src = "test{test}";
		logger.debug(Md5Util.getMD5Str(src));
		System.out.println(Md5Util.getMD5Str(src));
	}

}