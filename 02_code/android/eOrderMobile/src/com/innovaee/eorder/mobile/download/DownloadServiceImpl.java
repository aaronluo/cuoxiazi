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
	
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.utils.Env;
	
				
public class DownloadServiceImpl implements GoodService, ClassifyService {  
		
	private final String SERVER = Env.Server.SERVER_TEST; 
	
	/**
	 * 获取最新的商品信息
	 */
    @Override  
    public List<GoodsDataBean> getAllGoods() {  
        // 创建请求HttpClient客户端  
        HttpClient httpClient = new DefaultHttpClient();  
        	
        // 创建请求的url  
        String url = SERVER;  
        
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
                    return parseGoodsDataJson(json);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    /**
     * 解析json数组对象
     * @param json
     * @return
     */
    private List<GoodsDataBean> parseGoodsDataJson(String json) {  
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
  
    /**
     * 获取最新的单个商品的详细信息
     */
    @Override  
    public GoodsDataBean findGoodsById() {  
        // 创建请求HttpClient客户端  
        HttpClient httpClient = new DefaultHttpClient(); 
        			
        // 创建请求的url  
        String url = SERVER; 
        		
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
                    return parseGoodsDetailJson(json);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    							  
    /**
     * 解析json的单个对象
     * @param json
     * @return
     */
    private GoodsDataBean parseGoodsDetailJson(String json) {  
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

    /**
     * 获取最新的商品分类表
     */
	@Override	
	public List<ClassifyDataBean> getAllClassify() {
		// TODO Auto-generated method stub
		// 创建请求HttpClient客户端  
        HttpClient httpClient = new DefaultHttpClient();  
        	
        // 创建请求的url  
        String url = SERVER;  
        
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
                    return parseClassifyDataJson(json);  
                }  
            }  	
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
		return null;
	}
		  
	/**
	 * 解析json数组对象
	 * @param json
	 * @return
	 */
    private List<ClassifyDataBean> parseClassifyDataJson(String json) {  
        List<ClassifyDataBean> classifyList = new ArrayList<ClassifyDataBean>();  
        try {  
            JSONArray array = new JSONObject(json).getJSONArray("classify");  
            
            for (int i = 0; i < array.length(); i++) {  
                JSONObject obj = array.getJSONObject(i);  
                ClassifyDataBean classify = new ClassifyDataBean(obj.getInt("id"), obj.getString("name"));  
                classifyList.add(classify);  						
            }  		
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return classifyList;  
    }  		
}  