package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;
	
/**
 * 
 * @author wanglinglong
 *
 */
public class ClassifyDataBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public final static String TABLE = "classify";  			//表单名	
	public final static String COLUM_ID = "id"; 			 	//分类id
	public final static String COLUM_NAME = "name";			 	//分类名字
	public final static String COLUM_BITMAPUIL = "url";			//分类背景图片
				
    private int id;
    	
    private String name;
    
    private String bitmapUrl;
    	
    	
    public ClassifyDataBean() {
    }
    
    public ClassifyDataBean(int id, String name, String bitmapUrl) {
    	this.id = id;
    	this.name = name;
    	this.bitmapUrl = bitmapUrl;
    }	
    	    		
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
    	this.id = id;
    }
        
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getBitmapUrl() {
    	return this.bitmapUrl;
    }
    
    public void setBitmapUrl(String bitmapUrl) {
    	this.bitmapUrl = bitmapUrl;	
    }
}
