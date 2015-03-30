/***********************************************
 * Filename    : FeedTypeAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.util;

import java.util.List;

import com.innovaee.eorder.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * 分类数据Adapter
 *
 */
public class FeedTypeAdapter extends BaseAdapter {    
    //布局文件获取类
    private LayoutInflater inflater;
        
    //绑定数据
    private List<FeedType> feedTypeList;

    /**
     * 构造函数
     * @param paramContext 调用者Context
     */
    public FeedTypeAdapter(Context paramContext) {
        this.inflater = LayoutInflater.from(paramContext);
    }

    /**
     * 构造函数
     * @param paramContext 调用者Context
     * @param feedTypeList 绑定数据
     */
    public FeedTypeAdapter(Context paramContext, List<FeedType> feedTypeList) {
        this.inflater = LayoutInflater.from(paramContext);
        this.feedTypeList = feedTypeList;
    }

    /**
     * 获取绑定数据数目
     */
    public int getCount() {
        return feedTypeList.size();
    }
    
    /**
     * 获取每一项
     * @param paramInt 序号
     */
    public FeedType getItem(int paramInt) {
        return feedTypeList.get(paramInt);
    }

    /**
     * 获取每一项的id
     * @param paramInt 序号
     */
    public long getItemId(int paramInt) {
        return paramInt;
    }

    /**
     * 获取每一项的View
     * @param paramInt 序号
     * @param paramView 单独每项View，不为NULL则重复使用
     * @param ViewGroup ViewGrop是父View本身
     */		
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null) {
            paramView = this.inflater.inflate(R.layout.drop_down_item,
                    paramViewGroup, false);
        }

        FeedType localFeedType = getItem(paramInt);

        TextView localTextView = (TextView) paramView;
        localTextView.setText(localFeedType.getTypeName());
        return paramView;
    }
}