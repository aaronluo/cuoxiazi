/***********************************************
 * Filename    : OrderHestoryActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;

/**
 * 订单历史查询界面
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class OrderHestoryActivity extends Activity {
    // 调试Tag
    private static final String TAG = "OrderActivity";

    // 内部消息定义
    // 刷新UI消息
    public static final int MSG_UPDATE = 10001;

    // 初始化数据完成消息
    public static final int MSG_INITDATA = 10002;

    // 显示失败提示消息
    public static final int MSG_UPDATE_FAIL = 10003;

    // 已经选择菜品list
    private List<OrderHestoryDataBean> orderHestoryDataList;

    // 列表显示listview
    private ListView listView;

    // 我的订单数据绑定器
    private OrderHestoryAdapter orderHestoryAdapter;

    // 历史记录查询订单菜品，仅供测试
    private List<GoodsDataBean> orderHestoryGoods;

    // ActionBar
    private ActionBar actionBar;

    // 会员id
    private String userId;

    // 消息handler
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            // 刷新界面消息
            case MSG_UPDATE:
                orderHestoryAdapter = new OrderHestoryAdapter(
                        OrderHestoryActivity.this, orderHestoryDataList);
                listView.setAdapter(orderHestoryAdapter);
                break;

            // 数据初始化完成消息
            case MSG_INITDATA:
                break;

            // 显示失败消息
            case MSG_UPDATE_FAIL:
                Toast.makeText(OrderHestoryActivity.this,
                        R.string.order_toast_no_userid_fail, Toast.LENGTH_SHORT)
                        .show();
                finish();
                break;

            default:
                break;
            }
        };
    };

    /**
     * onCreate方法，系统默认调用
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhestory_activity);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("userid");

        ArrayList list = bundle.getParcelableArrayList("list");
        orderHestoryGoods = (List<GoodsDataBean>) list.get(0);

        initView();

        initData();

        // 加载对应会员号的历史订单记录
        loadOrderHestoryListData(userId);
    }

    /**
     * 系统调用
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        listView = (ListView) findViewById(R.id.order_hestory_listView);

        actionBar = getActionBar();
    }

    /**
     * 初始化Data
     */
    private void initData() {
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				openMyOrder();				
			}
        });

        // 设置ActionBar的属性
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.ic_publish_bottom_pan_back);
        actionBar.setHomeButtonEnabled(true);

        orderHestoryAdapter = new OrderHestoryAdapter(
                OrderHestoryActivity.this, orderHestoryDataList);

        listView.setAdapter(orderHestoryAdapter);
    }

    /**
     * 更新ListView ui
     */
    private void updateUi() {
        Message msg = Message.obtain();
        msg.what = MSG_UPDATE;
        msg.obj = (Object) orderHestoryDataList;
        handler.sendMessage(msg);
    }

    /**
     * 提醒失败
     */
    private void updateFailUi() {
        Message msg = Message.obtain();
        msg.what = MSG_UPDATE_FAIL;
        handler.sendMessage(msg);
    }

    /**
     * 进入我的订单
     */
    private void openMyOrder() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();

        // 这个list用于在budnle中传递 需要传递的ArrayList<Object>
        ArrayList list = new ArrayList();

        list.add(orderHestoryGoods);

        bundle.putParcelableArrayList("list", list);

        intent.putExtras(bundle);
        intent.setClass(OrderHestoryActivity.this, MyOrderActivity.class);
        startActivity(intent);
    }

    /**
     * 获取会员的历史订单
     * 
     * @param userId
     */
    private void loadOrderHestoryListData(final String userId) {
        // DataManager必须放在子线程中调用
        new Thread() {
            @Override
            public void run() {
                DataManager
                        .getInstance(OrderHestoryActivity.this)
                        .getOrderHestoryData(
                                userId,
                                new IDataRequestListener<OrderHestoryDataBean>() {
                                    // 获取成功回调函数，返回多个数据
                                    @Override
                                    public void onRequestSuccess(
                                            final List<OrderHestoryDataBean> data) {
                                        if (data == null) {
                                            return;
                                        }

                                        orderHestoryDataList = data;
                                        updateUi();
                                    }

                                    // 获取数据开始回调函数
                                    @Override
                                    public void onRequestStart() {
                                    }

                                    // 获取数据失败回调函数
                                    @Override
                                    public void onRequestFailed(String error) {
                                        updateFailUi();
                                    }

                                    // 获取数据成功回调函数，返回单个数据
                                    @Override
                                    public void onRequestSuccess(
                                            OrderHestoryDataBean data) {
                                    }
                                });
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

}