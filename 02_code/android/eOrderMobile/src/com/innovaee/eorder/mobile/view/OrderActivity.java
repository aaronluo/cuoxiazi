/***********************************************
 * Filename    : OrderActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.databean.UserInfoDataBean;
import com.innovaee.eorder.mobile.zxing.activity.CaptureActivity;

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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 下订单界面
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class OrderActivity extends Activity {
    // 调试Tag
    private final static String TAG = "OrderActivity";

    // 改变菜品数量广播
    public static final String ACTION_INTENT_CHANGECOUNT = "com.eorder.action.changeCount";

    // 刷新界面消息
    public final static int MSG_UPDATE = 10001;

    // 初始化数据完成消息
    public final static int MSG_INITDATA = 10002;

    // 更新显示数量消息
    public final static int MSG_UPDATE_COUNT = 10003;

    // 更新折扣信息消息
    public final static int MSG_UPDATE_DISCOUNT = 10004;

    // 下单成功消息
    public final static int MSG_ORDER_SUCCESS = 10005;

    // 已经选择菜品list
    private List<GoodsDataBean> selectOrderGoods;

    // 列表显示listview
    private ListView listView;

    // 我的订单数据绑定器
    private OrderAdapter orderAdapter;

    // ActionBar
    private ActionBar actionBar;

    // 输入桌号文本编辑器
    private EditText inputTableId;

    // 输入会员号文本编辑器
    private EditText inputUserId;

    // 输入员工号文本编辑器
    private EditText inputEmployeeId;

    // 二维码扫描按钮
    private Button qrcodeBtn;

    // 获取折扣按钮
    private Button discountBtn;

    // 确认按钮
    private Button okBtn;

    // 原始价格文本显示器
    private TextView originalPriceTxt;

    // 实际价格文本显示器
    private TextView realPriceTxt;

    // 折扣文本显示器
    private TextView discountTxt;

    // 最终价格显示器
    private TextView allPriceTxt;

    // 折扣
    private Double discount;

    // 消息handler
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            // 刷新界面消息
            case MSG_UPDATE:
                break;

            // 初始化数据完成消息
            case MSG_INITDATA:
                break;

            // 更新显示数目消息
            case MSG_UPDATE_COUNT:
                selectOrderGoods = (List<GoodsDataBean>) msg.obj;
                displayPrice();

                // 通知MainViewActivity刷新
                sendBroadcastToMainActivity();
                break;

            // 更新折扣消息
            case MSG_UPDATE_DISCOUNT:
                String discountStr = String.valueOf(discount);

                // 如果折扣为10则显示无折扣
                if (discount != 10.0) {
                    discountStr = OrderActivity.this
                            .getString(R.string.order_text_no_discount);
                }

                discountTxt.setText(discountStr);
                displayPrice();
                break;

            // 更新订单成功消息
            case MSG_ORDER_SUCCESS:
                Toast.makeText(OrderActivity.this,
                        R.string.order_toast_order_successful,
                        Toast.LENGTH_SHORT).show();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                selectOrderGoods.clear();
                sendBroadcastToMainActivity();
                finish();
                break;

            default:
                break;
            }
        };
    };

    /**
     * 系统调用 Called when the activity is first created
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.order_activity);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        // 获取已经选择菜品列表
        ArrayList list = bundle.getParcelableArrayList("list");
        selectOrderGoods = (List<GoodsDataBean>) list.get(0);

        initView();

        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        listView = (ListView) findViewById(R.id.order_listView);

        actionBar = getActionBar();

        inputTableId = (EditText) findViewById(R.id.table_input_id);

        inputUserId = (EditText) findViewById(R.id.user_input_id);

        inputEmployeeId = (EditText) findViewById(R.id.employee_input_id);

        qrcodeBtn = (Button) findViewById(R.id.employee_2code_id);

        discountBtn = (Button) findViewById(R.id.discount_get_id);

        okBtn = (Button) findViewById(R.id.ok_button);

        originalPriceTxt = (TextView) findViewById(R.id.original_price);

        realPriceTxt = (TextView) findViewById(R.id.real_price);

        discountTxt = (TextView) findViewById(R.id.discount_price);

        allPriceTxt = (TextView) findViewById(R.id.goods_allprice);
    }

    /**
     * 初始化Data
     */
    private void initData() {
        // 默认折扣为10
        discount = 10.0;

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
            }
        });

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(true);

        orderAdapter = new OrderAdapter(OrderActivity.this, selectOrderGoods,
                handler);// 对应R中的id 

        listView.setAdapter(orderAdapter);
        setListViewHeightBasedOnChildren(listView);

        qrcodeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent openCameraIntent = new Intent(OrderActivity.this,
                        CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        discountBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String userId = inputUserId.getText().toString();

                if (userId != null && !userId.equals("")) {
                    getDiscountData(userId);
                } else {
                    Toast.makeText(OrderActivity.this,
                            R.string.order_toast_please_input_userid,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String tableId = inputTableId.getText().toString();
                String employeeId = inputEmployeeId.getText().toString();
                String userId = inputUserId.getText().toString();

                if (tableId.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            R.string.order_toast_please_input_tableId,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (employeeId.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            R.string.order_toast_please_input_employeeId,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userId.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            R.string.order_toast_please_input_userid,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                orderToService(selectOrderGoods);
            }
        });

        displayPrice();
    }

    /**
     * 系统自动调用 调用startActivityForResult到二维码扫描界面再返回
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            if (isNumeric(scanResult)) {
                inputEmployeeId.setText(scanResult);
            } else {
                Toast.makeText(OrderActivity.this,
                        R.string.order_toast_scanresult_fail,
                        Toast.LENGTH_SHORT).show();
            }
        }
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

    /**
     * 刷新显示价格等TextView
     */
    private void displayPrice() {
        Double allPrice = getAllPrice();

        // 计算实际价格
        Double realPrice = (allPrice * discount) / 10;

        // 原始价格文本显示器
        originalPriceTxt.setText(this.getApplicationContext().getString(
                R.string.order_text_originalprice)
                + this.getApplicationContext().getString(
                        R.string.main_griditem_text_rmb)
                + String.valueOf(allPrice));

        // 实际价格文本显示器
        realPriceTxt.setText(this.getApplicationContext().getString(
                R.string.order_text_realprice)
                + this.getApplicationContext().getString(
                        R.string.main_griditem_text_rmb)
                + String.valueOf(realPrice));

        // 折扣文本显示器
        // 如果折扣为10则显示无折扣
        if (discount != 10.0) {
            discountTxt.setText(this.getApplicationContext().getString(
                    R.string.order_text_discount)
                    + String.valueOf(discount));
        } else {
            discountTxt.setText(this.getApplicationContext().getString(
                    R.string.order_text_no_discount));
        }

        // 最终价格显示器
        allPriceTxt.setText(this.getApplicationContext().getString(
                R.string.main_griditem_text_rmb)
                + String.valueOf(realPrice));
    }

    /**
     * 更新折扣等ui
     */
    private void updateDiscountUi() {
        Message msg = Message.obtain();
        msg.what = MSG_UPDATE_DISCOUNT;
        handler.sendMessage(msg);
    }

    /**
     * 下单成功更新ui
     */
    private void orderSuccessful() {
        Message msg = Message.obtain();
        msg.what = MSG_ORDER_SUCCESS;
        handler.sendMessage(msg);
    }

    /**
     * 获取某个会员号的信息
     * 
     * @param userId
     *            会员id
     */
    private void getDiscountData(final String userId) {
        // DataManager必须在子线程里面调用
        new Thread() {
            @Override
            public void run() {
                DataManager.getInstance(OrderActivity.this)
                        .getUserDiscountData(userId,
                                new IDataRequestListener<UserInfoDataBean>() {
                                    // 获取成功回调函数，返回多个数据
                                    @Override
                                    public void onRequestSuccess(
                                            final List<UserInfoDataBean> data) {
                                        if (data == null) {
                                            return;
                                        }
                                        UserInfoDataBean userInfoDataBean = data
                                                .get(0);
                                        discount = userInfoDataBean
                                                .getDiscount();
                                        updateDiscountUi();
                                    }

                                    // 获取开始回调函数
                                    @Override
                                    public void onRequestStart() {
                                    }

                                    // 获取失败回调函数
                                    @Override
                                    public void onRequestFailed() {
                                    }

                                    // 获取成功回调函数，返回单个数据
                                    @Override
                                    public void onRequestSuccess(
                                            UserInfoDataBean data) {
                                    }
                                });
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 判断字符串是否为数字
     * 
     * @param str
     *            输入字符串
     * @return 是否为数字，true为数字，false不为数字
     */
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 重新设置listview的高
     * 
     * @param listView
     *            需要设置的listview
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 下单到服务器
     * 
     * @param selectOrderGoods
     *            下订单数据
     */
    private void orderToService(final List<GoodsDataBean> selectOrderGoods) {
        orderSuccessful();
    }
}
