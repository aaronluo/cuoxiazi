/***********************************************
 * Filename		: ForegroundAdapter.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;

import com.innovaee.eorder.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * 类描述: 蒙版显示控制器 功能详细描述: 根据触屏状态下的调整蒙版显示效果
 * 
 * @date [2014年10月21日]
 */
public class ForegroundAdapter {

    private IForeground ifCoverState;
    private Drawable coverDrawable;

    public ForegroundAdapter(Context context, IForeground iCoverState) {
        ifCoverState = iCoverState;
        if (ifCoverState == null) {
            throw new RuntimeException("iCoverState must not null!");
        }
        coverDrawable = context.getResources().getDrawable(
                R.drawable.themestore_common_foreground);
        ifCoverState.setDuplicateParentStateEnabled(false);
    }

    public void setForegroundDrawable(Drawable drawable) {
        if (coverDrawable != drawable) {
            coverDrawable = drawable;
            ifCoverState.invalidate();
        }
    }

    /**
     * 功能简述: 功能详细描述: 注意: 必须由宿主调用
     * 
     * @param canvas
     */
    public void dispatchDraw(Canvas canvas) {
        if (coverDrawable != null && ifCoverState.isPressed()) {
            coverDrawable.draw(canvas);
        }
    }

    /**
     * 功能简述: 功能详细描述: 注意: 必须由宿主调用
     */
    public void drawableStateChanged() {
        Drawable d = coverDrawable;
        if (d != null && d.isStateful()) {
            d.setState(ifCoverState.getDrawableState());
            Rect rect = new Rect();
            ifCoverState.getHitRect(rect);
            d.setBounds(0, 0, rect.width(), rect.height());
            ifCoverState.invalidate();
        }
    }
}
