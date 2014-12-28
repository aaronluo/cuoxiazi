/***********************************************
 * Filename		: ImageDataManager.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;
	
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.SparseArray;
	
import com.innovaee.eorder.mobile.util.LruImageCache;

/**
 * 图片管理器
 * 管理图片，存储图片到缓存和sdcard,使用时从缓存或者sdcard读取图片
 * 
 * 
 */
public class ImageDataManager {
	//线程池类型
	public static enum ThreadPoolType {
		TYPE_LOCAL,  // 用于加载本地图片
		TYPE_REMOTE  // 用于下载远程图片
	}

	//默认小大
	private static final int DEFAULT_SIZE = 5 * 1024 * 1024;

	//SDcard存储地址
	private final static String STORAGE_PATH = Environment
			.getExternalStorageDirectory().getPath() + "/eOrderMobile";
	
	/**
	 * Soft cache for bitmaps kicked out of hard cache
	 */
	//	private static LruCache<String, SoftReference<Bitmap>> sSoftBitmapCache = new LruCache<String, SoftReference<Bitmap>>(
	//			5);
	
	private static LruImageCache sSoftBitmapCache = new LruImageCache(DEFAULT_SIZE);

	private static ImageDataManager sInstance = null;

	/**
	 * when available space in SD for theme store is power CACHE_SIZE we should
	 * clear the space about theme store data
	 */
	private final static int CACHE_SIZE = 200 * 1024 * 1024;

	/**
	 * when available space in SD for theme store is low
	 * FREE_SD_SPACE_NEEDED_TO_CACHE, we should not use the SD for storage
	 * Attention: the cache size in megabytes
	 */
	private final static int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;

	/**
	 * 下载Image的线程池
	 */
	private ThreadPoolExecutor mImageThreadPoolRemote = null;
	private ThreadPoolExecutor mImageThreadPoolLocal = null;
	private HashMap<String, ImageDownloadTask> mActiveQueue; // 活跃任务队列
	private Queue<Runnable> mWaitTasksQueue = null; // 被reject的任务队列
	private Object mLock = new Object();
	private RejectedExecutionHandler mRejectedExecutionHandler = null; // 任务被拒绝执行的处理器
	private static ScheduledRunnable sScheduledRunnable = null; // 调度Runnable
	private static ScheduledExecutorService sScheduledExecutorService = null; // 调度线程池

	private ImageDataManager() {
		mActiveQueue = new HashMap<String, ImageDownloadTask>();
		mWaitTasksQueue = new ConcurrentLinkedQueue<Runnable>();
		if (sScheduledRunnable == null) {
			sScheduledRunnable = new ScheduledRunnable();
			sScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			sScheduledExecutorService.scheduleAtFixedRate(sScheduledRunnable, 0, 150,
					TimeUnit.MILLISECONDS);
		}
		initRejectedExecutionHandler();
	}

	public synchronized static ImageDataManager getInstance() {
		if (sInstance == null) {
			sInstance = new ImageDataManager();
		}
		return sInstance;
	}

	/**
	 * 获取线程池的方法，因为涉及到并发的问题，我们加上同步锁
	 * 
	 * @return
	 */
	public ThreadPoolExecutor getThreadPool(ThreadPoolType type) {
		synchronized (this) {
			ThreadPoolExecutor retval = null;
			if (type == ThreadPoolType.TYPE_LOCAL) {
				if (mImageThreadPoolLocal == null || mImageThreadPoolLocal.isShutdown()) {
					// 为了下载图片更加的流畅，我们用了3个线程来下载图片
					mImageThreadPoolLocal = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
							new ArrayBlockingQueue<Runnable>(30), mRejectedExecutionHandler);
				}
				retval = mImageThreadPoolLocal;
			} else {
				if (mImageThreadPoolRemote == null || mImageThreadPoolRemote.isShutdown()) {
					// 为了下载图片更加的流畅，我们用了3个线程来下载图片
					mImageThreadPoolRemote = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
							new ArrayBlockingQueue<Runnable>(30), mRejectedExecutionHandler);
				}
				retval = mImageThreadPoolRemote;
			}
			return retval;
		}
	}

	/**
	 * 先从内存缓存中获取Bitmap,如果没有就从SD卡或者手机缓存中获取，SD卡或者手机缓存 没有就去下载
	 * 
	 * @param url
	 * @param listener
	 * @return
	 */
	public void downloadImage(String url, int hashCode, OnImageLoaderListener listener) {
		synchronized (mLock) {
			ImageDownloadTask task = mActiveQueue.get(url);
			if (task != null) {
				task.addListener(hashCode, listener);
				return;
			}
			ThreadPoolExecutor executor = null;
			if (isLocalBitmapExist(url)) {
				File file = getBitmapSaveFile(url);
				executor = getThreadPool(ThreadPoolType.TYPE_LOCAL);
				task = new ImageDownloadTask(file, url);
			} else {
				executor = getThreadPool(ThreadPoolType.TYPE_REMOTE);
				task = new ImageDownloadTask(url);
			}
			task.addListener(hashCode, listener);
			mActiveQueue.put(url, task);
			executor.execute(task);
		}
	}

	/**
	 * 取消正在下载的任务
	 */
	public synchronized void cancelTask() {
		synchronized (mLock) {
			mActiveQueue.clear();
			mWaitTasksQueue.clear();
		}
		if (mImageThreadPoolRemote != null) {
			mImageThreadPoolRemote.shutdownNow();
			mImageThreadPoolRemote = null;
		}
		if (mImageThreadPoolLocal != null) {
			mImageThreadPoolLocal.shutdownNow();
			mImageThreadPoolLocal = null;
		}
	}

	/**
	 * 异步下载图片的回调接口
	 * 
	 */
	public interface OnImageLoaderListener {
		void onImageLoader(Bitmap bitmap, String url);
	}

	/**
	 * @param url
	 *            The URL of the image that will be retrieved from the cache.
	 * @return The cached bitmap or null if it was not found.
	 */
	public Bitmap getBitmapFromCache(String url) {
		if (TextUtils.isEmpty(url)) {
			return null;
		}
		Bitmap bitmap = sSoftBitmapCache.get(url);
		if (bitmap != null && !bitmap.isRecycled()) {
			return bitmap;
		} else {
			return null;
		}
	}

	/**
	 * 功能简述: 从sd卡加载图片
	 * 功能详细描述:
	 * 注意:
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromSdCard(String url) {
		Bitmap retval = null;
		if (!isSdExits()) {
			return null;
		}
		File file = getBitmapSaveFile(url);
		if (file.exists()) {
			retval = DisplayUtil.decodeBitmap(file.getAbsolutePath());
		}
		return retval;
	}

	/**
	 * 功能简述: 同步下载图片
	 * 功能详细描述:
	 * 注意:
	 * @param url
	 * @return
	 */
	public Bitmap downloadBitmapSync(String url) {
		Bitmap bitmap = null;
		InputStream stream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		long contentLength = 0;
		long readCount = 0;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams
					.setConnectionTimeout(httpClient.getParams(), 10 * 1000);
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), 15 * 1000);
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			Header headerContentLength = response.getFirstHeader("Content-Length");
			if (headerContentLength != null) {
				contentLength = Long.parseLong(TextUtils.isEmpty(headerContentLength
						.getValue()) ? "0" : headerContentLength.getValue());
			}
			int statusCode = response.getStatusLine().getStatusCode();
			stream = response.getEntity().getContent();
			if (statusCode == 200 || statusCode == 206) {
				byte[] buffer = new byte[2 * 1024];
				int byteCount = 0;
				while ((byteCount = stream.read(buffer)) > 0) {
					bos.write(buffer, 0, byteCount);
					readCount += byteCount;
				}
			}
		} catch (Exception e) {
		} catch (OutOfMemoryError e) {
		} finally {
			if (contentLength > 0 && readCount != contentLength) {
			} else {
				bitmap = DisplayUtil.decodeBitmap(bos.toByteArray());
			}
			try {
				if (stream != null) {
					stream.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
			}
		}
		return bitmap;
	}
	
	/**
	 * 功能简述: 获取 本地缓存文件
	 * 功能详细描述:
	 * 注意:
	 * @param url
	 * @return
	 */
	public File getBitmapSaveFile(String url) {
		File dir = new File(STORAGE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		hideMedia(STORAGE_PATH);
		String name = url;
		int startIndex = url.lastIndexOf("/");
		if (startIndex > 0) {
			name = url.substring(url.lastIndexOf("/") + 1);
		}
		File file = new File(STORAGE_PATH + "/" + name);
		return file;
	}

	/**
	 * 功能简述: 本地缓存文件是否存在
	 * 功能详细描述:
	 * 注意:
	 * @param url
	 * @return
	 */
	public boolean isLocalBitmapExist(String url) {
		boolean retval = false;
		File file = getBitmapSaveFile(url);
		retval = file.exists();
		return retval;
	}

	/**
	 * An InputStream that skips the exact number of bytes provided, unless it
	 * reaches EOF.
	 * 
	 */
	static class FlushedInputStream extends FilterInputStream {
		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int b = read();
					if (b < 0) {
						break; // we reached EOF
					} else {
						bytesSkipped = 1; // we read one byte
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}

	/**
	 * Adds this bitmap to the cache.
	 * 
	 * @param bitmap
	 *            The newly downloaded bitmap.
	 */
	public void addBitmapToCache(String url, Bitmap bitmap) {
		if (bitmap == null) {
			return;
		}
		sSoftBitmapCache.remove(url);
		//		sSoftBitmapCache.put(url, new SoftReference<Bitmap>(bitmap));
		sSoftBitmapCache.set(url, bitmap);
	}

	/**
	 * save bitmap to sdcard
	 * 
	 * @param bm
	 */
	private void saveBmpToSd(String url, Bitmap bitmap) {
		autoCleanCache(STORAGE_PATH);
		if (TextUtils.isEmpty(url) || bitmap == null) {
			return;
		}
		if (!isSdExits()) {
			return;
		}
		// if available space is below 50, return
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > getSdAvailable()) {
			return;
		}
		File file = getBitmapSaveFile(url);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		} catch (FileNotFoundException e) {
		} finally {
			if (outStream != null) {
				try {
					outStream.flush();
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 在媒体库中隐藏文件夹内的媒体文件 1. 加入.nomedia文件，使媒体功能扫描不到，用户可以通过文件浏览器方便看到 2.
	 * 在文件夹前面加点，隐藏整个文件夹，用户需要对文件浏览器设置显示点文件才能看到
	 * 
	 * @param folder
	 *            文件夹
	 */
	private void hideMedia(final String folder) {
		File file = new File(folder);
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(folder, ".nomedia");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
			}
		}
		file = null;
	}

	/**
	 * calculate size of the file, when file size > CACHE_SIZE or the available
	 * space for sdcard < FREE_SD_SPACE_NEEDED_TO_CACHE, delete 40% for unused
	 * files recently
	 * 
	 * @param dirPath
	 */
	private void autoCleanCache(String dirPath) {
		if (!isSdExits()) {
			return;
		}
		hideMedia(dirPath);
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length <= 0) {
			return;
		}
		long dirSize = 0;
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			dirSize += file.length();
		}
		if (dirSize > CACHE_SIZE
				|| FREE_SD_SPACE_NEEDED_TO_CACHE > getSdAvailable()) {
			int removeFactor = (int) ((0.4 * files.length) + 1);
			Arrays.sort(files, new FileLastModifSort());
			for (int i = 0; i < removeFactor; i++) {
				files[i].delete();
			}
		}
	}

	/**
	 * sort file based on latest modify time
	 * 
	 * 
	 */
	class FileLastModifSort implements Comparator<File> {
		@Override
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * judge exists for SD
	 * 
	 * @return
	 */
	public static boolean isSdExits() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * obtain extra space size for SD card
	 * 
	 * @return
	 */
	private static int getSdAvailable() {
		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		double sdFreeMB = (availableBlocks * blockSize) / 1024d / 1024d;
		return (int) sdFreeMB;
	}

	private void executeWaitTask() {
		synchronized (mLock) {
			if (hasMoreWaitTask()) {
				Runnable runnable = mWaitTasksQueue.poll();
				if (runnable != null) {
					ThreadPoolType threadPoolType = ThreadPoolType.TYPE_LOCAL;
					if (runnable instanceof ImageDownloadTask) {
						DownloadTaskType taskType = ((ImageDownloadTask) runnable).getTaskType();
						if (taskType == DownloadTaskType.TASKTYPE_URL) {
							threadPoolType = ThreadPoolType.TYPE_REMOTE;
						}
					}
					getThreadPool(threadPoolType).execute(runnable);
				}
			}
		}
	}
	
	/**
	 * 初始化任务被拒绝执行的处理器的方法
	 */
	private void initRejectedExecutionHandler() {
		mRejectedExecutionHandler = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// 把被拒绝的任务重新放入到等待队列中
				synchronized (mLock) {
					mWaitTasksQueue.offer(r);
				}
			}
		};
	}

	/**
	 * 是否还有等待任务的方法
	 * 
	 * @return
	 */
	public boolean hasMoreWaitTask() {
		boolean result = false;
		if (!mWaitTasksQueue.isEmpty()) {
			result = true;
		}
		return result;
	}

	public boolean removeTask(int hashCode, String url) {
		if (TextUtils.isEmpty(url)) {
			return false;
		}
	
		synchronized (mLock) {
			boolean needRemove = true;
			ImageDownloadTask task = mActiveQueue.get(url);
			if (task != null) {
				needRemove = task.removeListener(hashCode);
			}
			if (needRemove) {
				mActiveQueue.remove(url);
				if (mWaitTasksQueue.contains(task)) {
					mWaitTasksQueue.remove(task);
				}
				if (mImageThreadPoolRemote != null) {
					mImageThreadPoolRemote.remove(task);
				}
				if (mImageThreadPoolLocal != null) {
					mImageThreadPoolLocal.remove(task);
				}
			}	
			return needRemove;
		}
	}

	public void destory() {
		cancelTask();
		sInstance = null;
		if (sScheduledRunnable != null) {
			sScheduledRunnable = null;
		}	
		mRejectedExecutionHandler = null;
	}
	
	/**
	 * 初始化调度Runable
	 */
	private class ScheduledRunnable implements Runnable {
		@Override
		public void run() {
			executeWaitTask();
		}
	}
	
	/**
	 * 
	 * 类描述:
	 * 功能详细描述:
	 * 
	 */
	private enum DownloadTaskType {
		TASKTYPE_URL,
		TASKTYPE_FILE
	}
	
	/**
	 * 类描述: 
	 * 功能详细描述: 
	 */
	private class ImageDownloadTask implements Runnable {
		private SparseArray<OnImageLoaderListener> mListeners;
		private String mUrl;
		private File mFile;
		private DownloadTaskType mTaskType;

		private ImageDownloadTask(File file, String url) {
			mUrl = url;				// 回调函数需要用到url
			mFile = file;	
			mTaskType = DownloadTaskType.TASKTYPE_FILE;
			mListeners = new SparseArray<ImageDataManager.OnImageLoaderListener>();
		}

		private ImageDownloadTask(String url) {
			mUrl = url;
			mTaskType = DownloadTaskType.TASKTYPE_URL;
			mListeners = new SparseArray<ImageDataManager.OnImageLoaderListener>();
		}

		public DownloadTaskType getTaskType() {
			return mTaskType;
		}

		public void addListener(int hashCode, OnImageLoaderListener listener) {
			if (mListeners.get(hashCode) == null) {
				mListeners.put(hashCode, listener);
			}	
		}

		/**
		 * @param hashCode
		 * @return true listener列表为空，可以移除整个task
		 */
		public boolean removeListener(int hashCode) {
			mListeners.delete(hashCode);
			return mListeners.size() == 0;
		}

		@Override
		public void run() {
			// Bitmap bitmap = getBitmapFromSdcard(mUrl);
			Bitmap bitmap = null;
			if (mTaskType == DownloadTaskType.TASKTYPE_FILE) {
				if (mFile.exists()) {
					bitmap = DisplayUtil.decodeBitmap(mFile.getAbsolutePath());
				}
			} else if (mTaskType == DownloadTaskType.TASKTYPE_URL) {
				File file = getBitmapSaveFile(mUrl);
				if (file.exists()) {
					bitmap = DisplayUtil.decodeBitmap(file.getAbsolutePath());
				}
				if (bitmap == null || bitmap.isRecycled()) {
					file.delete();
					bitmap = downloadBitmapSync(mUrl);
				}
				if (bitmap != null) {
					addBitmapToCache(mUrl, bitmap);
					saveBmpToSd(mUrl, bitmap);
				} else {
				}
					
			}
			for (int i = 0; i < mListeners.size(); i++) {
				OnImageLoaderListener listener = mListeners.valueAt(i);
				if (listener != null) {
					listener.onImageLoader(bitmap, mUrl);
				}
			}
			mActiveQueue.remove(mUrl);
		}

	}

}
