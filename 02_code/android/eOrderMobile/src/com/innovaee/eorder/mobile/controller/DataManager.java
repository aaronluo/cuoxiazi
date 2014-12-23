/***********************************************
 * Filename		: DataManager.java																							
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.controller;

import java.util.List;
import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDetailDataBean;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;
import com.innovaee.eorder.mobile.databean.UserInfoDataBean;
import com.innovaee.eorder.mobile.service.DownloadService;
import com.innovaee.eorder.mobile.service.ICallback;

import android.content.Context;
import android.util.Log;

/**
 * 数据管理器
 * 通过该类获取相关的数据，分类列表，某分类菜品列表，会员信息等
 * @author wanglinglong
 * 
 */
public class DataManager {
	private static DataManager self;
	private static Context context;
		
	private DataManager(Context context) {
		// TODO
		this.context = context.getApplicationContext();
	}																			
	
	public synchronized static DataManager getInstance(Context contextTemp) {
		context = contextTemp.getApplicationContext();
							
		if (self == null) {
			self = new DataManager(context);
		}
		return self;
	}

	/**
	 * 获取所有分类列表
	 * 
	 * @param listener
	 */
	public void getCategoryData(
			final IDataRequestListener<CategoryDataBean> listener) {
		Log.d("DataManager:", "getCategoryData");
			
		if (listener == null) {
			// 无回调，也就没有实际意义
			return;
		}

		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getAllCategory(new ICallback<CategoryDataBean>() {
			@Override
			public void onStarted() {
				listener.onRequestStart();
			}

			@Override
			public void onSuccess(List<CategoryDataBean> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

			@Override
			public void onFailed(String error) {
				listener.onRequestFailed();
			}	

			@Override
			public void onSuccessT(CategoryDataBean response) {
				// TODO Auto-generated method stub
			}

		});
	}

	/**
	 * 获取所有商品列表
	 * 
	 * @param id 指定菜品分类的id
	 * @param listener 回调监听
	 */
	public void getGoodsData(int id,
			final IDataRequestListener<GoodsDataBean> listener) {
		if (listener == null) {
			// 无回调，也就没有实际意义
			return;
		}

		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getAllGoods(id, new ICallback<GoodsDataBean>() {
			@Override	
			public void onStarted() {
				listener.onRequestStart();
			}

			@Override
			public void onSuccess(List<GoodsDataBean> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

			@Override
			public void onFailed(String error) {
				listener.onRequestFailed();
			}

			@Override
			public void onSuccessT(GoodsDataBean response) {
				// TODO Auto-generated method stub
			}

		});
	}

	/**
	 * 获取商品详情数据
	 * 通过商品的id获取详情数据，一期无此需求，为后续保留接口
	 * @param id 商品的id
	 * @param listener 回调监听器
	 */
	public void getGoodsDetailData(int id,
			final IDataRequestListener<GoodsDetailDataBean> listener) {
		if (listener == null) {
			// 无回调，也就没有实际意义
			return;
		}

		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getAllGoods(id, new ICallback<GoodsDetailDataBean>() {
			@Override	
			public void onStarted() {
				listener.onRequestStart();
			}

			@Override
			public void onSuccess(List<GoodsDetailDataBean> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

			@Override
			public void onFailed(String error) {
				listener.onRequestFailed();
			}

			@Override
			public void onSuccessT(GoodsDetailDataBean response) {
				// TODO Auto-generated method stub
				boolean success = response != null;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

		});
	}
	
	/**
	 * 得到会员的折扣信息
	 * @param id 会员id
	 * @param iDataRequestListener 回调监听器
	 */
	public void getUserDiscountData(String id,
			final IDataRequestListener<UserInfoDataBean> iDataRequestListener) {
		if (iDataRequestListener == null) {
			// 无回调，也就没有实际意义
			return;
		}
						
		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getUserDiscountData(id, new ICallback<UserInfoDataBean>() {
			@Override	
			public void onStarted() {
				iDataRequestListener.onRequestStart();
			}
			
			@Override
			public void onSuccess(List<UserInfoDataBean> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					iDataRequestListener.onRequestSuccess(response);
				} else {
					iDataRequestListener.onRequestFailed();
				}
			}
			
			@Override
			public void onFailed(String error) {
				iDataRequestListener.onRequestFailed();
			}
			
			@Override
			public void onSuccessT(UserInfoDataBean response) {
				// TODO Auto-generated method stub
				boolean success = response != null;
				if (success) {
					iDataRequestListener.onRequestSuccess(response);
				} else {
					iDataRequestListener.onRequestFailed();
				}
			}

		});
	}
		
	/**
	 * 下单到服务器
	 * @param selectOrderGoods 下单的菜品数据list
	 * @param iDataRequestListener 回调监听器
	 */
	public void orderToService(List<GoodsDataBean> selectOrderGoods,
			final IDataRequestListener<String> iDataRequestListener) {
		if (iDataRequestListener == null) {
			// 无回调，也就没有实际意义
			return;
		}
						
		String id = "";			
		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getUserDiscountData(id, new ICallback<String>() {
			@Override	
			public void onStarted() {
				iDataRequestListener.onRequestStart();
			}
			
			@Override
			public void onSuccess(List<String> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					iDataRequestListener.onRequestSuccess(response);
				} else {
					iDataRequestListener.onRequestFailed();
				}
			}
			
			@Override
			public void onFailed(String error) {
				iDataRequestListener.onRequestFailed();
			}
			
			@Override
			public void onSuccessT(String response) {
				// TODO Auto-generated method stub
				boolean success = response != null;
				if (success) {
					iDataRequestListener.onRequestSuccess(response);
				} else {
					iDataRequestListener.onRequestFailed();
				}
			}

		});
	}
	
	/**
	 * 得到某个会员的历史订单记录
	 * @param id 会员id
	 * @param listener 回调监听器
	 */
	public void getOrderHestoryData(String id,
			final IDataRequestListener<OrderHestoryDataBean> listener) {
		if (listener == null) {
			// 无回调，也就没有实际意义
			return;
		}
					
		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getOrderHestory(id, new ICallback<OrderHestoryDataBean>() {
			@Override
			public void onStarted() {
				listener.onRequestStart();
			}
			
			@Override
			public void onSuccess(List<OrderHestoryDataBean> response) {
				boolean success = response != null && response.size() > 0;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

			@Override
			public void onFailed(String error) {
				listener.onRequestFailed();
			}
			
			@Override
			public void onSuccessT(OrderHestoryDataBean response) {
				// TODO Auto-generated method stub
				boolean success = response != null;
				if (success) {
					listener.onRequestSuccess(response);
				} else {
					listener.onRequestFailed();
				}
			}

		});
	}

	/**
	 * 类描述: 数据请求监听器 
	 * 功能详细描述:
	 * 
	 * @author wanglinglong
	 * @date [2014年9月24日]
	 */
	public static interface IDataRequestListener<T> {

		/**
		 * 功能简述: 数据请求开始回调接口
		 * 功能详细描述: 
		 * 注意:
		 */
		public void onRequestStart();

		/**
		 * 功能简述: 数据请求成功回调接口
		 * 功能详细描述: 
		 * 注意:返回单个数据Bean
		 * 
		 * @param data
		 *            更新后的缓存数据
		 */
		public void onRequestSuccess(T data);

		/**
		 * 功能简述: 数据请求成功回调接口
		 * 功能详细描述: 
		 * 注意:返回多个数据Bean列表list
		 * 
		 * @param data
		 *            更新后的缓存数据列表
		 */
		public void onRequestSuccess(List<T> data);

		/**
		 * 功能简述: 数据请求失败回调接口
		 * 功能详细描述: 
		 * 注意: 
		 */
		public void onRequestFailed();
	}

}