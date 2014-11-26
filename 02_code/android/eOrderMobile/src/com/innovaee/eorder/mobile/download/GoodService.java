package com.innovaee.eorder.mobile.download;

/**
 * 	
 * @author wanglinglong
 *
 */
public interface GoodService {  
  
    // 获取最新的商品信息  
    public abstract <T> void getAllGoods(ICallback<T> callback);  
    	
    // 获取最新的单个商品的详细信息  
    public abstract <T> void findGoodsById(ICallback<T> callback);  
    																	
}  	