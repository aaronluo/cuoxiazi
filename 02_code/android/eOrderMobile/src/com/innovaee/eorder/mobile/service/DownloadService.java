/***********************************************
 * Filename    : DownloadService.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
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
import com.innovaee.eorder.mobile.databean.OrderReturnDataBean;
import com.innovaee.eorder.mobile.databean.ReturnResultDataBean;
import com.innovaee.eorder.mobile.databean.TableInfoDataBean;
import com.innovaee.eorder.mobile.databean.UserInfoDataBean;
import com.innovaee.eorder.mobile.util.Env;

/**
 * 下载服务 通过该服务和WebService获取所需数据
 * 
 */
public class DownloadService implements GoodService, CategoryService {
    //DownloadService本身
    private static DownloadService downloadService;

    //调用者Context
    private static Context context;

    /**
     * DownloadService
     * 
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
     * 
     * @param context
     * @return DownloadService对象
     */
    public static synchronized DownloadService getInstance(Context contextTemp) {
        if (contextTemp == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        context = contextTemp.getApplicationContext();

        if (downloadService == null) {
            downloadService = new DownloadService(context);
        }
        return downloadService;
    }

    /**
     * 得到设置中的服务器地址 如果设置中没有设置服务器地址，则返回默认服务Url地址
     * 
     * @return 服务器地址
     */
    public static String getServiceUrl() {
        String url;

        // 从xml文件读取设置的服务器地址
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "setting", Activity.MODE_PRIVATE);

        url = sharedPreferences.getString("serviceUrl", "");

        // 如果没有设置地址，则返回默认值
        if (url.equals("")) {
            url = "http://192.168.1.13:8080";
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
        String url = getServiceUrl() + Env.Server.SERVIE_GET_DISH
                + String.valueOf(id) + "/dishes";
        	
        try {
            // 创建请求的对象
            HttpGet get = new HttpGet(new URI(url));

            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
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
                    List<T> beans = (List<T>) parseGoodsDataJson(json);
                    callback.onSuccess(beans);
                } else {
                    // 异常信息
                    callback.onFailed("entityIsNull");
                }
            } else {
                // 异常信息
                callback.onFailed("getStatusCodeError");
            }
        } catch (Exception error) {
            // 异常信息
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
                GoodsDataBean good = new GoodsDataBean(obj.getInt("id"),
                        obj.getString("name"),
                        (Double) obj.getDouble("price"),
                        getBitmapUrl(obj.getString("picPath")));
                goods.add(good);
            }
        } catch (JSONException error) {
            Log.e("DownloadService", error.toString());
        }	
        return goods;
    }

    /**
     * 获取会员的折扣信息
     * 
     * @param userId
     *            会员id
     * @param callback
     *            回调监听器
     */
    public <T> void getUserDiscountData(String userId, ICallback<T> callback) {
        // 创建请求HttpClient客户端
        HttpClient httpClient = new DefaultHttpClient();

        Log.d("getUserDiscountData", "leonwang:");
        		
        // 创建请求的url
        String url = getServiceUrl() + Env.Server.SERVER_GET_USERINFO + userId;

        try {
            // 创建请求的对象
            HttpGet get = new HttpGet(new URI(url));

            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
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
                    ReturnResultDataBean returnData = new ReturnResultDataBean();	
                    UserInfoDataBean userInfo = parseUserDiscountDataJson(json, returnData);			
                    if(returnData.getResult()) {					
                    	callback.onSuccessT((T)userInfo);
                    } else {			
                    	callback.onFailed(returnData.getMessage());
                    }													
                } else {		
                    // 异常信息
                	Log.d("getUserDiscountData", "entityIsNull");
                    callback.onFailed("entityIsNull");
                }	
            } else {
                // 异常信息
            	Log.d("getUserDiscountData", "getStatusCodeError");
                callback.onFailed("getStatusCodeError");
            }	
        } catch (Exception error) {
            // 异常信息	
        	Log.d("getUserDiscountData", "ExceptionError");
            callback.onFailed("ExceptionError");
        }
    }

    /**
     * 解析会员信息json数组对象
     * 
     * @param json
     *            会员信息json数据
     * @return 成功或者失败
     */					
    private UserInfoDataBean parseUserDiscountDataJson(String json, ReturnResultDataBean result) {
    	Log.d("parseUserDiscountDataJson:", "json=" + json);	
        try {
            JSONObject obj = new JSONObject(json).getJSONObject("user");
            	
            UserInfoDataBean userInfo = new UserInfoDataBean(
                    obj.getInt("id"), 
                    obj.getString("username"),
                    obj.getString("cellphone"), 
                    obj.getString("levelName"),
                    (Double) obj.getDouble("discount"));
            	result.setMessage("ok");		
            	result.setResult(true);
            	return userInfo;
        } catch (JSONException error) {		
        	Log.e("parseUserDiscountDataJson 1", error.toString());
        	try {									
				JSONObject obj = new JSONObject(json);
				String err = obj.getString("exception");
				if(err.equals("")) {
					err = "error";
				}
									
				result.setMessage(err);		
				result.setResult(false);			
				Log.d("parseUserDiscountDataJson 2", "exception =" + err);
			} catch (JSONException e) {			
				// TODO Auto-generated catch block
				Log.d("parseUserDiscountDataJson 3", "exception");
				e.printStackTrace();
				result.setMessage("error");		
				result.setResult(false);
			}											
        	return null;
        }			
    }

    /**
     * 获取最新的商品分类表
     * 
     * @param callback
     *            回调监听器
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void getAllCategory(ICallback<T> callback) {
        // 创建请求HttpClient客户端
        HttpClient httpClient = new DefaultHttpClient();

        // 创建请求的url
        String url = getServiceUrl() + Env.Server.SERVIE_GET_CATEGORY;

        try {
            // 创建请求的对象
            HttpGet get = new HttpGet(new URI(url));

            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
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

                    List<T> beans = (List<T>) parseCategoryDataJson(json);
                    callback.onSuccess(beans);
                } else {
                    // 异常信息
                    callback.onFailed("entityIsNull");
                }
            } else {
                // 异常信息
                callback.onFailed("getStatusCodeError");
            }
        } catch (Exception error) {
            // 异常信息
            callback.onFailed("ExceptionError");
        }
    }

    /**
     * 解析分类json数据
     * 
     * @param json
     *            菜品分类json数据
     * @return 菜品分类信息list
     */
    private List<CategoryDataBean> parseCategoryDataJson(String json) {
        List<CategoryDataBean> categoryList = new ArrayList<CategoryDataBean>();
        try {
            JSONArray array = new JSONObject(json).getJSONArray("categories");

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                CategoryDataBean category = new CategoryDataBean(
                        obj.getInt("id"),
                        obj.getString("name"),
                        getBitmapUrl(obj.getString("picPath")));
                categoryList.add(category);
            }	
        } catch (JSONException error) {
        	Log.e("DownloadService", error.toString());
        }
        return categoryList;
    }

    /**
     * 获取某个会员号的历史订单记录
     * 
     * @param userId
     *            会员id
     * @param callback
     *            回调监听器
     */
    public <T> void getOrderHestory(String userId, ICallback<T> callback) {
        // 创建请求HttpClient客户端
        HttpClient httpClient = new DefaultHttpClient();

        // 创建请求的url
        String url = getServiceUrl() + Env.Server.SERVIE_GET_ORDERHESTORY
                + userId + "/orders";

        try {
            // 创建请求的对象
            HttpGet get = new HttpGet(new URI(url));

            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
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
                    Log.d("getOrderHestory", "json =" + json);
                    
                    //List<T> beans = (List<T>) parseOrderHestoryDataJson(json);
                    	
                    ReturnResultDataBean returnData = new ReturnResultDataBean();	
                    List<T> beans = (List<T>) parseOrderHestoryDataJson(json, returnData);			
                    		
                    //判断是否有订单数据，没有数据返回失败
                    if(returnData.getResult()) {
                    	if(beans.size() > 0) {
                    		callback.onSuccess(beans);
                    		Log.d("getOrderHestory", "callback.onSuccess(beans)");
                    	} else {
                    		callback.onFailed("orderIsNull");
                    		Log.d("getOrderHestory", "callback.onFailed(orderIsNull);");
                    	}			
                    } else {			
                    	callback.onFailed(returnData.getMessage());
                    	Log.d("getOrderHestory", "callback.onFailed(returnData.getMessage());");
                    }			
                } else {
                    // 异常信息
                    callback.onFailed("entityIsNull");
                }
            } else {
                // 异常信息
                callback.onFailed("getStatusCodeError");
            }
        } catch (Exception error) {
            // 异常信息
            callback.onFailed("ExceptionError");
        }
    }

    /**
     * 解析订单历史json数据
     * 
     * @param json
     *            订单历史json数据
     * @return 订单数据Bean列表list
     */
    private List<OrderHestoryDataBean> parseOrderHestoryDataJson(String json, ReturnResultDataBean result) {
        List<OrderHestoryDataBean> categoryList = new ArrayList<OrderHestoryDataBean>();
        
        try {	
            JSONArray array = new JSONObject(json).getJSONArray("orders");

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                OrderHestoryDataBean category = new OrderHestoryDataBean(
                        obj.getInt("id"), obj.getString("createAt"),
                        obj.getDouble("totalPrice"));
                categoryList.add(category);              
            }
            
            Log.e("parseOrderHestoryDataJson", "result.setMessage(ok);");
            result.setMessage("ok");				
			result.setResult(true);	
        } catch (JSONException error) {
        	Log.e("parseOrderHestoryDataJson", error.toString());
        	try {									
				JSONObject obj = new JSONObject(json);
				String err = obj.getString("exception");
				result.setMessage(err);		
				result.setResult(false);			
				Log.d("parseUserDiscountDataJson", "exception =" + err);
			} catch (JSONException e) {			
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setMessage("error");		
				result.setResult(false);
			}				
        }	
        return categoryList;
    }

    /**
     * 获取某个订单详情
     * 
     * @param callback
     *            回调监听器
     */								
    public <T> void getOrderInfo(String id, ICallback<T> callback) {
        // 创建请求HttpClient客户端
        HttpClient httpClient = new DefaultHttpClient();

        // 创建请求的url
        String url = getServiceUrl() + Env.Server.SERVIE_GET_ORDERINFO + id;
        		
        Log.d("getOrderInfo", "id=" + id);
        Log.d("getOrderInfo", "url=" + url);	
        
        try {
            // 创建请求的对象
            HttpGet get = new HttpGet(new URI(url));

            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
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

                    List<T> beans = (List<T>) parseOrderInfoDataJson(json);
                    callback.onSuccess(beans);
                } else {
                    // 异常信息
                    callback.onFailed("entityIsNull");
                }
            } else {
                // 异常信息
                callback.onFailed("getStatusCodeError");
            }
        } catch (Exception error) {
            // 异常信息
            callback.onFailed("ExceptionError");
        }
    }

    /**
     * 解析订单详情json数据
     * 
     * @param json
     *            订单详情json数据
     * @return 订单详情数据Bean列表list
     */	
    private List<GoodsDataBean> parseOrderInfoDataJson(String json) {
    	Log.d("parseOrderInfoDataJson", "json=" + json);	
        List<GoodsDataBean> OrderInfoList = new ArrayList<GoodsDataBean>();
        try {
            JSONArray array = new JSONObject(json).getJSONArray("orders");

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                GoodsDataBean orderInfo = new GoodsDataBean(
                        obj.getInt("dishId"), 
                        obj.getString("dishName"),
                        obj.getDouble("dishPrice"), 
                        getBitmapUrl(obj.getString("dishPicture")), 
                        obj.getInt("dishAmount"));
                OrderInfoList.add(orderInfo);		
            }							
        } catch (JSONException error) {
        	Log.e("parseOrderInfoDataJson", error.toString());
        }
        return OrderInfoList;
    }

    /**
     * 提交订单详情
     * 
     * @param tableInfo
     *            下单台数据信息
     * @param dataBeanList
     *            下单数据详情信息
     * @param callback
     *            回调监听器
     */	
    public <T> void postOrderInfo(TableInfoDataBean tableInfo,
            List<GoodsDataBean> dataBeanList, ICallback<T> callback) {
        // 创建请求HttpClient客户端
        HttpClient httpClient = new DefaultHttpClient();
        
        // 创建请求的url
        String url = getServiceUrl() + Env.Server.SERVIE_POST_ORDER;

        try {
            // 创建请求的对象
            HttpPost request = new HttpPost(new URI(url));

            request.setHeader("Content-Type", "application/json"); 
            	
            HttpParams params = new BasicHttpParams();

            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, 20000);

            // 设置请求超时
            HttpConnectionParams.setSoTimeout(params, 20000);
            

            request.setParams(params);

            // 先封装一个 JSON 对象
            JSONObject object = writeOrderJSON(tableInfo, dataBeanList);
            
            Log.d("Order Json Object:", "Json =" + object.toString());	
            	
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

                    List<T> beans = (List<T>) parseOrderDataJson(json);
                    callback.onSuccess(beans);
                } else {
                    // 异常信息
                    callback.onFailed("entityIsNull");
                }
            } else {
            	Log.d("error", "error code =" + httpResponse.getStatusLine().getStatusCode());
                // 异常信息	
                callback.onFailed("getStatusCodeError");
            }
        } catch (Exception error) {
            // 异常信息
            callback.onFailed("ExceptionError");
        }
    }

    /**
     * 转换数据到json格式
     * 
     * @param tableInfo
     *            下单台数据信息
     * @param dataBeanList
     *            下单数据详情信息
     * @return 转换后的json数据
     */
    public JSONObject writeOrderJSON(TableInfoDataBean tableInfo,
            List<GoodsDataBean> dataBeanList) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        try {	
            object.put("tableNumber", tableInfo.getTableId());
            object.put("attendeeNumber", tableInfo.getAttendeeNumber());
            object.put("serventId", tableInfo.getServantId());
            object.put("memberId", tableInfo.getCellphone());

            for (GoodsDataBean databean : dataBeanList) {
                JSONObject dataInfo = new JSONObject();

                dataInfo.put("dishId", databean.getId());
                dataInfo.put("dishAmount", databean.getCount());
                array.put(dataInfo);
            }		

            object.put("items", array);
        } catch (JSONException error) {
        	Log.e("DownloadService", error.toString());
        }	
        	
        return object;
    }
    
    /**
     * 解析下单返回详情json数据
     * 
     * @param json
     *            下单返回详情json数据
     * @return 下单返回详情数据Bean列表list
     */
    private List<OrderReturnDataBean> parseOrderDataJson(String json) {
    	List<OrderReturnDataBean> OrderResult = new ArrayList<OrderReturnDataBean>();
    	OrderReturnDataBean dataBean = new OrderReturnDataBean();
        String result = "false";
        String message = "";
    									
        try {
        	JSONObject obj = new JSONObject(json);
        	
        	result = obj.getString("result");
        	message = obj.getString("message");  
        			
        	dataBean.setResult(result);
        	dataBean.setMessage(message);
        } catch (JSONException error) {
        	Log.e("parseOrderDataJson", error.toString());
        }	
        OrderResult.add(dataBean);	
        			
        return OrderResult;
    }		
    
    /**
     * 由服务器图片地址转换到真实url地址
     * 
     * @param bitmapPath
     *            服务器下发的图片地址
     * @return 真实的图片url地址
     */
    private String getBitmapUrl(String bitmapPath) {
        String bitmapUrl = getServiceUrl() + "/eorder-ws/images" + bitmapPath;
        return bitmapUrl;
    }
    	
}