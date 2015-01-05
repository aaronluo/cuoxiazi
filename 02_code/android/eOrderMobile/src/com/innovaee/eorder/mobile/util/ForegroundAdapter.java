/***********************************************
 * Filename    : ForegroundAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
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
    //蒙版显示兼容接口
    private IForeground ifCoverState;
    
    //资源对象
    private Drawable coverDrawable;
        
    /**
     * 构造函数
     * @param context 调用者Context
     * @param iCoverState  蒙版显示兼容接口
     */
    public ForegroundAdapter(Context context, IForeground iCoverState) {
        ifCoverState = iCoverState;
        if (ifCoverState == null) {
            throw new RuntimeException("iCoverState must not null!");
        }
        coverDrawable = context.getResources().getDrawable(
                R.drawable.themestore_common_foreground);
        ifCoverState.setDuplicateParentStateEnabled(false);
    }

    /**
     * 设置资源对象
     * @param drawable 资源对象
     */
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
        Drawable drawable = coverDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(ifCoverState.getDrawableState());
            Rect rect = new Rect();
            ifCoverState.getHitRect(rect);
            drawable.setBounds(0, 0, rect.width(), rect.height());
            ifCoverState.invalidate();
        }
    }
}
