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
import android.util.Log;
import android.util.SparseArray;

/**
 * theme image manager, contain restore to cash, sdcard, etc
 * 
 * @author majunwen
 * 
 */
public class ThemeImageManager {

	private static final int DEFAULT_SIZE = 5 * 1024 * 1024;

	/**
	 * SDcard storage path
	 */
	private final static String STORAGE_PATH = Environment
			.getExternalStorageDirectory().getPath()
			+ "/GOLauncherEX_ThemeStore";

	/**
	 * Soft cache for bitmaps kicked out of hard cache
	 */
	// private static LruCache<String, SoftReference<Bitmap>> softBitmapCache =
	// new LruCache<String, SoftReference<Bitmap>>(
	// 5);

	private static LruImageCache softBitmapCache = new LruImageCache(
			DEFAULT_SIZE);

	private static ThemeImageManager instance = null;

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
	private ThreadPoolExecutor imageThreadPool = null;
	private HashMap<String, ImageDownloadTask> activeQueue; // 活跃任务队列
	private Queue<Runnable> waitTasksQueue = null; // 被reject的任务队列
	private Object lock = new Object();
	private RejectedExecutionHandler rejectedExecutionHandler = null; // 任务被拒绝执行的处理器
	private static ScheduledRunnable scheduledRunnable = null; // 调度Runnable
	private static ScheduledExecutorService scheduledExecutorService = null; // 调度线程池

	private ThemeImageManager() {
		activeQueue = new HashMap<String, ImageDownloadTask>();
		waitTasksQueue = new ConcurrentLinkedQueue<Runnable>();
		if (scheduledRunnable == null) {
			scheduledRunnable = new ScheduledRunnable();
			scheduledExecutorService = Executors
					.newSingleThreadScheduledExecutor();
			scheduledExecutorService.scheduleAtFixedRate(scheduledRunnable, 0,
					150, TimeUnit.MILLISECONDS);
		}
		initRejectedExecutionHandler();
	}

	public synchronized static ThemeImageManager getInstance() {
		if (instance == null) {
			instance = new ThemeImageManager();
		}
		return instance;
	}

	/**
	 * 获取线程池的方法，因为涉及到并发的问题，我们加上同步锁
	 * 
	 * @return
	 */
	public ThreadPoolExecutor getThreadPool() {
		synchronized (this) {
			if (imageThreadPool == null || imageThreadPool.isShutdown()) {
				// 为了下载图片更加的流畅，我们用了3个线程来下载图片
				imageThreadPool = new ThreadPoolExecutor(3, 5, 10,
						TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30),
						rejectedExecutionHandler);
			}
			return imageThreadPool;
		}
	}

	/**
	 * 通过URL获取图片
	 * 
	 * @param url
	 * @return
	 */
	/*
	 * public Bitmap getBitmap(String url) { Bitmap bitmap =
	 * getBitmapFromCache(url); if (null == bitmap) { bitmap =
	 * getBitmapFromNet(url); } return bitmap; }
	 */

	/**
	 * 先从内存缓存中获取Bitmap,如果没有就从SD卡或者手机缓存中获取，SD卡或者手机缓存 没有就去下载
	 * 
	 * @param url
	 * @param listener
	 * @return
	 */
	public void downloadImage(String url, int hashCode,
			OnImageLoaderListener listener) {
		synchronized (lock) {
			ImageDownloadTask task = activeQueue.get(url);
			if (task != null) {
				task.addListener(hashCode, listener);
				Log.i("RemoteImage", "downloadImage "
						+ " return, duplex task, url:" + url
						+ ", add new listener to task");
				return;
			}
			task = new ImageDownloadTask(url);
			task.addListener(hashCode, listener);
			activeQueue.put(url, task);
			getThreadPool().execute(task);
		}
	}

	/**
	 * 从网络以Url获取Bitmap
	 * 
	 * @param url
	 * @return
	 */
	/*
	 * private Bitmap getBitmapFromNet(String url) { if (TextUtils.isEmpty(url))
	 * { return null; } Bitmap bitmap = null; InputStream stream = null;
	 * HttpURLConnection conn = null; try { conn = (HttpURLConnection) new
	 * URL(url).openConnection(); conn.setConnectTimeout(15 * 1000);
	 * conn.setReadTimeout(15 * 1000); int code = conn.getResponseCode(); if
	 * (code == 200) { stream = conn.getInputStream(); bitmap =
	 * ThemeStoreDisplayUtil.decodeBitmap(stream); } try { if (bitmap != null) {
	 * addBitmapToCache(url, bitmap); saveBmpToSd(url, bitmap);
	 * Log.d("RemoteImage", "Image cached " + url); } else {
	 * Log.w("RemoteImage", "Failed to cache " + url); } } catch
	 * (NullPointerException e) { Log.w("RemoteImage", "Failed to cache " +
	 * url); } } catch (Exception e) { Log.w("RemoteImage",
	 * "Couldn't load bitmap from url: " + url); } finally { try { if (stream !=
	 * null) { stream.close(); } } catch (IOException e) {
	 * 
	 * } } return bitmap; }
	 */

	/**
	 * 取消正在下载的任务
	 */
	public synchronized void cancelTask() {
		synchronized (lock) {
			activeQueue.clear();
			waitTasksQueue.clear();
		}
		if (imageThreadPool != null) {
			imageThreadPool.shutdownNow();
			imageThreadPool = null;
		}
	}

	/**
	 * 异步下载图片的回调接口
	 * 
	 * @author majunwen
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
		Bitmap bitmap = softBitmapCache.get(url);
		if (bitmap != null && !bitmap.isRecycled()) {
			return bitmap;
		} else {
			return null;
		}
	}

	public Bitmap getBitmapFromSdCard(String url) {
		Bitmap retval = null;
		if (!isSdExits()) {
			return null;
		}
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
		if (file.exists()) {
			retval = DisplayUtil.decodeBitmap(file.getAbsolutePath());
		}
		return retval;
	}

	/**
	 * An InputStream that skips the exact number of bytes provided, unless it
	 * reaches EOF.
	 * 
	 * @author majunwen
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
		softBitmapCache.remove(url);
		// softBitmapCache.put(url, new SoftReference<Bitmap>(bitmap));
		softBitmapCache.set(url, bitmap);
	}

	/**
	 * save bitmap to sdcard
	 * 
	 * @param bm
	 */
	private void saveBmpToSd(String url, Bitmap bitmap) {
		autoCleanCache(STORAGE_PATH);
		if (TextUtils.isEmpty(url) || bitmap == null) {
			Log.d("mjw", " trying to savenull bitmap");
			return;
		}
		if (!isSdExits()) {
			return;
		}
		// if available space is below 50, return
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > getSdAvailable()) {
			Log.w("mjw", "Low free space onsd, do not cache");
			return;
		}
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
		File file = new File(dir, name);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			Log.i("mjw", "Image saved tosd");
		} catch (FileNotFoundException e) {
			Log.w("mjw", "FileNotFoundException");
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
				Log.i("RemoteImage", "hideMediaInFolder error, folder = "
						+ folder);
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
			Log.i("RemoteImage", "Clear some expiredcache files ");
			for (int i = 0; i < removeFactor; i++) {
				files[i].delete();
			}
		}
	}

	/**
	 * sort file based on latest modify time
	 * 
	 * @author majunwen
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
		synchronized (lock) {
			if (hasMoreWaitTask()) {
				Runnable runnable = waitTasksQueue.poll();
				if (runnable != null) {
					Log.i("RemoteImage", "downloadImage, executeWaitTask "
							+ runnable.toString());
					getThreadPool().execute(runnable);
				}
			}
		}
	}

	/**
	 * 初始化任务被拒绝执行的处理器的方法
	 */
	private void initRejectedExecutionHandler() {
		rejectedExecutionHandler = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r,
					ThreadPoolExecutor executor) {
				// 把被拒绝的任务重新放入到等待队列中
				synchronized (lock) {
					Log.i("RemoteImage",
							"downloadImage, rejectedExecution, add to waitTasksQueue: "
									+ r.toString());
					waitTasksQueue.offer(r);
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
		if (!waitTasksQueue.isEmpty()) {
			result = true;
		}
		return result;
	}

	public boolean removeTask(int hashCode, String url) {
		if (TextUtils.isEmpty(url)) {
			return false;
		}
		synchronized (lock) {
			boolean needRemove = true;
			ImageDownloadTask task = activeQueue.get(url);
			if (task != null) {
				needRemove = task.removeListener(hashCode);
			}
			if (needRemove) {
				activeQueue.remove(url);
				if (waitTasksQueue.contains(task)) {
					waitTasksQueue.remove(task);
				}
				imageThreadPool.remove(task);
			}
			return needRemove;
		}
	}

	public void destory() {
		cancelTask();
		instance = null;
		if (scheduledRunnable != null) {
			scheduledRunnable = null;
		}
		rejectedExecutionHandler = null;
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
	 * <br>
	 * 类描述: <br>
	 * 功能详细描述:
	 * 
	 * @author lichong
	 * @date [2014年11月19日]
	 */
	private enum DownloadTaskType {
		TASKTYPE_URL, TASKTYPE_FILE
	}

	/**
	 * 
	 * <br>
	 * 类描述: <br>
	 * 功能详细描述:
	 * 
	 * @author lichong
	 * @date [2014年9月30日]
	 */
	private class ImageDownloadTask implements Runnable {
		private SparseArray<OnImageLoaderListener> mListeners;
		private String mUrl;
		private File mFile;
		private DownloadTaskType mTaskType;

		private ImageDownloadTask(File file) {
			mFile = file;
			mTaskType = DownloadTaskType.TASKTYPE_FILE;
			mListeners = new SparseArray<ThemeImageManager.OnImageLoaderListener>();
		}

		private ImageDownloadTask(String url) {
			mUrl = url;
			mTaskType = DownloadTaskType.TASKTYPE_URL;
			mListeners = new SparseArray<ThemeImageManager.OnImageLoaderListener>();
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
				String filename = mUrl.substring(mUrl.lastIndexOf("/") + 1);
				File file = new File(STORAGE_PATH + "/" + filename);
				if (file.exists()) {
					bitmap = DisplayUtil.decodeBitmap(file.getAbsolutePath());
				}
				if (bitmap == null || bitmap.isRecycled()) {
					file.delete();
					InputStream stream = null;
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					long contentLength = 0;
					long readCount = 0;
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpConnectionParams.setConnectionTimeout(
								httpClient.getParams(), 10 * 1000);
						HttpConnectionParams.setSoTimeout(
								httpClient.getParams(), 15 * 1000);
						HttpGet httpGet = new HttpGet(mUrl);
						HttpResponse response = httpClient.execute(httpGet);
						Header headerContentLength = response
								.getFirstHeader("Content-Length");
						if (headerContentLength != null) {
							contentLength = Long
									.parseLong(TextUtils
											.isEmpty(headerContentLength
													.getValue()) ? "0"
											: headerContentLength.getValue());
						}
						int statusCode = response.getStatusLine()
								.getStatusCode();
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
						Log.w("RemoteImage", "Couldn't load bitmap from url: "
								+ mUrl + ", exception:" + e.toString());
					} catch (OutOfMemoryError e) {
						Log.w("RemoteImage", "Couldn't load bitmap from url: "
								+ mUrl + ", exception:" + e.toString());
					} finally {
						if (contentLength > 0 && readCount != contentLength) {
							Log.d("RemoteImage",
									"Content-Length error, contentLength:"
											+ contentLength + ", readLength:"
											+ readCount);
						} else {
							bitmap = DisplayUtil
									.decodeBitmap(bos.toByteArray());
						}
						if (bitmap != null) {
							addBitmapToCache(mUrl, bitmap);
							saveBmpToSd(mUrl, bitmap);
							Log.d("RemoteImage", "Image cached " + mUrl);
						} else {
							Log.w("RemoteImage", "Failed to cache " + mUrl);
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
				}
			}
			for (int i = 0; i < mListeners.size(); i++) {
				OnImageLoaderListener listener = mListeners.valueAt(i);
				if (listener != null) {
					listener.onImageLoader(bitmap, mUrl);
				}
			}
			activeQueue.remove(mUrl);
		}

	}

}
