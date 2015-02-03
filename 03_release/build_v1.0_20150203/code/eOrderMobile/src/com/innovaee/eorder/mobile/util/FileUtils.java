/***********************************************
 * Filename    : FileUtils.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;


/**
 * 文件操作工具集
 * 
 * 
 */
public final class FileUtils {

    /**
     * 检查是否存在SDCard
     * 
     * @return 是否存在，true存在，false不存在
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存Bitmap到缓存文件夹中
     * 
     * @param bitmap
     *            需要保存的图片对象
     * @param name
     *            保持的图片名字
     */
    public static void saveBitmap(Bitmap bitmap, String name) {
        File file = new File(Env.Path.PHOTO_PATH, name);

        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException error) {
            Log.d("saveBitmap", error.toString());
        } catch (IOException error) {
            Log.d("saveBitmap", error.toString());
        }    
    }

    /**
     * 创建文件夹（如果不存在）
     * 
     * @param dir
     *            文件夹地址
     */
    public static void mkDir(final String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception error) {
            	Log.e("mkDir", error.toString());
            }	
        }
        file = null;
    }

    /**
     * 功能简述:创建文件 功能详细描述: 注意:1：如果不存在父文件夹，则新建文件夹；2：如果文件已存在，则直接返回
     * 
     * @param destFileName
     *            文件路径名
     * @param replace
     *            是否删除旧文件，生成新文件
     * @return 是否成功
     */
    public static boolean createFile(String destFileName, boolean replace) {
        File file = new File(destFileName);
        if (file.exists()) {
            if (replace) {
                file.delete();
            } else {
                Log.d("createFile:", "创建单个文件失败" + destFileName);
                return false;
            }
        }
        if (destFileName.endsWith(File.separator)) {
            Log.d("createFile:", "创建单个文件失败，目标不能是目录" + destFileName);
            return false;
        }    
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                Log.d("createFile:", "创建目录文件所在的目录失败！");
                return false;
            }
        }
            
        //创建目标文件
        try {
            if (file.createNewFile()) {
                Log.d("createFile:", "创建单个文件成功！" + destFileName);
                return true;
            } else {
                Log.d("createFile:", "创建单个文件失败！" + destFileName);
                return false;
            }
        } catch (IOException error) {
            Log.d("createFile:", "创建单个文件失败！" + destFileName);
            return false;
        }    
    }

    /**
     * sd卡是否可读写
     * 
     * @return 是否可读写，true可以读写，false不可读写
     */
    public static boolean isSDCardAvaiable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 指定路径文件是否存在
     * 
     * @param filePath
     *            文件路径
     * @return 是否存在，true存在，false不存在
     */
    public static boolean isFileExist(String filePath) {
        boolean result = false;
        try {
            File file = new File(filePath);
            result = file.exists();
            file = null;
        } catch (Exception error) {
        	Log.e("isFileExist", error.toString());
        }
        return result;
    }	

}
