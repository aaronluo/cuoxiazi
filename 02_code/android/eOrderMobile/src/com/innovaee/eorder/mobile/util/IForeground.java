/***********************************************
 * Filename    : IForeground.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;


/**
 * 类描述: 蒙版显示兼容接口 功能详细描述:
 * 
 * @date [2014年10月21日]
 */
public interface IForeground {

    /**
     * 得到资源状态
     * 
     * @return 资源状态
     */
    public int[] getDrawableState();

    /**
     * 得到热区位置
     * 
     * @param outRect
     *            热区位置
     */
    public void getHitRect(Rect outRect);

    /**
     * 刷新
     */
    public void invalidate();

    /**
     * 设置是否生效
     * 
     * @param enabled
     *            是否生效，true是，false否
     */
    public void setDuplicateParentStateEnabled(boolean enabled);

    /**
     * 是否被按下
     * 
     * @return 是否按下，true是，false否
     */
    public boolean isPressed();
}
