/***********************************************
 * Filename		: FeedTypeAdapter.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
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
public class FeedTypeAdapter extends BaseAdapter
{
	private boolean hasNewSecretOfFriend;
	private LayoutInflater inflater;
	private List<FeedType> feedTypeList;
  													
	public FeedTypeAdapter(Context paramContext)
	{
		this.inflater = LayoutInflater.from(paramContext);
	}
				
	public FeedTypeAdapter(Context paramContext, List<FeedType> feedTypeList)
	{
		this.inflater = LayoutInflater.from(paramContext);
		this.feedTypeList = feedTypeList;
	}	
  	
	public int getCount()
	{
		return feedTypeList.size();
	}	

	public FeedType getItem(int paramInt)
	{
		return feedTypeList.get(paramInt);
	}		

	public long getItemId(int paramInt)
	{
		return paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	{
		if (paramView == null) {
			paramView = this.inflater.inflate(R.layout.drop_down_item, paramViewGroup, false);
		}
    				
		FeedType localFeedType = getItem(paramInt);	
    	
		TextView localTextView = (TextView)paramView;
		localTextView.setText(localFeedType.getTypeName());			
		return paramView;
	}	
	
	public boolean isHasNewSecretOfFriend()
	{
		return this.hasNewSecretOfFriend;
	}
	
	public void setHasNewSecretOfFriend(boolean paramBoolean)
	{
		this.hasNewSecretOfFriend = paramBoolean;
		notifyDataSetChanged();
	}
		
}