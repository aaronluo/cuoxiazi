/***********************************************
 * Filename		: IForeground.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;

import android.graphics.Rect;

/**
 * 类描述: 蒙版显示兼容接口 
 * 功能详细描述:
 * 
 * @author wanglinglong
 * @date [2014年10月21日]
 */
public interface IForeground {

	public int[] getDrawableState();

	public void getHitRect(Rect outRect);

	public void invalidate();

	public void setDuplicateParentStateEnabled(boolean enabled);

	public boolean isPressed();
}
