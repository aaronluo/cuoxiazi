package com.innovaee.eorder.mobile.download;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.innovaee.eorder.mobile.databean.GoodsDataBean;
	
	
public class GoodServiceImpl implements GoodService {  
  
    // 获取最新的商品信息  
    /* (non-Javadoc) 
     * @see cn.redarmy.service.Impl.GoodService#findAll() 
     */  
    @Override  
    public List<GoodsDataBean> findAll() {  
        // 创建请求HttpClient客户端  
        HttpClient httpClient = new DefaultHttpClient();  
        // 创建请求的url  
        String url = "http://192.168.4.32:8080/shop/csdn/listNewsGoods.action";  
        try {  
            // 创建请求的对象  
            HttpGet get = new HttpGet(new URI(url));  
            // 发送get请求  
            HttpResponse httpResponse = httpClient.execute(get);  
            // 如果服务成功返回响应  
            if (httpResponse.getStatusLine().getStatusCode() == 200) {  
                HttpEntity entity = httpResponse.getEntity();  
                if (entity != null) {  
                    // 获取服务器响应的json字符串  
                    String json = EntityUtils.toString(entity);  
                    return parseArrayJosn(json);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    //解析json数组对象  
    private List<GoodsDataBean> parseArrayJosn(String json) {  
        List<GoodsDataBean> goods = new ArrayList<GoodsDataBean>();  
        try {  
            JSONArray array = new JSONObject(json).getJSONArray("goods");  
            for (int i = 0; i < array.length(); i++) {  
                JSONObject obj = array.getJSONObject(i);  
                GoodsDataBean good = new GoodsDataBean(obj.getInt("id"), obj.getString("name"),  
                        (float) obj.getDouble("price"));  
                goods.add(good);  
            }  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return goods;  
    }  
  
    // 获取最新的单个商品的详细信息  
    /* (non-Javadoc) 
     * @see cn.redarmy.service.Impl.GoodService#findById() 
     */  
    @Override  
    public GoodsDataBean findById() {  
        // 创建请求HttpClient客户端  
        HttpClient httpClient = new DefaultHttpClient();  
        // 创建请求的url  
        String url = "http://192.168.4.32:8080/shop/csdn/findGood.action";  
        try {  
            // 创建请求的对象  
            HttpGet get = new HttpGet(new URI(url));  
            // 发送get请求  
            HttpResponse httpResponse = httpClient.execute(get);  
            // 如果服务成功返回响应  
            if (httpResponse.getStatusLine().getStatusCode() == 200) {  
                HttpEntity entity = httpResponse.getEntity();  
                if (entity != null) {  
                    // 获取服务器响应的json字符串  
                    String json = EntityUtils.toString(entity);  
                    return parseObjJosn(json);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    //解析json的单个对象  
    private GoodsDataBean parseObjJosn(String json) {  
        JSONObject obj = null;  
        try {  
            obj = new JSONObject(json).getJSONObject("good");  
            GoodsDataBean good = new GoodsDataBean(obj.getInt("id"), obj.getString("name"),  
                    (float) obj.getDouble("price"));  
            return good;  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}  