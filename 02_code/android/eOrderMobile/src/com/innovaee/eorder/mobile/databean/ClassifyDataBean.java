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
	public final static String COLUM_ID = "id"; 			 	//id
	public final static String COLUM_NAME = "name";			 	//名字
			
    private int id;
    	
    private String name;
    
    	
    public ClassifyDataBean() {
    }
    			
    public ClassifyDataBean(int id, String name) {
    	this.id = id;
    	this.name = name;
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
}
