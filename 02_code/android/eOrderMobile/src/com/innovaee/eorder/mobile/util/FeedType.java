package com.innovaee.eorder.mobile.util;
	
		
/**
 * 目前查看的食品的类型或者分类
 * @author wanglinglong
 *
 */	
public class FeedType
{
	private String feedTypeName;	//分类名
	private boolean needRefresh;	//是否需要刷新
	private int categoryId;			//分类id
				
	public FeedType(int paramInt, String paramString)
	{
		this.categoryId = paramInt;
	    this.feedTypeName = paramString;
	}	
	
	public boolean needRefresh()
	{
	    return this.needRefresh;
	}
			
	public void setNeedRefresh(boolean paramBoolean)
	{
	    this.needRefresh = paramBoolean;
	}
	
	public int getCategoryId()
	{
	    return this.categoryId;
	}
	
	public String getTypeName() {
		return feedTypeName;	
	}	
	
	public void setCategoryId(int id) {
		this.categoryId = id;
	}
	
	public void setFeedTypeName(String feedTypeName) {
		this.feedTypeName = feedTypeName;
	}	
}