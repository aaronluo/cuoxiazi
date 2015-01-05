/***********************************************
 * Filename    : RemoteImageView.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;

import com.google.zxing.common.Collections;
import com.innovaee.eorder.mobile.util.ImageDataManager.OnImageLoaderListener;

/**
 * RemoteImageView继续自ImageView
 * 可以自动从url下载图片
 */
public class RemoteImageView extends ImageView implements IForeground {
    //下载消息
    private static final int STATE_LOADING = 0x01;
    
    //成功消息
    private static final int STATE_SUCCESS = 0x02;
    
    //错误消息
    private static final int STATE_ERROR = 0x03;
        
    //状态
    private int state;
        
    //对应图片数据
    private final Map<ImageView, String> imageViews = Collections
            .synchronizedMap(new WeakHashMap<ImageView, String>());
    
    //消息Handler
    private Handler uiHandler;

    //图片在listView中的位置
    private int position;

    //保存图片的listview
    private ListView listView;

    //默认图片，加载数据或者没有找到图片时候显示
    private Integer defaultImage;
    
    //图片管理器
    private ImageDataManager themeImageManager;

    //图片显示类型
    private ImageView.ScaleType scaleTypeDefault;
    private ImageView.ScaleType scaleTypeContent;
    
    //默认高宽比     
    private float defaultAspect;

    //内容填充高款比
    private float curAspect;
    
    //是否根据图片的高宽进行高宽比适配
    private boolean applyAspect;

    //计算大小时候，高款比参照物大小
    private int aspectSize;
    private AspectRef aspectRef;

    //蒙版数据管理器
    private ForegroundAdapter fgAdapter;

    //计算大小时候，高宽比参照物
    public static enum AspectRef {
        WIDTH, HEIGHT;
    }
    
    /**
     * 构造函数
     * @param context 调用者Context
     * @param attrs AttributeSet属性
     * @param defStyle 风格
     */
    public RemoteImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    /**
     * 构造函数
     * @param context 调用者Context
     * @param attrs AttributeSet属性
     */
    public RemoteImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 构造函数
     * @param context 调用者Context
     */
    public RemoteImageView(Context context) {
        super(context);
        init();
    }
    
    /**
     * 初始化函数
     */
    private void init() {
        fgAdapter = new ForegroundAdapter(getContext(), this);
        fgAdapter.setForegroundDrawable(null); // 默认禁用前景绘制
        uiHandler = new Handler(Looper.getMainLooper());
        defaultImage = R.drawable.themestore_common_default_pic;
        themeImageManager = ImageDataManager.getInstance();
        scaleTypeDefault = ScaleType.FIT_XY;
        scaleTypeContent = ScaleType.CENTER_CROP;
        applyAspect = false;
        defaultAspect = 1.0f;
        curAspect = 1.0f;
        aspectSize = 0;
        aspectRef = AspectRef.HEIGHT;
    }

    /**
     * 设置是否根据图片的高宽进行高宽比适配
     * @param apply 是否要进行适配
     */
    public void setIsApplyAspect(boolean apply) {
        applyAspect = apply;
    }

    /**
     * 设置图片的高宽比适配参照物
     * @param ref 适配参照物
     * @param size 适配大小
     */
    public void setAspectSize(AspectRef ref, int size) {
        aspectRef = ref;
        aspectSize = size;
    }

    /**
     * 设置默认高宽比 
     * @param defaultDefault
     */
    public void setDefaultAspect(float defaultDefault) {
        defaultAspect = defaultDefault;
    }

    /**
     * 功能简述: 预先给视图绑定一个url，后续触发加载 功能详细描述: 注意:
     * 
     * @param url
     */
    public void prepareImageUrl(String url) {
        loadDefaultImage();
        if (!TextUtils.isEmpty(url)) {
            imageViews.put(this, url);
        }
    }
    
    /**
     * 加载图片
     * @param url 图片地址
     */
    public void setImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultImage();
            return;
        }
        String oldUrl = imageViews.get(this);
        if (url.equals(oldUrl) && state == STATE_SUCCESS) {
            return;
        }
        removeWaitingTask(oldUrl);
        imageViews.put(this, url);
        Bitmap bitmap = themeImageManager.getBitmapFromCache(url);
        if (bitmap != null) {
            setImageBitmap(url, bitmap);
        } else {
            loadDefaultImage();
            try {
                themeImageManager.downloadImage(url, hashCode(),
                        new OnImageLoaderListener() {
                            @Override
                            public void onImageLoader(final Bitmap bitmap,
                                    final String url) {
                                if (bitmap != null) {
                                    uiHandler.post(new Runnable() {

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
     * @param url
     *            url地址
     * @param bitmap
     *            bitmap对象
     */
    private void setImageBitmap(String url, Bitmap bitmap) {
        state = STATE_ERROR;
        String tag = imageViews.get(RemoteImageView.this);
        if (tag != null && tag.equals(url)) {
            if (bitmap == null) {
                loadDefaultImage();
            } else {
                // if image belongs to a list update it only if it's visible
                if (listView != null) {
                    if (position < listView.getFirstVisiblePosition()
                            || position > listView.getLastVisiblePosition()) {
                        return;
                    }
                }
                state = STATE_SUCCESS;
                int width = getWidth() - getPaddingLeft() - getPaddingRight();
                int height = getHeight() - getPaddingTop() - getPaddingBottom();
                int bitmapH = bitmap.getHeight();
                int bitmapW = bitmap.getWidth();
                float newAapect = (float) bitmapH / bitmapW;
                if (applyAspect) {
                    if (Math.abs(newAapect - curAspect) > 0.000001f) {
                        curAspect = newAapect;
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
                if (!applyAspect && scaleTypeContent == ScaleType.MATRIX) {
                    setImageMatrix(bmpDrawable.getIntrinsicWidth(),
                            bmpDrawable.getIntrinsicHeight(), width, height);
                }
                RemoteImageView.this.setScaleType(scaleTypeContent);
                RemoteImageView.this.setImageDrawable(bmpDrawable);
            }
        }
    }

    /**
     * 系统调用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (applyAspect) {
            int width;
            int height;
            if (aspectRef == AspectRef.WIDTH) {
                width = aspectSize;
                height = (int) (width * curAspect);
            } else {
                height = aspectSize;
                width = (int) (height / curAspect);
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        if (!applyAspect && getScaleType() == ScaleType.MATRIX
                && getDrawable() != null) {
            Drawable drawable = getDrawable();
            setImageMatrix(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), getMeasuredWidth(),
                    getMeasuredHeight());
        }
    }

    /**
     * 设置默认图片
     * @param resid 默认图片id
     */
    public void setDefaultImage(Integer resid) {
        defaultImage = resid;
    }

    /**
     * 设置scale类型
     * 
     * @param scaleType
     *            scale类型
     */
    public void setScaleTypeDefault(ImageView.ScaleType scaleType) {
        scaleTypeDefault = scaleType;
    }

    /**
     * 设置scale类型
     * 
     * @param scaleType
     *            scale类型
     */
    public void setScaleTypeContent(ImageView.ScaleType scaleType) {
        scaleTypeContent = scaleType;
    }

    /**
     * 加载默认图片
     */
    private void loadDefaultImage() {
        if (state == STATE_LOADING) {
            return;
        }
        state = STATE_LOADING;
        if (defaultImage != null) {
            setScaleType(scaleTypeDefault);
            setImageResource(defaultImage);
        }
        if (applyAspect && Math.abs(defaultAspect - curAspect) > .000001f) {
            curAspect = defaultAspect;
            requestLayout();
        }
    }

    /**
     * 系统调用 
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    /**
     * 系统调用 
     */
    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
    }

    /**
     * 系统调用 
     */
    @Override
    public void dispatchDisplayHint(int hint) {
        super.dispatchDisplayHint(hint);
        final String url = imageViews.get(this);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (View.VISIBLE == hint) {
            if (state != STATE_SUCCESS) {
                setImageUrl(url);
            }
        } else {
            removeWaitingTask(url);
            loadDefaultImage();
        }
    }

    /**
     * 系统调用 
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (fgAdapter != null) {
            fgAdapter.dispatchDraw(canvas);
        }
    }

    /**
     * 系统调用 
     */
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (fgAdapter != null) {
            fgAdapter.drawableStateChanged();
        }
    }

    /**
     * 系统调用 
     */
    public void setForegroundDrawable(Drawable drawable) {
        if (fgAdapter != null) {
            fgAdapter.setForegroundDrawable(drawable);
        }
    }

    /**
     * 系统调用 
     */
    private void removeWaitingTask(String url) {
        if (themeImageManager != null) {
            themeImageManager.removeTask(hashCode(), url);
        }
    }

    /**
     * 设置图片大小
     * @param dwidth 宽
     * @param dheight 高
     * @param vwidth 转换宽
     * @param vheight 转换高
     */
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