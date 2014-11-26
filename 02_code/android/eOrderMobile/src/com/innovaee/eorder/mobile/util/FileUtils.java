package com.innovaee.eorder.mobile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

/**
*
* @author
* 
*/	
public final class FileUtils {
	
    /**
     * 检查是否存在SDCard
     * @return
    */
    public static boolean hasSdcard(){
    	String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
        	return true;
        } else {
        	return false;
        }
    }
    				
    /**
     * 保存Bitmap到缓存文件夹中	
     * @param mBitmap
     * @param mName
     */
    public static void saveBitmap(Bitmap mBitmap, String mName) {
    	File f = new File(Env.Path.PHOTO_PATH, mName);
    	  			
    	if (f.exists()) {
    		f.delete();
    	}
    	try {
    		FileOutputStream out = new FileOutputStream(f);
    		mBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
    		out.flush();
    		out.close();
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    	
    /**
	 * 创建文件夹（如果不存在）
	 * 
	 * @param dir
	 */
	public static void mkDir(final String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				Log.d("leonwang:", "mkDir error, folder = " + dir);
			}
		}
		file = null;
	}
	
	/**
	 * <br>功能简述:创建文件
	 * <br>功能详细描述:
	 * <br>注意:1：如果不存在父文件夹，则新建文件夹；2：如果文件已存在，则直接返回
	 * @param destFileName
	 * @param replace 是否删除旧文件，生成新文件
	 * @return
	 */		
	public static boolean createFile(String destFileName, boolean replace) {
		File file = new File(destFileName);
		if (file.exists()) {
			if (replace) {
				file.delete();
			} else {
				System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
				return false;
			}
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标不能是目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {
			System.out.println("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目录文件所在的目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建单个文件" + destFileName + "失败！");
			return false;
		}
	}

	/**
	 * sd卡是否可读写
	 * 
	 * @author 
	 * @return
	 */
	public static boolean isSDCardAvaiable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	
	/**
	 * 指定路径文件是否存在
	 * 
	 * @author 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		boolean result = false;
		try {
			File file = new File(filePath);
			result = file.exists();
			file = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
    
}
