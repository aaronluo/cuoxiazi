package com.innovaee.eorder.mobile.controller;

import java.util.List;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDetailDataBean;
import com.innovaee.eorder.mobile.service.DownloadService;
import com.innovaee.eorder.mobile.service.ICallback;

import android.content.Context;

/**
 * 数据管理器
 * 
 * @author wanglinglong
 * 
 */
public class OrderManager {
	private static OrderManager self;
	private Context context;

	private OrderManager(Context context) {
		// TODO
		context = context.getApplicationContext();
	}

	public synchronized static OrderManager getInstance(Context context) {
		if (self == null) {
			self = new OrderManager(context);
		}
		return self;
	}

	/**
	 * 获取所有分类列表
	 * 
	 * @param listener
	 */
	public void getClassifyData(
			final IDataRequestListener<ClassifyDataBean> listener) {
		if (listener == null) {
			// 无回调，也就没有实际意义
			return;
		}

		DownloadService mDldMgr = DownloadService.getInstance(context);
		mDldMgr.getAllClassify(new ICallback<ClassifyDataBean>() {
			@Override
			public void onStarted() {
				listener.onRequestStart();
			}

			@Override
			public void onSuccess(List<ClassifyDataBean> response) {
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
			public void onSuccessT(ClassifyDataBean response) {
				// TODO Auto-generated method stub
			}

		});
	}

	/**
	 * 获取所有商品列表
	 * 
	 * @param id
	 * @param listener
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
	 * 
	 * @param id
	 * @param listener
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
	 * 
	 * <br>
	 * 类描述: 数据请求监听器 <br>
	 * 功能详细描述:
	 * 
	 * @author lichong
	 * @date [2014年9月24日]
	 */
	public static interface IDataRequestListener<T> {

		/**
		 * <br>
		 * 功能简述: <br>
		 * 功能详细描述: <br>
		 * 注意:
		 */
		public void onRequestStart();

		/**
		 * <br>
		 * 功能简述: <br>
		 * 功能详细描述: <br>
		 * 注意:
		 * 
		 * @param data
		 *            更新后的缓存数据
		 */
		public void onRequestSuccess(T data);

		/**
		 * <br>
		 * 功能简述: <br>
		 * 功能详细描述: <br>
		 * 注意:
		 * 
		 * @param data
		 *            更新后的缓存数据列表
		 */
		public void onRequestSuccess(List<T> data);

		/**
		 * <br>
		 * 功能简述: <br>
		 * 功能详细描述: <br>
		 * 注意:
		 */
		public void onRequestFailed();
	}

}