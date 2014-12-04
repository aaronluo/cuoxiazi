package com.innovaee.eorder.mobile.service;

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

import android.content.Context;

import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.Env;

/**
 * 下载管理器
 * 
 * @author wanglinglong
 * 
 */
public class OrderService implements GoodService, ClassifyService {
	private final String SERVER = Env.Server.SERVER_TEST;

	private static OrderService self;

	private Context context;

	/**
	 * 
	 * @param context
	 */
	private OrderService(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context can not be null");
		}
		context = context.getApplicationContext();
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized OrderService getInstance(Context context) {
		if (self == null) {
			self = new OrderService(context);
		}
		return self;
	}

	/**
	 * 获取最新的商品信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void getAllGoods(ICallback<T> callback) {
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
					List<T> beans = (List<T>) parseGoodsDataJson(json);
					callback.onSuccess(beans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析json数组对象
	 * 
	 * @param json
	 * @return
	 */
	private List<GoodsDataBean> parseGoodsDataJson(String json) {
		List<GoodsDataBean> goods = new ArrayList<GoodsDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("goods");

			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				GoodsDataBean good = new GoodsDataBean(obj.getInt("id"),
						obj.getString("name"), (Double) obj.getDouble("price"), obj.getString("url"));
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
	@SuppressWarnings("unchecked")
	@Override
	public <T> void findGoodsById(ICallback<T> callback) {
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

					T bean = (T) parseGoodsDetailJson(json);
					callback.onSuccessT(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析json的单个对象
	 * 
	 * @param json
	 * @return
	 */
	private GoodsDataBean parseGoodsDetailJson(String json) {
		JSONObject obj = null;
		try {
			obj = new JSONObject(json).getJSONObject("good");

			GoodsDataBean good = new GoodsDataBean(obj.getInt("id"),
					obj.getString("name"), (Double) obj.getDouble("price"), obj.getString("url"));
			return good;	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	/**
	 * 获取最新的商品分类表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void getAllClassify(ICallback<T> callback) {
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
					List<T> beans = (List<T>) parseClassifyDataJson(json);
					callback.onSuccess(beans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析json数组对象
	 * 
	 * @param json
	 * @return
	 */
	private List<ClassifyDataBean> parseClassifyDataJson(String json) {
		List<ClassifyDataBean> classifyList = new ArrayList<ClassifyDataBean>();
		try {
			JSONArray array = new JSONObject(json).getJSONArray("classify");

			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				ClassifyDataBean classify = new ClassifyDataBean(
						obj.getInt("id"), obj.getString("name"),
						obj.getString("url"));
				classifyList.add(classify);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return classifyList;
	}

	/**
	 * 获取最新上架的商品信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void getTopNewGoods(ICallback<T> callback) {
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
					List<T> beans = (List<T>) parseGoodsDataJson(json);
					callback.onSuccess(beans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取最热上架的商品信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void getTopHotGoods(ICallback<T> callback) {
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
					List<T> beans = (List<T>) parseGoodsDataJson(json);
					callback.onSuccess(beans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}