package com.innovaee.eorder.mobile.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

/**
 * 显示工具集
 * 
 * @author wanglinglong
 * 
 */
public class DisplayUtil {
	/**
	 * @param res
	 * @param resId
	 * @return
	 */
	public static Bitmap decodeBitmap(Resources res, int resId) {
		// TODO
		Bitmap retval = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inDither = false; // Disable Dithering mode
			opts.inPurgeable = true; // Tell to gc that whether it needs free
			// memory, the Bitmap can be cleared
			opts.inInputShareable = true; // Which kind of reference will be
											// used to recover the Bitmap data
											// after being clear, when it will
											// be used in the future
			// 3.0以下系统，压缩图片质量
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				opts.inPreferredConfig = Bitmap.Config.RGB_565;
				opts.inDither = true;
			}
			retval = BitmapFactory.decodeResource(res, resId, opts);
		} catch (Exception e) {
		} catch (OutOfMemoryError e) {
			Log.e("DisplayUtil", "decodeBitmap:" + e.toString());
			System.gc();
		}
		return retval;
	}

	/**
	 * 
	 * @param pathName
	 * @return
	 */
	public static Bitmap decodeBitmap(String pathName) {
		Bitmap retval = null;
		InputStream stream = null;
		try {
			stream = new FileInputStream(pathName);
			retval = decodeBitmap(stream);
		} catch (Exception e) {
		} catch (OutOfMemoryError e) {
			System.gc();
		}
		return retval;
	}

	/**
	 * 
	 * @param is
	 * @return
	 */
	public static Bitmap decodeBitmap(InputStream is) {
		if (is == null) {
			return null;
		}
		Bitmap retval = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inDither = false; // Disable Dithering mode
		opts.inPurgeable = true; // Tell to gc that whether it needs free
									// memory, the Bitmap can be cleared
		opts.inInputShareable = true; // Which kind of reference will be
										// used to recover the Bitmap data
										// after being clear, when it will
										// be used in the future
		// 3.0以下系统，压缩图片质量
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			opts.inPreferredConfig = Bitmap.Config.RGB_565;
			opts.inDither = true;
		}
		try {
			retval = BitmapFactory.decodeStream(is, null, opts);
		} catch (OutOfMemoryError e) {
			Log.e("DisplayUtil", "decodeBitmap:" + e.toString());
			System.gc();
		}
		return retval;
	}

	/**
	 * @param bytes
	 * @return
	 */
	public static Bitmap decodeBitmap(byte[] bytes) {
		Bitmap retval = null;
		InputStream stream = null;
		try {
			stream = new ByteArrayInputStream(bytes);
			retval = decodeBitmap(stream);
		} catch (Exception e) {
		} catch (OutOfMemoryError e) {
			System.gc();
		}
		return retval;
	}
}