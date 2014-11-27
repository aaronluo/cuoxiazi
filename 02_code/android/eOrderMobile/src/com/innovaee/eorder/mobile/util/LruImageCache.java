package com.innovaee.eorder.mobile.util;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
/**
 * 
 * <br>类描述:LRU图片缓存器
 * <br>功能详细描述:
 * 
 * @author  wangzhuobin
 * @date  [2012-11-30]
 */
@SuppressLint("NewApi")
public class LruImageCache implements IImageCache {
	/**
	 * 强引用图片缓存的大小，5M
	 */
	private int maxMemorySize = DEFAULT_SIZE;
	public static final int DEFAULT_SIZE = 5 * 1024 * 1024;

	/**
	 * 强引用缓存，线程安全
	 * 当缓存超过限定大小时，该缓存会把最久没有使用的图片从缓存中移除，直到小于限制值为止
	 */
	private LruCache<String, Bitmap> lruCache = null;

	/**
	 * 弱引用缓存
	 */
	private ConcurrentHashMap<String, SoftReference<Bitmap>> softCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();

	public LruImageCache(int maxMemorySize) {
		if (maxMemorySize > 0) {
			this.maxMemorySize = maxMemorySize;
		}
		lruCache = new LruCache<String, Bitmap>(maxMemorySize) {

			@Override
			protected void entryRemoved(boolean evicted, String key, Bitmap oldValue,
					Bitmap newValue) {
				//如果超过了大小，就把从强引用移除的图片加入到弱引用中
				if (evicted) {
					softCache.put(key, new SoftReference<Bitmap>(oldValue));
				}
			}

			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}

		};
	}

	/**
	 * 设置一个键值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Bitmap value) {
		if (key == null || value == null) {
			return;
		}
		if (lruCache != null) {
			lruCache.put(key, value);
			Log.d("mjw", "xx+" + lruCache.size());
		}
	}

	/**
	 * 获取值，没有则返回空
	 * 
	 * @param key
	 * @return
	 */
	public Bitmap get(String key) {
		if (key == null) {
			return null;
		}
		//先从强引用缓存中取，如果取不到的话，再从弱引用缓存里面取
		Bitmap bitmap = lruCache.get(key);
		if (bitmap == null) {
			SoftReference<Bitmap> softReference = softCache.get(key);
			if (softReference != null) {
				bitmap = softReference.get();
			}
		}
		return bitmap;
	}

	public void clear() {
		lruCache.evictAll();
		softCache.clear();
	}

	@Override
	public void remove(String key) {
		if (key == null) {
			return;
		}
		lruCache.remove(key);
		softCache.remove(key);
	}

	public int getMaxMemorySize() {
		return maxMemorySize;
	}
	
	@Override
	public void recycle(String key) {
		if (TextUtils.isEmpty(key)) {
			return;
		}
		Bitmap bitmap = lruCache.remove(key);
		if (bitmap == null) {
			SoftReference<Bitmap> softReference = softCache.remove(key);
			if (softReference == null) {
				return;
			}
			bitmap = softReference.get();
		}
		if (bitmap == null) {
			return;
		}
		if (!bitmap.isRecycled()) {
			bitmap.recycle();
		}
		bitmap = null;
	}
}
