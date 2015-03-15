/***********************************************
 * Filename    : DataManager.java                                             
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.controller;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;
import com.innovaee.eorder.mobile.databean.OrderInfoDataBean;
import com.innovaee.eorder.mobile.databean.OrderReturnDataBean;
import com.innovaee.eorder.mobile.databean.TableInfoDataBean;
import com.innovaee.eorder.mobile.databean.UserInfoDataBean;
import com.innovaee.eorder.mobile.service.DownloadService;
import com.innovaee.eorder.mobile.service.ICallback;

/**
 * 数据管理器 通过该类获取相关的数据，分类列表，某分类菜品列表，会员信息等
 * 
 */
public class DataManager {
    private static DataManager dataManager;
    private static Context context;
    
    /**
     * 构造函数
     * @param context 调用者的Context
     */
    private DataManager(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * 单例
     * @param contextTemp 调用者的Context
     * @return 单例自身
     */
    public synchronized static DataManager getInstance(Context contextTemp) {
        context = contextTemp.getApplicationContext();

        if (dataManager == null) {
            dataManager = new DataManager(context);
        }
        return dataManager;
    }

    /**
     * 获取所有分类列表
     * 
     * @param listener
     */
    public void getCategoryData(
            final IDataRequestListener<CategoryDataBean> listener) {
        if (listener == null) {
            // 无回调，也就没有实际意义
            return;
        }

        // 获取DownloadService实例
        DownloadService downloadService = DownloadService.getInstance(context);

        // 获取所有分类列表数据
        downloadService.getAllCategory(new ICallback<CategoryDataBean>() {
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
                    listener.onRequestFailed("error");
                }
            }

            @Override
            public void onFailed(String error) {
                listener.onRequestFailed(error);
            }

            @Override
            public void onSuccessT(CategoryDataBean response) {
            }

        });
    }

    /**
     * 获取所有商品列表
     * 
     * @param id
     *            指定菜品分类的id
     * @param listener
     *            回调监听
     */
    public void getGoodsData(int id,
            final IDataRequestListener<GoodsDataBean> listener) {
        if (listener == null) {
            // 无回调，也就没有实际意义
            return;
        }

        // 获取DownloadService实例
        DownloadService downloadService = DownloadService.getInstance(context);

        // 获取某个分类id下所有菜品列表
        downloadService.getAllGoods(id, new ICallback<GoodsDataBean>() {
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
                    listener.onRequestFailed("error");
                }
            }

            @Override
            public void onFailed(String error) {
                listener.onRequestFailed(error);
            }

            @Override
            public void onSuccessT(GoodsDataBean response) {
            }

        });
    }

    /**
     * 得到会员的折扣信息
     * 
     * @param id
     *            会员id
     * @param dataRequestListener
     *            回调监听器
     */
    public void getUserDiscountData(String id,
            final IDataRequestListener<UserInfoDataBean> dataRequestListener) {
        if (dataRequestListener == null) {
            // 无回调，也就没有实际意义
            return;
        }	
        
        // 获取DownloadService实例
        DownloadService downloadService = DownloadService.getInstance(context);

        // 获取某个会员号的折扣信息
        downloadService.getUserDiscountData(id, new ICallback<UserInfoDataBean>() {
            @Override
            public void onStarted() {
                dataRequestListener.onRequestStart();
            }

            @Override
            public void onSuccess(List<UserInfoDataBean> response) {
            	Log.d("DataManager:", "onSuccess");
                boolean success = response != null && response.size() > 0;
                if (success) {
                    dataRequestListener.onRequestSuccess(response);
                } else {
                    dataRequestListener.onRequestFailed("error");
                }
            }

            @Override
            public void onFailed(String error) {
            	Log.d("DataManager:", "onFailed");
                dataRequestListener.onRequestFailed(error);
            }

            @Override
            public void onSuccessT(UserInfoDataBean response) {
            	Log.d("DataManager:", "onSuccessT");
                boolean success = response != null;
                if (success) {
                    dataRequestListener.onRequestSuccess(response);
                } else {		
                    dataRequestListener.onRequestFailed("error");
                }
            }

        });
    }

    /**
     * 下单到服务器
     * 
     * @param tableInfo
     * 			  下单的台信息
     * @param dataBeanList
     *            下单的菜品数据list
     * @param dataRequestListener
     *            回调监听器
     */
    public void orderToService(TableInfoDataBean tableInfo, List<GoodsDataBean> dataBeanList,
            final IDataRequestListener<OrderReturnDataBean> dataRequestListener) {
        if (dataRequestListener == null) {
            // 无回调，也就没有实际意义
            return;
        }
        			
        // 获取DownloadService实例
        DownloadService downloadService = DownloadService.getInstance(context);

        // 下单到服务器
        downloadService.postOrderInfo(tableInfo, dataBeanList, new ICallback<OrderReturnDataBean>() {
            @Override	
            public void onStarted() {
                dataRequestListener.onRequestStart();
            }
            
            @Override
            public void onSuccess(List<OrderReturnDataBean> response) {
                boolean success = response != null && response.size() > 0;
                Log.d("onSuccess:", "result =" + response.get(0).getResult());
                Log.d("onSuccess:", "message =" + response.get(0).getMessage());
                if (success) {	
                    dataRequestListener.onRequestSuccess(response);
                } else {
                    dataRequestListener.onRequestFailed("error");
                }
            }

            @Override	
            public void onFailed(String error) {
            	Log.d("error", "error =" + error);
                dataRequestListener.onRequestFailed(error);
            }
            
            @Override
            public void onSuccessT(OrderReturnDataBean response) {
                boolean success = response != null;
                if (success) {
                    dataRequestListener.onRequestSuccess(response);
                } else {
                    dataRequestListener.onRequestFailed("error");
                }
            }

        });
    }

    /**
     * 得到某个会员的历史订单记录
     * 
     * @param id
     *            会员id
     * @param listener
     *            回调监听器
     */
    public void getOrderHestoryData(String id,
            final IDataRequestListener<OrderHestoryDataBean> listener) {
        if (listener == null) {
            // 无回调，也就没有实际意义
            return;
        }

        // 获取DownloadService实例
        DownloadService downloadService = DownloadService.getInstance(context);

        // 获取某个会员的历史订单记录
        downloadService.getOrderHestory(id, new ICallback<OrderHestoryDataBean>() {
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
                    listener.onRequestFailed("error");
                }
            }

            @Override
            public void onFailed(String error) {
                listener.onRequestFailed(error);
            }

            @Override
            public void onSuccessT(OrderHestoryDataBean response) {
                boolean success = response != null;
                if (success) {
                    listener.onRequestSuccess(response);
                } else {
                    listener.onRequestFailed("error");
                }
            }

        });
    }

    /**
     * 类描述: 数据请求监听器 功能详细描述:
     * 
     */
    public static interface IDataRequestListener<T> {

        /**
         * 功能简述: 数据请求开始回调接口 功能详细描述: 注意:
         */
        public void onRequestStart();

        /**
         * 功能简述: 数据请求成功回调接口 功能详细描述: 注意:返回单个数据Bean
         * 
         * @param data
         *            更新后的缓存数据
         */
        public void onRequestSuccess(T data);

        /**
         * 功能简述: 数据请求成功回调接口 功能详细描述: 注意:返回多个数据Bean列表list
         * 
         * @param data
         *            更新后的缓存数据列表
         */
        public void onRequestSuccess(List<T> data);

        /**
         * 功能简述: 数据请求失败回调接口 功能详细描述: 注意:
         */
        public void onRequestFailed(String error);
    }	

}