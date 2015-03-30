/***********************************************
 * Filename    : OrderHestoryDetailActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

/**
 * 我的订单界面
 * 
 */
public class OrderHestoryDetailActivity extends Activity {
    // 调试Tag
    private static final String TAG = "OrderHestoryDetailActivity";

    // 删除所有已选项广播
    public static final String ACTION_ADD_TO_ORDER = "com.eorder.action.addToOrder";
    
    // 内部消息定义，刷新消息
    public static final int MSG_UPDATE = 10001;

    // 初始化数据完成消息
    public static final int MSG_INITDATA = 10002;

    // 历史订单选择菜品
    private List<GoodsDataBean> hestorySelectOrderGoods;
    
    // 列表显示listview
    private ListView listView;

    // 我的订单数据绑定器
    private OrderHestoryDetailAdapter orderHestoryDetailAdapter;
    
    // ActionBar
    private ActionBar actionBar;

    // 消息handler
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            // 更新界面消息
            case MSG_UPDATE:
                break;

            // 数据初始化完成消息
            case MSG_INITDATA:
                break;
                	
            default:
                break;
            }
        };
    };

    /**
     * onCreate方法，系统默认调用
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhestorydetail_activity);
        
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        ArrayList list = bundle.getParcelableArrayList("list");
        hestorySelectOrderGoods = (List<GoodsDataBean>) list.get(0);

        initView();

        initData();
    }

    /**
     * 系统自动调用
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
        listView = (ListView) findViewById(R.id.orderhestorydetail_listView);

        actionBar = getActionBar();
        
    }
    
    /**
     * 初始化Data
     */
    @SuppressLint("NewApi")
	private void initData() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(true);

        orderHestoryDetailAdapter = new OrderHestoryDetailAdapter(OrderHestoryDetailActivity.this,
                hestorySelectOrderGoods, handler);// 对应R中的id 
        
        listView.setAdapter(orderHestoryDetailAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
            	GoodsDataBean data = hestorySelectOrderGoods.get(arg2);
            	sendBroadcastToMainActivity(data);
            	Toast.makeText(getApplicationContext(), R.string.orderhestorydetail_toast_to_order, Toast.LENGTH_SHORT).show();
            }					
        });

    }
    
    /**
     * 发送增加菜品广播到主界面
     */		
    private void sendBroadcastToMainActivity(GoodsDataBean data) {
        Intent intent = new Intent(ACTION_ADD_TO_ORDER);	
        Bundle bundle = new Bundle();
        ArrayList list = new ArrayList();
        List<GoodsDataBean> addToOrderGoods = new ArrayList<GoodsDataBean>();
        addToOrderGoods.add(data);		
        	
        list.add(addToOrderGoods);	
        
        bundle.putParcelableArrayList("list", list);
        	
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
