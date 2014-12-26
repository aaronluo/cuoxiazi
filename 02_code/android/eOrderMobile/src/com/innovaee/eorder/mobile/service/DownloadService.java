/***********************************************
 * Filename		: DownloadService.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;
import com.innovaee.eorder.mobile.databean.OrderInfoDataBean;
import com.innovaee.eorder.mobile.databean.TableInfoDataBean;
import com.innovaee.eorder.mobile.databean.UserInfoDataBean;
import com.innovaee.eorder.mobile.util.Env;
	
/**
 * 下载服务
 * 通过该服务和WebService获取所需数据
 * @author wanglinglong
 * 
 */
public class DownloadService implements GoodService, CategoryService {
	//调试Tag	
	private final static String TAG = "DownloadService";
			
	private static DownloadService self;
	
	private static Context context;		
			
	/**
	 * DownloadService
	 * @param context
	 */
	private DownloadService(Context contextTemp) {
		if (contextTemp == null) {
			throw new IllegalArgumentException("context can not be null");
		}
		context = contextTemp.getApplicationContext();
	}

	/**
	 * 获取DownloadService接口
	 * @param context
	 * @return DownloadService对象
	 */
	public static synchronized DownloadService getInstance(Context contextTemp) {
		if (contextTemp == null) {
			throw new IllegalArgumentException("context can not be null");
		}
		context = contextTemp.getApplicationContext();
							
		if (self == null) {
			self = new DownloadService(context);
		}
		return self;
	}
	
	/**
	 * 得到设置中的服务器地址
	 * 如果设置中没有设置服务器地址，则返回默认服务Url地址
	 * @return 服务器地址
	 */
	public static String getServiceUrl() {
		String url;
				
		SharedPreferences sharedPreferences = context.getSharedPreferences("setting", Activity.MODE_PRIVATE); 
						
		url = sharedPreferences.getString("serviceUrl", "");   
			
		if(url.equals("")) {
			url = "http://192.168.1.11:8080";
		}	
				
		return url;
	}	

	/**
	 * 获取某分类下面商品列表信息
	 */			
	@SuppressWarnings("unchecked")
	@Override	
	public <T> void getAllGoods(int id, ICallback<T> callback) {
		// 创建请求HttpClient客户端
		HttpClient httpClient = new DefaultHttpClient();

		// 创建请求的url					
		String url = getServiceUrl() + Env.Server.SERVIE_GET_DISH + String.valueOf(id);	
		Log.d(TAG, "url=" + url);
			
		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));

			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);
	        
			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseGoodsDataJson(json);
					callback.onSuccess(beans);
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析菜品json数组对象
	 * 
	 * @param json
	 * @return 菜品列表list
	 */
	private List<GoodsDataBean> parseGoodsDataJson(String json) {
		List<GoodsDataBean> goods = new ArrayList<GoodsDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("dishes");

			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				GoodsDataBean good = new GoodsDataBean(obj.getInt("dishId"),
						obj.getString("dishName"), (Double) obj.getDouble("dishPrice"), getBitmapUrl(obj.getString("dishPicture")));
				goods.add(good);	
			}				
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/**
	 * 获取会员的折扣信息
	 * @param userId 会员id
	 * @param callback  回调监听器
	 */
	public <T> void getUserDiscountData(String userId, ICallback<T> callback) {
		// 创建请求HttpClient客户端	
		HttpClient httpClient = new DefaultHttpClient();
		
		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVER_GET_USERINFO + userId;	
		Log.d(TAG, "url=" + url);

		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));

			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);

			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseUserDiscountDataJson(json);
					callback.onSuccess(beans);
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析会员信息json数组对象
	 * 
	 * @param json 会员信息json数据
	 * @return 会员数据list, 默认使用0位置数据
	 */		
	private List<UserInfoDataBean> parseUserDiscountDataJson(String json) {
		List<UserInfoDataBean> goods = new ArrayList<UserInfoDataBean>();
		try {	
			JSONObject obj = new JSONObject(json).getJSONObject("user");
				
			UserInfoDataBean userInfo = new UserInfoDataBean(obj.getInt("userId"),
					obj.getString("userName"), obj.getString("cellphone"), obj.getString("levelName"), (Double) obj.getDouble("discount"));
			goods.add(userInfo);							
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return goods;
	}	
		
	/**
	 * 获取最新的单个商品的详细信息
	 * @param callback 回调监听器
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void findGoodsById(ICallback<T> callback) {
		// 创建请求HttpClient客户端
		HttpClient httpClient = new DefaultHttpClient();

		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVIE_GET_DISH_TEST;
		Log.d(TAG, "url=" + url);
		
		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));

			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);

			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					T bean = (T) parseGoodsDetailJson(json);
					callback.onSuccessT(bean);
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析商品详情json数据
	 * 该功能为后期扩展使用
	 * @param json 菜品详情数据
	 * @return 菜品信息数据Bean
	 */
	private GoodsDataBean parseGoodsDetailJson(String json) {
		JSONObject obj = null;
		try {
			obj = new JSONObject(json).getJSONObject("good");

			GoodsDataBean good = new GoodsDataBean(obj.getInt("id"),
					obj.getString("name"), (Double) obj.getDouble("price"), getBitmapUrl(obj.getString("url")));
			return good;	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取最新的商品分类表
	 * @param callback 回调监听器
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void getAllCategory(ICallback<T> callback) {
		// TODO Auto-generated method stub
		// 创建请求HttpClient客户端
		Log.d(TAG, "getAllCategory");	
		HttpClient httpClient = new DefaultHttpClient();
					
		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVIE_GET_CATEGORY;
		Log.d(TAG, "url=" + url);
			
		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));
			
			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);

			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) { 
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串			
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseCategoryDataJson(json);
					callback.onSuccess(beans);
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析分类json数据
	 * 
	 * @param json 菜品分类json数据
	 * @return 菜品分类信息list
	 */
	private List<CategoryDataBean> parseCategoryDataJson(String json) {
		List<CategoryDataBean> categoryList = new ArrayList<CategoryDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("categories");

			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				CategoryDataBean category = new CategoryDataBean(
						obj.getInt("categoryId"), obj.getString("categoryName"),
						getBitmapUrl(obj.getString("categoryPicture")));
				categoryList.add(category);	
			}	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	/**
	 * 获取某个会员号的历史订单记录
	 * @param userId 会员id
	 * @param callback 回调监听器
	 */	
	public <T> void getOrderHestory(String userId, ICallback<T> callback) {
		// TODO Auto-generated method stub
		// 创建请求HttpClient客户端
		HttpClient httpClient = new DefaultHttpClient();

		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVIE_GET_ORDERHESTORY + userId;
		Log.d(TAG, "url=" + url);
		
		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));

			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);

			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseOrderHestoryDataJson(json);
					callback.onSuccess(beans);	
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析订单历史json数据
	 * 
	 * @param json 订单历史json数据
	 * @return 订单数据Bean列表list
	 */
	private List<OrderHestoryDataBean> parseOrderHestoryDataJson(String json) {
		List<OrderHestoryDataBean> categoryList = new ArrayList<OrderHestoryDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("orders");
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				OrderHestoryDataBean category = new OrderHestoryDataBean(
						obj.getInt("orderId"), obj.getString("createAt"),
						obj.getDouble("totalPrice"));
				categoryList.add(category);	
			}				
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	
	/**
	 * 获取某个订单详情
	 * @param callback 回调监听器
	 */	
	public <T> void getOrderInfo(ICallback<T> callback) {
		// TODO Auto-generated method stub
		// 创建请求HttpClient客户端
		HttpClient httpClient = new DefaultHttpClient();

		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVIE_GET_ORDERINFO;
		Log.d(TAG, "url=" + url);
		
		try {
			// 创建请求的对象
			HttpGet get = new HttpGet(new URI(url));

			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        get.setParams(params);

			// 发送get请求
			HttpResponse httpResponse = httpClient.execute(get);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseOrderInfoDataJson(json);
					callback.onSuccess(beans);	
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}

	/**
	 * 解析订单详情json数据
	 * 
	 * @param json 订单详情json数据
	 * @return 订单详情数据Bean列表list
	 */
	private List<OrderInfoDataBean> parseOrderInfoDataJson(String json) {
		List<OrderInfoDataBean> OrderInfoList = new ArrayList<OrderInfoDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("orderitems");
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				OrderInfoDataBean orderInfo = new OrderInfoDataBean(
						obj.getInt("dishId"), obj.getString("createAt"),
						obj.getDouble("totalPrice"), obj.getInt("dishAmount"), getBitmapUrl(obj.getString("dishPicture")));
				OrderInfoList.add(orderInfo);		
			}									
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return OrderInfoList;
	}
	
	/**
	 * 提交订单详情
	 * @param tableInfo 下单台数据信息
	 * @param dataBeanList 下单数据详情信息
	 * @param callback 回调监听器
	 */						
	public <T> void postOrderInfo(TableInfoDataBean tableInfo, List<OrderInfoDataBean> dataBeanList, ICallback<T> callback) {
		// TODO Auto-generated method stub	
		// 创建请求HttpClient客户端
		HttpClient httpClient = new DefaultHttpClient();

		// 创建请求的url
		String url = getServiceUrl() + Env.Server.SERVIE_POST_ORDER;
		Log.d(TAG, "url=" + url);
			
		try {	
			// 创建请求的对象	
			HttpPost request = new HttpPost(new URI(url)); 
				
			HttpParams params = new BasicHttpParams(); 
			
			//设置连接超时
	        HttpConnectionParams.setConnectionTimeout(params, 20000);
	        
	        //设置请求超时
	        HttpConnectionParams.setSoTimeout(params, 20000); 	        		
	        	
	        request.setParams(params);

			// 先封装一个 JSON 对象 
			JSONObject object = writeJSON(tableInfo, dataBeanList);
			
			// 绑定到请求 Entry  
			StringEntity se = new StringEntity(object.toString());   
			request.setEntity(se);  
							
			// 发送post请求
			HttpResponse httpResponse = httpClient.execute(request);

			// 如果服务成功返回响应
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {	
					// 获取服务器响应的json字符串
					String json = EntityUtils.toString(entity, "UTF-8");
					Log.d(TAG, "json=" + json.toString());
					List<T> beans = (List<T>) parseOrderInfoDataJson(json);
					callback.onSuccess(beans);	
				} else {
					callback.onFailed("entityIsNull");
				}				
			} else {	
				callback.onFailed("getStatusCodeError");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed("ExceptionError");
		}	
	}	
			
	/**
	 * 转换数据到json格式
	 * @param tableInfo
	 * @param dataBeanList
	 * @return
	 */
	public JSONObject writeJSON(TableInfoDataBean tableInfo, List<OrderInfoDataBean> dataBeanList) {
	    JSONObject object = new JSONObject();	    	
	    JSONArray array = new JSONArray();
	    	
        try {
        	object.put("cellphone", tableInfo.getCellphone());
        	object.put("tableId", tableInfo.getId());
        	object.put("servantId", tableInfo.getServantId());
        	object.put("dishPrice", tableInfo.getDishPrice()); 
	        
	        for (OrderInfoDataBean databean : dataBeanList ) {
	        	JSONObject dataInfo = new JSONObject();
	        			      
		        dataInfo.put("dishId", databean.getId());
		        dataInfo.put("dishName", databean.getDishName());
		        dataInfo.put("price", databean.getDishPrice());
		        dataInfo.put("dishAmount", databean.getDishAmount());
		        dataInfo.put("dishPicture", databean.getDishPicture());		        	
							
		        array.put(dataInfo);
	        }
	        	
	        object.put("dishList", array);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	        
        	
        Log.d("DownloadService:", "object=" + object.toString());	
        return object;
	}
			
	/**
	 * 由服务器图片地址转换到真实url地址
	 * @param bitmapPath 服务器下发的图片地址
	 * @return 真实的图片url地址
	 */
	private String getBitmapUrl(String bitmapPath) {
		String bitmapUrl = getServiceUrl() + "/eorder-ws/images" + bitmapPath;			
		return bitmapUrl;
	}
	
}