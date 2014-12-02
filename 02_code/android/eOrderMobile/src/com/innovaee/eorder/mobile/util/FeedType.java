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
	private int classifyId;			//分类id
				
	public FeedType(int paramInt, String paramString)
	{
		this.classifyId = paramInt;
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
	
	public int getClassifyId()
	{
	    return this.classifyId;
	}
	
	public String getTypeName() {
		return feedTypeName;	
	}	
	
	public void setClassifyId(int id) {
		this.classifyId = id;
	}
	
	public void setFeedTypeName(String feedTypeName) {
		this.feedTypeName = feedTypeName;
	}	
}