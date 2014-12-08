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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * ImageView extended class allowing easy downloading of remote images
 * 
 * @author majunwen
 */
public class RemoteImageView extends ImageView implements IForeground {

	private static final int STATE_LOADING = 0x01;
	private static final int STATE_SUCCESS = 0x02;
	private static final int STATE_ERROR = 0x03;

	private int state = STATE_LOADING;

	// Hard cache, with a fixed maximum capacity and a life duration
	// private final static LinkedList<Bitmap> sHardBitmapCache = new
	// LinkedList<Bitmap>();
	private final Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());

	private Handler uiHandler;

	/**
	 * Position of the image in the listView
	 */
	private int position;

	/**
	 * ListView containg this image
	 */
	private ListView listView;

	/**
	 * Default image shown while loading or on url not found
	 */
	private Integer defaultImage;

	/**
	 * Image manager
	 */
	private ImageDataManager themeImageManager;

	private ImageView.ScaleType scaleTypeDefault;
	private ImageView.ScaleType scaleTypeContent;
	/**
	 * 默认高宽比
	 */
	private float defaultAspect;
	/**
	 * 内容填充高款比
	 */
	private float curAspect;
	/**
	 * 是否根据图片的高款进行高款比适配
	 */
	private boolean applyAspect;
	/**
	 * 计算大小时候，高款比参照物大小
	 */
	private int aspectSize;
	private AspectRef aspectRef;

	private ForegroundAdapter fgAdapter;

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

	public void setIsApplyAspect(boolean apply) {
		applyAspect = apply;
	}

	public void setAspectSize(AspectRef ref, int size) {
		aspectRef = ref;
		aspectSize = size;
	}

	public void setDefaultAspect(float defaultDefault) {
		defaultAspect = defaultDefault;
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
								Log.i("RemoteImageView",
										"downloadImage "
												+ " callback, bitmap "
												+ (bitmap == null ? "null"
														: bitmap.toString())
												+ ", url:" + url);
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

	private void setImageBitmap(String url, Bitmap bitmap) {
		state = STATE_ERROR;
		String tag = imageViews.get(RemoteImageView.this);
		if (tag != null && tag.equals(url)) {
			if (bitmap == null) {
				// Log.w("TAG", "Trying again to download " + url);
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
					Log.i("RemoteImageView", "compreseBitmap, srcWidth:"
							+ bitmapW + ", srcHeight:" + bitmapH
							+ ", dstWidth:" + desWidth + ", dstHeight:"
							+ desHeight);
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
		final float bitmapSize = bitmap == null ? .0f : (float) (bitmap
				.getRowBytes() * bitmap.getHeight()) / 1024;
		Log.i("RemoteImageView", "setImageBitmap "
				+ (state == STATE_SUCCESS ? "success" : "failed") + ", size:"
				+ bitmapSize + "KB" + ", url:" + url);
	}

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
	 * get bitmap from the method
	 * 
	 * @param url
	 * @return
	 */
	/*
	 * public Bitmap getImageUsingUrl(String url) { return
	 * themeImageManager.getBitmap(url); }
	 */

	/**
	 * Sets default local image shown when remote one is unavailable
	 * 
	 * @param resid
	 */
	public void setDefaultImage(Integer resid) {
		defaultImage = resid;
	}

	public void setScaleTypeDefault(ImageView.ScaleType scaleType) {
		scaleTypeDefault = scaleType;
	}

	public void setScaleTypeContent(ImageView.ScaleType scaleType) {
		scaleTypeContent = scaleType;
	}

	/**
	 * Loads default image
	 */
	private void loadDefaultImage() {
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
	 * Loads image from remote location in the ListView
	 * 
	 * @param url
	 *            eg. http://random.com/abz.jpg
	 * @param position
	 *            ListView position where the image is nested
	 * @param listView
	 *            ListView to which this image belongs
	 */
	/*
	 * public void setImageUrl(String url, int position, ListView listView) {
	 * position = position; listView = listView; setImageUrl(url); }
	 */

	/*
	 * private Bitmap drawableToBitmap(Drawable drawable) {
	 * 
	 * Drawable杞寲涓築itmap
	 * 
	 * int width = drawable.getIntrinsicWidth(); int height =
	 * drawable.getIntrinsicHeight(); Bitmap bitmap = Bitmap.createBitmap(width,
	 * height, drawable.getOpacity() != PixelFormat.OPAQUE ?
	 * Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565); Canvas canvas = new
	 * Canvas(bitmap); drawable.setBounds(0, 0, width, height);
	 * drawable.draw(canvas); return bitmap; }
	 */

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		setImageDrawable(null);
		removeWaitingTask(imageViews.get(this));
	}

	@Override
	public void onStartTemporaryDetach() {
		// TODO Auto-generated method stub
		super.onStartTemporaryDetach();
		removeWaitingTask(imageViews.get(this));
	}

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

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (fgAdapter != null) {
			fgAdapter.dispatchDraw(canvas);
		}
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		if (fgAdapter != null) {
			fgAdapter.drawableStateChanged();
		}
	}

	public void setForegroundDrawable(Drawable drawable) {
		if (fgAdapter != null) {
			fgAdapter.setForegroundDrawable(drawable);
		}
	}

	private void removeWaitingTask(String url) {
		if (themeImageManager != null) {
			themeImageManager.removeTask(hashCode(), url);
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

	/**
	 * clear, resycle resources
	 */
	/*
	 * public void clearUp() { themeImageManager.destory(); themeImageManager =
	 * null; uiHandler = null; }
	 */
}