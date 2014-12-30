/***********************************************
 * Filename    : RemoteImageView.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.RejectedExecutionException;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.util.ImageDataManager.OnImageLoaderListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * ImageView extended class allowing easy downloading of remote images
 * 
 */
public class RemoteImageView extends ImageView implements IForeground {

    private static final int STATE_LOADING = 0x01;
    private static final int STATE_SUCCESS = 0x02;
    private static final int STATE_ERROR = 0x03;

    private int mState;

    // Hard cache, with a fixed maximum capacity and a life duration
    // private static final LinkedList<Bitmap> sHardBitmapCache = new
    // LinkedList<Bitmap>();
    private final Map<ImageView, String> mImageViews = Collections
            .synchronizedMap(new WeakHashMap<ImageView, String>());

    private Handler mUiHandler;

    /**
     * Position of the image in the mListView
     */
    private int mPosition;

    /**
     * ListView containg this image
     */
    private ListView mListView;

    /**
     * Default image shown while loading or on url not found
     */
    private Integer mDefaultImage;

    /**
     * Image manager
     */
    private ImageDataManager mThemeImageManager;

    private ImageView.ScaleType mScaleTypeDefault;
    private ImageView.ScaleType mScaleTypeContent;
    /**
     * 默认高宽比
     */
    private float mDefaultAspect;
    /**
     * 内容填充高款比
     */
    private float mCurAspect;
    /**
     * 是否根据图片的高款进行高款比适配
     */
    private boolean mApplyAspect;
    /**
     * 计算大小时候，高款比参照物大小
     */
    private int mAspectSize;
    private AspectRef mAspectRef;

    private ForegroundAdapter mFgAdapter;

    /**
     * 计算大小时候，高款比参照物
     */
    public static enum AspectRef {
        WIDTH, HEIGHT;
    }

    public RemoteImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RemoteImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RemoteImageView(Context context) {
        super(context);
        init();
    }

    /**
     * Sharable code between constructors
     */
    private void init() {
        mFgAdapter = new ForegroundAdapter(getContext(), this);
        mFgAdapter.setForegroundDrawable(null); // 默认禁用前景绘制
        mUiHandler = new Handler(Looper.getMainLooper());
        mDefaultImage = R.drawable.themestore_common_default_pic;
        mThemeImageManager = ImageDataManager.getInstance();
        mScaleTypeDefault = ScaleType.FIT_XY;
        mScaleTypeContent = ScaleType.CENTER_CROP;
        mApplyAspect = false;
        mDefaultAspect = 1.0f;
        mCurAspect = 1.0f;
        mAspectSize = 0;
        mAspectRef = AspectRef.HEIGHT;
    }

    public void setIsApplyAspect(boolean apply) {
        mApplyAspect = apply;
    }

    public void setAspectSize(AspectRef ref, int size) {
        mAspectRef = ref;
        mAspectSize = size;
    }

    public void setDefaultAspect(float defaultDefault) {
        mDefaultAspect = defaultDefault;
    }

    /**
     * 功能简述: 预先给视图绑定一个url，后续触发加载 功能详细描述: 注意:
     * 
     * @param url
     */
    public void prepareImageUrl(String url) {
        loadDefaultImage();
        if (!TextUtils.isEmpty(url)) {
            mImageViews.put(this, url);
        }
    }

    /**
     * Loads image from remote location
     * 
     * @param url
     *            eg. http://random.com/abz.jpg
     */
    public void setImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultImage();
            return;
        }
        String oldUrl = mImageViews.get(this);
        if (url.equals(oldUrl) && mState == STATE_SUCCESS) {
            return;
        }
        removeWaitingTask(oldUrl);
        mImageViews.put(this, url);
        Bitmap bitmap = mThemeImageManager.getBitmapFromCache(url);
        if (bitmap != null) {
            setImageBitmap(url, bitmap);
        } else {
            loadDefaultImage();
            try {
                mThemeImageManager.downloadImage(url, hashCode(),
                        new OnImageLoaderListener() {
                            @Override
                            public void onImageLoader(final Bitmap bitmap,
                                    final String url) {
                                if (bitmap != null) {
                                    mUiHandler.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            setImageBitmap(url, bitmap);
                                        }
                                    });
                                }
                            }
                        });

            } catch (RejectedExecutionException e) {
                // do nothing, just don't crash
            }
        }
    }

    /**
     * 设置bitmap
     * 
     * @param url
     *            url地址
     * @param bitmap
     *            bitmap对象
     */
    private void setImageBitmap(String url, Bitmap bitmap) {
        mState = STATE_ERROR;
        String tag = mImageViews.get(RemoteImageView.this);
        if (tag != null && tag.equals(url)) {
            if (bitmap == null) {
                loadDefaultImage();
            } else {
                // if image belongs to a list update it only if it's visible
                if (mListView != null) {
                    if (mPosition < mListView.getFirstVisiblePosition()
                            || mPosition > mListView.getLastVisiblePosition()) {
                        return;
                    }
                }
                mState = STATE_SUCCESS;
                int width = getWidth() - getPaddingLeft() - getPaddingRight();
                int height = getHeight() - getPaddingTop() - getPaddingBottom();
                int bitmapH = bitmap.getHeight();
                int bitmapW = bitmap.getWidth();
                float newAapect = (float) bitmapH / bitmapW;
                if (mApplyAspect) {
                    if (Math.abs(newAapect - mCurAspect) > 0.000001f) {
                        mCurAspect = newAapect;
                        requestLayout();
                    }
                } else if (width > 0 && height > 0
                        && (width < bitmapW || height < bitmapH)) {
                    float scale = Math.min((float) width / bitmapW,
                            (float) height / bitmapH);
                    int desWidth = Math.min(bitmapW,
                            Math.round(bitmapW * scale));
                    int desHeight = Math.min(bitmapH,
                            Math.round(bitmapH * scale));
                    bitmap = Bitmap.createScaledBitmap(bitmap, desWidth,
                            desHeight, false);
                }
                BitmapDrawable bmpDrawable = new BitmapDrawable(getResources(),
                        bitmap);
                if (!mApplyAspect && mScaleTypeContent == ScaleType.MATRIX) {
                    setImageMatrix(bmpDrawable.getIntrinsicWidth(),
                            bmpDrawable.getIntrinsicHeight(), width, height);
                }
                RemoteImageView.this.setScaleType(mScaleTypeContent);
                RemoteImageView.this.setImageDrawable(bmpDrawable);
            }
        }
    }

    /**
     * 系统调用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mApplyAspect) {
            int width;
            int height;
            if (mAspectRef == AspectRef.WIDTH) {
                width = mAspectSize;
                height = (int) (width * mCurAspect);
            } else {
                height = mAspectSize;
                width = (int) (height / mCurAspect);
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        if (!mApplyAspect && getScaleType() == ScaleType.MATRIX
                && getDrawable() != null) {
            Drawable drawable = getDrawable();
            setImageMatrix(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), getMeasuredWidth(),
                    getMeasuredHeight());
        }
    }

    /**
     * get bitmap from the method
     * 
     * @param url
     * @return
     */
    /*
     * public Bitmap getImageUsingUrl(String url) { return
     * mThemeImageManager.getBitmap(url); }
     */

    /**
     * Sets default local image shown when remote one is unavailable
     * 
     * @param resid
     */
    public void setDefaultImage(Integer resid) {
        mDefaultImage = resid;
    }

    /**
     * 设置scale类型
     * 
     * @param scaleType
     *            scale类型
     */
    public void setScaleTypeDefault(ImageView.ScaleType scaleType) {
        mScaleTypeDefault = scaleType;
    }

    /**
     * 设置scale类型
     * 
     * @param scaleType
     *            scale类型
     */
    public void setScaleTypeContent(ImageView.ScaleType scaleType) {
        mScaleTypeContent = scaleType;
    }

    /**
     * Loads default image
     */
    private void loadDefaultImage() {
        if (mState == STATE_LOADING) {
            return;
        }
        mState = STATE_LOADING;
        if (mDefaultImage != null) {
            setScaleType(mScaleTypeDefault);
            setImageResource(mDefaultImage);
        }
        if (mApplyAspect && Math.abs(mDefaultAspect - mCurAspect) > .000001f) {
            mCurAspect = mDefaultAspect;
            requestLayout();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
    }

    @Override
    public void dispatchDisplayHint(int hint) {
        super.dispatchDisplayHint(hint);
        final String url = mImageViews.get(this);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (View.VISIBLE == hint) {
            if (mState != STATE_SUCCESS) {
                setImageUrl(url);
            }
        } else {
            removeWaitingTask(url);
            loadDefaultImage();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mFgAdapter != null) {
            mFgAdapter.dispatchDraw(canvas);
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mFgAdapter != null) {
            mFgAdapter.drawableStateChanged();
        }
    }

    public void setForegroundDrawable(Drawable drawable) {
        if (mFgAdapter != null) {
            mFgAdapter.setForegroundDrawable(drawable);
        }
    }

    private void removeWaitingTask(String url) {
        if (mThemeImageManager != null) {
            mThemeImageManager.removeTask(hashCode(), url);
        }
    }

    private void setImageMatrix(int dwidth, int dheight, int vwidth, int vheight) {
        if (vwidth <= 0 || vheight <= 0) {
            setScaleType(ScaleType.CENTER_CROP);
            return;
        }
        float scale;
        float dx = 0, dy = 0;

        if (dwidth * vheight > vwidth * dheight) {
            scale = (float) vheight / (float) dheight;
            dx = (vwidth - dwidth * scale) * 0.5f;
        } else {
            scale = (float) vwidth / (float) dwidth;
            dy = vheight - dheight * scale;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        setImageMatrix(matrix);
    }
}