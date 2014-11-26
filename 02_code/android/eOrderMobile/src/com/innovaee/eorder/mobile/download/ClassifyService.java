package com.innovaee.eorder.mobile.download;

import java.util.List;

import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

/**
 * 	
 * @author wanglinglong
 *
 */
public interface ClassifyService {  
  
    // 获取最新的商品信息  
    public abstract List<ClassifyDataBean> getAllClassify();      	    
}  	