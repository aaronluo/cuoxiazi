package com.innovaee.eorder.mobile.download;

import java.util.List;

import com.innovaee.eorder.mobile.databean.GoodsDataBean;

/**
 * 	
 * @author wanglinglong
 *
 */
public interface GoodService {  
  
    // 获取最新的商品信息  
    public abstract List<GoodsDataBean> findAll();  
    
    // 获取最新的单个商品的详细信息  
    public abstract GoodsDataBean findById();  
    
}  	