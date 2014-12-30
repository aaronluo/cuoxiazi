/***********************************************
 * Filename		: DisplayUtil.java	
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 显示工具集
 * 
 * 
 */
public class DisplayUtil {
    /**
     * @param res
     *            转换资源
     * @param resId
     *            转换资源id
     * @return 生成的bitmap对象
     */
    public static Bitmap decodeBitmap(Resources res, int resId) {
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
            System.gc();
        }
        return retval;
    }

    /**
     * 生成bitmap对象
     * 
     * @param pathName
     *            图片文件路径
     * @return 生成的bitmap对象
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
     * 解析输入流到bitmap对象
     * 
     * @param is
     *            输入流
     * @return 生成的bitmap对象
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
            System.gc();
        }
        return retval;
    }

    /**
     * 转换字节数据到bitmap对象
     * 
     * @param bytes
     *            图像字节数据
     * @return 生成的bitmap对象
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

    /**
     * 转换函数，Dip到Pixel
     * 
     * @param paramDisplayMetrics
     *            工具类
     * @param paramFloat
     * @return 转换后的数据
     */
    public static int dipToPixels(DisplayMetrics paramDisplayMetrics,
            float paramFloat) {
        return (int) TypedValue.applyDimension(1, paramFloat,
                paramDisplayMetrics);
    }
}