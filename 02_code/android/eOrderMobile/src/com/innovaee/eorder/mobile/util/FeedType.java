/***********************************************
 * Filename		: FeedType.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.util;
	
		
/**
 * 目前查看的食品的类型或者分类
 *
 */	
public class FeedType
{
	//分类名
	private String feedTypeName;	
	
	//是否需要刷新
	private boolean needRefresh;	
	
	//分类id
	private int categoryId;			
				
	/**
	 * 构造函数
	 * @param paramInt 分类id
	 * @param paramString 分类名
	 */
	public FeedType(int paramInt, String paramString)
	{
		this.categoryId = paramInt;
	    this.feedTypeName = paramString;
	}	
		
	/**
	 * 是否需要刷新
	 * @return 是否需要刷新，true需要刷新， false不需要刷新
	 */
	public boolean needRefresh()
	{
	    return this.needRefresh;
	}
			
	/**
	 * 设置是否需要刷新
	 * @param paramBoolean 是否需要刷新，true需要刷新， false不需要刷新
	 */
	public void setNeedRefresh(boolean paramBoolean)
	{
	    this.needRefresh = paramBoolean;
	}
	
	/**
	 * 得到分类id
	 * @return 分类id
	 */
	public int getCategoryId()
	{
	    return this.categoryId;
	}
	
	/**
	 * 得到分类名字
	 * @return
	 */
	public String getTypeName() {
		return feedTypeName;	
	}	
	
	/**
	 * 设置分类id
	 * @param id 分类id
	 */
	public void setCategoryId(int id) {
		this.categoryId = id;
	}
	
	/**
	 * 设置分类名
	 * @param feedTypeName 分类名
	 */
	public void setFeedTypeName(String feedTypeName) {
		this.feedTypeName = feedTypeName;
	}	
}