/***********************************************
 * Filename    : MyOrderActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 我的订单界面
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MyOrderActivity extends Activity {
    // 调试Tag
    private final static String TAG = "MyOrderActivity";

    // 删除所有已选项广播
    public static final String ACTION_INTENT_DELALL = "com.eorder.action.delAll";

    // 改变菜品数量广播
    public static final String ACTION_INTENT_CHANGECOUNT = "com.eorder.action.changeCount";

    // 内部消息定义，刷新消息
    public final static int MSG_UPDATE = 10001;

    // 初始化数据完成消息
    public final static int MSG_INITDATA = 10002;

    // 更新显示的下单数目消息
    public final static int MSG_UPDATE_COUNT = 10003;

    // 重新设置适配器消息
    public final static int MSG_RESET_ADAPTER = 10004;

    // 已选择菜品
    private List<GoodsDataBean> selectOrderGoods;

    // 列表显示listview
    private ListView listView;

    // 我的订单数据绑定器
    private MyOrderAdapter myOrderAdapter;

    // ActionBar
    private ActionBar actionBar;

    // 总价格显示textview
    private TextView allPirce;

    // 删除所有选择菜品按钮
    private Button delAllBtn;

    // 下单按钮
    private Button orderBtn;

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

            // 更新数目消息
            case MSG_UPDATE_COUNT:
                selectOrderGoods = (List<GoodsDataBean>) msg.obj;
                Double price = getAllPrice();
                allPirce.setText(MyOrderActivity.this.getApplicationContext()
                        .getString(R.string.main_griditem_text_rmb) + price);

                // 通知MainViewActivity刷新
                sendBroadcastToMainActivity();
                break;

            // 重新设置Adapter消息
            case MSG_RESET_ADAPTER:
                selectOrderGoods = (List<GoodsDataBean>) msg.obj;

                if (selectOrderGoods != null && selectOrderGoods.size() > 0) {
                    Double price1 = getAllPrice();
                    allPirce.setText(MyOrderActivity.this
                            .getApplicationContext().getString(
                                    R.string.main_griditem_text_rmb)
                            + price1);
                    myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this,
                            selectOrderGoods, handler);// 对应R中的id 
                    listView.setAdapter(myOrderAdapter);
                } else {
                    finish();
                }

                // 通知MainViewActivity刷新
                sendBroadcastToMainActivity();
                break;

            default:
                break;
            }
        };

    };

    /**
     * 系统自动调用 Called when the activity is first created
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_activity);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        ArrayList list = bundle.getParcelableArrayList("list");
        selectOrderGoods = (List<GoodsDataBean>) list.get(0);

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
        listView = (ListView) findViewById(R.id.myorder_listView);

        actionBar = getActionBar();

        allPirce = (TextView) findViewById(R.id.goods_allprice);

        delAllBtn = (Button) findViewById(R.id.del_all_button);

        orderBtn = (Button) findViewById(R.id.cancel_button);
    }

    /**
     * 初始化Data
     */
    private void initData() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(true);

        myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this,
                selectOrderGoods, handler);// 对应R中的id 

        listView.setAdapter(myOrderAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
            }
        });

        delAllBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent(ACTION_INTENT_DELALL);
                sendBroadcast(intent);
                finish();
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                openOrderActivity();
                finish();
            }
        });

        allPirce.setText(MyOrderActivity.this.getApplicationContext()
                .getString(R.string.main_griditem_text_rmb)
                + String.valueOf(getAllPrice()));
    }

    /**
     * 得到总价格
     * 
     * @return
     */
    private Double getAllPrice() {
        Double allPrice = 0.0;

        for (GoodsDataBean datebean : selectOrderGoods) {
            allPrice = datebean.getPrice() * datebean.getCount() + allPrice;
        }

        return allPrice;
    }

    /**
     * 跳转到下单界面
     */
    @SuppressWarnings("unchecked")
    private void openOrderActivity() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();

        // 这个list用于在budnle中传递 需要传递的ArrayList<Object>
        @SuppressWarnings("rawtypes")
        ArrayList list = new ArrayList();

        list.add(selectOrderGoods);

        bundle.putParcelableArrayList("list", list);

        intent.putExtras(bundle);
        intent.setClass(MyOrderActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * 发送广播到主界面
     */
    private void sendBroadcastToMainActivity() {
        Intent intent = new Intent(ACTION_INTENT_CHANGECOUNT);
        Bundle bundle = new Bundle();
        ArrayList list = new ArrayList();

        list.add(selectOrderGoods);

        bundle.putParcelableArrayList("list", list);

        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
