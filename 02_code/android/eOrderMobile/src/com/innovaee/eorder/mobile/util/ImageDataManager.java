/***********************************************
 * Filename    : ImageDataManager.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
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
import android.util.Log;
import android.util.SparseArray;
	
import com.innovaee.eorder.mobile.util.LruImageCache;

/**
 * 图片管理器 管理图片，存储图片到缓存和sdcard,使用时从缓存或者sdcard读取图片
 * 
 * 
 */
public class ImageDataManager {
    //线程池类型
    public static enum ThreadPoolType {
        //用于加载本地图片
        TYPE_LOCAL, 
        
        //用于下载远程图片
        TYPE_REMOTE 
    }

    //默认小大
    private static final int DEFAULT_SIZE = 5 * 1024 * 1024;

    //SDcard存储地址
    private static final String STORAGE_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/eOrderMobile";

    //缓存
    private static LruImageCache softBitmapCache = new LruImageCache(
            DEFAULT_SIZE);

    //ImageDataManager单例
    private static ImageDataManager instance = null;

    //缓存小大
    private static final int CACHE_SIZE = 200 * 1024 * 1024;
    
    //当sd空间小于FREE_SD_SPACE_NEEDED_TO_CACHE，将不在使用sd空间
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;

    //下载Image的线程池
    private ThreadPoolExecutor imageThreadPoolRemote = null;
    
    //下载Image的本地线程池
    private ThreadPoolExecutor imageThreadPoolLocal = null;
    
    //活跃任务队列
    private HashMap<String, ImageDownloadTask> activeQueue;
    
    //被reject的任务队列
    private Queue<Runnable> waitTasksQueue = null; 
    
    //锁
    private Object lock = new Object();
    
    //任务被拒绝执行的处理器
    private RejectedExecutionHandler rejectedExecutionHandler = null;
    
    //调度Runnable
    private static ScheduledRunnable scheduledRunnable = null; 
    
    //调度线程池
    private static ScheduledExecutorService scheduledExecutorService = null; 

    /**
     * 构造函数
     */
    private ImageDataManager() {
        activeQueue = new HashMap<String, ImageDownloadTask>();
        waitTasksQueue = new ConcurrentLinkedQueue<Runnable>();
        if (scheduledRunnable == null) {
            scheduledRunnable = new ScheduledRunnable();
            scheduledExecutorService = Executors
                    .newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(scheduledRunnable,
                    0, 150, TimeUnit.MILLISECONDS);
        }
        initRejectedExecutionHandler();
    }

    /**
     * 获取ImageDataManager对象
     * @return ImageDataManager对象
     */
    public synchronized static ImageDataManager getInstance() {
        if (instance == null) {
            instance = new ImageDataManager();
        }
        return instance;
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
                if (imageThreadPoolLocal == null
                        || imageThreadPoolLocal.isShutdown()) {
                    //为了下载图片更加的流畅，我们用了3个线程来下载图片
                    imageThreadPoolLocal = new ThreadPoolExecutor(3, 5, 10,
                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
                                    30), rejectedExecutionHandler);
                }
                retval = imageThreadPoolLocal;
            } else {
                if (imageThreadPoolRemote == null
                        || imageThreadPoolRemote.isShutdown()) {
                    //为了下载图片更加的流畅，我们用了3个线程来下载图片
                    imageThreadPoolRemote = new ThreadPoolExecutor(3, 5, 10,
                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
                                    30), rejectedExecutionHandler);
                }
                retval = imageThreadPoolRemote;
            }
            return retval;
        }
    }

    /**
     * 先从内存缓存中获取Bitmap,如果没有就从SD卡或者手机缓存中获取，SD卡或者手机缓存 没有就去下载
     * 
     * @param url 图片地址
     * @param listener 监听器
     */
    public void downloadImage(String url, int hashCode,
            OnImageLoaderListener listener) {
        synchronized (lock) {
            ImageDownloadTask task = activeQueue.get(url);
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
            activeQueue.put(url, task);
            executor.execute(task);
        }
    }

    /**
     * 取消正在下载的任务
     */
    public synchronized void cancelTask() {
        synchronized (lock) {
            activeQueue.clear();
            waitTasksQueue.clear();
        }
        if (imageThreadPoolRemote != null) {
            imageThreadPoolRemote.shutdownNow();
            imageThreadPoolRemote = null;
        }
        if (imageThreadPoolLocal != null) {
            imageThreadPoolLocal.shutdownNow();
            imageThreadPoolLocal = null;
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
     * @param url 图片地址
     * @return 图片对象
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

    /**
     * 功能简述: 从sd卡加载图片  
     * @param url 图片地址
     * @return 图片对象
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
     * @param url 图片地址
     * @return 图片对象
     */
    public Bitmap downloadBitmapSync(String url) {
        Bitmap bitmap = null;
        InputStream stream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        long contentLength = 0;
        long readCount = 0;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
                    10 * 1000);
            HttpConnectionParams
                    .setSoTimeout(httpClient.getParams(), 15 * 1000);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            Header headerContentLength = response
                    .getFirstHeader("Content-Length");
            if (headerContentLength != null) {
                contentLength = Long.parseLong(TextUtils
                        .isEmpty(headerContentLength.getValue()) ? "0"
                        : headerContentLength.getValue());
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
        } catch (Exception error) {
            Log.e("downloadBitmapSync", "error!");
        } finally {
            if (contentLength > 0 && readCount != contentLength) {
            	Log.d("downloadBitmapSync", "readCount != contentLength");
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
            } catch (IOException error) {
            	Log.e("DownloadService", error.toString());
            }
        }
        return bitmap;
    }

    /**
     * 功能简述: 获取本地缓存文件 
     * @param url 图片地地址
     * @return 图片文件
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
     * @param url 图片地址
     * @return 是否存在
     */
    public boolean isLocalBitmapExist(String url) {
        boolean retval = false;
        File file = getBitmapSaveFile(url);
        retval = file.exists();
        return retval;
    }

    /**
     * InputStream跳过指定直接数
     */
    static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long number) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < number) {
                long bytesSkipped = in.skip(number - totalBytesSkipped);
                if (bytesSkipped == 0L) {
                    int b = read();
                    if (b < 0) {
                        break; 
                    } else {
                        bytesSkipped = 1; 
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }

    /**
     * 把图片加到缓存
     * @param url 图片地址
     * @param bitmap 图片对象
     */    
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        softBitmapCache.remove(url);
        softBitmapCache.set(url, bitmap);
    }

    /**
     * 保存图片到sdcard
     * @param url 图片地址
     * @param bm 图片对象
     */
    private void saveBmpToSd(String url, Bitmap bitmap) {
        autoCleanCache(STORAGE_PATH);
        if (TextUtils.isEmpty(url) || bitmap == null) {
            return;
        }
        if (!isSdExits()) {
            return;
        }

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
        } catch (FileNotFoundException error) {
            Log.e("saveBmpToSd", "error!");
        } finally {    
            if (outStream != null) {
                try {
                    outStream.flush();
                    outStream.close();
                } catch (IOException error) {
                	Log.d("ImageDataManager", error.toString());
                }
            }
        }
    }

    /**
     * 在媒体库中隐藏文件夹内的媒体文件 
     * 1. 加入.nomedia文件，使媒体功能扫描不到，用户可以通过文件浏览器方便看到 
     * 2.在文件夹前面加点，隐藏整个文件夹，用户需要对文件浏览器设置显示点文件才能看到 
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
            } catch (Exception error) {
                Log.e("hideMedia", "error!");
            }
        }
        file = null;
    }

    /**
     * 自动清除缓存 
     * @param dirPath 地址
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
     * 按最后修改时间排列文件
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
     * 判断是否存在sd卡
     * @return 是否存在
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
     * 获取sd卡剩余空间
     * @return sd卡剩余空间
     */
    private static int getSdAvailable() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        double sdFreeMB = (availableBlocks * blockSize) / 1024d / 1024d;
        return (int) sdFreeMB;
    }

    /**
     * 下载任务
     */
    private void executeWaitTask() {
        synchronized (lock) {
            if (hasMoreWaitTask()) {
                Runnable runnable = waitTasksQueue.poll();
                if (runnable != null) {
                    ThreadPoolType threadPoolType = ThreadPoolType.TYPE_LOCAL;
                    if (runnable instanceof ImageDownloadTask) {
                        DownloadTaskType taskType = ((ImageDownloadTask) runnable)
                                .getTaskType();
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
        rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable,
                    ThreadPoolExecutor executor) {
                //把被拒绝的任务重新放入到等待队列中
                synchronized (lock) {
                    waitTasksQueue.offer(runnable);
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

    /**
     * 删除任务
     * @param hashCode 任务id
     * @param url 图片地址
     * @return 是否删除成功
     */
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
                if (imageThreadPoolRemote != null) {
                    imageThreadPoolRemote.remove(task);
                }
                if (imageThreadPoolLocal != null) {
                    imageThreadPoolLocal.remove(task);
                }
            }
            return needRemove;
        }
    }

    /**
     * 销毁函数
     */
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
     * 类描述: 功能详细描述:
     * 
     */
    private enum DownloadTaskType {
        TASKTYPE_URL, TASKTYPE_FILE
    }

    /**
     * 类描述: 功能详细描述:
     */
    private class ImageDownloadTask implements Runnable {
        private SparseArray<OnImageLoaderListener> listeners;
        private String url;
        private File file;
        private DownloadTaskType taskType;

        private ImageDownloadTask(File Downloadfile, String urlStr) {
            url = urlStr; // 回调函数需要用到url
            file = Downloadfile;
            taskType = DownloadTaskType.TASKTYPE_FILE;
            listeners = new SparseArray<ImageDataManager.OnImageLoaderListener>();
        }

        private ImageDownloadTask(String urlStr) {
            url = urlStr;
            taskType = DownloadTaskType.TASKTYPE_URL;
            listeners = new SparseArray<ImageDataManager.OnImageLoaderListener>();
        }

        public DownloadTaskType getTaskType() {
            return taskType;
        }

        public void addListener(int hashCode, OnImageLoaderListener listener) {
            if (listeners.get(hashCode) == null) {
                listeners.put(hashCode, listener);
            }
        }

        /**
         * @param hashCode
         * @return true listener列表为空，可以移除整个task
         */
        public boolean removeListener(int hashCode) {
            listeners.delete(hashCode);
            return listeners.size() == 0;
        }

        @Override
        public void run() {
            Bitmap bitmap = null;
            if (taskType == DownloadTaskType.TASKTYPE_FILE) {
                if (file.exists()) {
                    bitmap = DisplayUtil.decodeBitmap(file.getAbsolutePath());
                }
            } else if (taskType == DownloadTaskType.TASKTYPE_URL) {
                File file = getBitmapSaveFile(url);
                if (file.exists()) {
                    bitmap = DisplayUtil.decodeBitmap(file.getAbsolutePath());
                }
                if (bitmap == null || bitmap.isRecycled()) {
                    file.delete();
                    bitmap = downloadBitmapSync(url);
                }
                if (bitmap != null) {
                    addBitmapToCache(url, bitmap);
                    saveBmpToSd(url, bitmap);
                }	 
            }	
            for (int i = 0; i < listeners.size(); i++) {
                OnImageLoaderListener listener = listeners.valueAt(i);
                if (listener != null) {
                    listener.onImageLoader(bitmap, url);
                }
            }
            activeQueue.remove(url);
        }

    }

}
