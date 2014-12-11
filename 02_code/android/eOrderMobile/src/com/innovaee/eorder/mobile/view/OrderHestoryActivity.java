package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
		
/**
 * 
 * @author wanglinglong
 * 
 */
public class OrderHestoryActivity extends Activity {
	//调试Tag
	private final static String TAG = "OrderActivity";
		
	//内部消息定义
	public final static int MSG_UPDATE = 10001;
	public final static int MSG_INITDATA = 10002;
		
	//已经选择菜品list
	private List<OrderHestoryDataBean> orderHestoryDataList;	
						
	//列表显示listview
	private ListView listView;
		
	//我的订单数据绑定器
	private OrderHestoryAdapter orderHestoryAdapter;
		
	//ActionBar
	private ActionBar actionBar;
				
	private String userId;
		
	//消息handler
	private Handler handler = new Handler(Looper.getMainLooper()) {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_UPDATE:	
					Log.d("leonwang:", "FolderListManagerActivity:MSG_UPDATE");	
					//orderHestoryDataList = (List<OrderHestoryDataBean>) msg.obj;	
					orderHestoryAdapter = new OrderHestoryAdapter(OrderHestoryActivity.this, orderHestoryDataList);								
					listView.setAdapter(orderHestoryAdapter);							
					break;					
											
	            case MSG_INITDATA:
					break;
										
				default:
					break;
			}
		};				
	};	
			
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderhestory_activity);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("userid");
        				
        initView();
		
		initData();		
		
		loadOrderHestoryListData(userId);
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
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub	
			}												
        });	
				
		actionBar.setDisplayHomeAsUpEnabled(true);
				
		orderHestoryAdapter = new OrderHestoryAdapter(OrderHestoryActivity.this, orderHestoryDataList);
								
		listView.setAdapter(orderHestoryAdapter);	
		
		//initTestData();
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
	 * 获取会员的历史订单
	 * @param userId
	 */
	private void loadOrderHestoryListData(final String userId) {	
		new Thread() {
			@Override	
			public void run(){			
			DataManager.getInstance(OrderHestoryActivity.this).getOrderHestoryData(userId, 
					new IDataRequestListener<OrderHestoryDataBean>() {
						@Override			
						public void onRequestSuccess(
								final List<OrderHestoryDataBean> data) {
							// TODO Auto-generated method stub
							Log.d("MainViewActivity:", "onRequestSuccess!");
							if (data == null) {
								return;
							}
							
							orderHestoryDataList = data;
							updateUi();		
						}			
									
						@Override
						public void onRequestStart() {
							// TODO
							Log.d("MainViewActivity:", "onRequestStart!");
						}
							
						@Override
						public void onRequestFailed() {
							// TODO
							Log.d("MainViewActivity:", "onRequestFailed!");
						}
	
						@Override
						public void onRequestSuccess(OrderHestoryDataBean data) {
							// TODO Auto-generated method stub
							Log.d("MainViewActivity:", "onRequestSuccess!");
						}			
					});				
				handler.sendEmptyMessage(0);
			}
		}.start();		
	}
		
	/**
	 * 初始化测试数据
	 */
	private void initTestData() {
		orderHestoryDataList = new ArrayList<OrderHestoryDataBean>();
			
		OrderHestoryDataBean databean1 = new OrderHestoryDataBean();
		databean1.setId(100001);
		databean1.setTime("2014-8-01");
		databean1.setTotalPrice(98.5);	
		orderHestoryDataList.add(databean1);	
						
		OrderHestoryDataBean databean2 = new OrderHestoryDataBean();
		databean2.setId(100002);
		databean2.setTime("2014-8-03");
		databean2.setTotalPrice(143.5);	
		orderHestoryDataList.add(databean2);

		OrderHestoryDataBean databean3 = new OrderHestoryDataBean();
		databean3.setId(100003);
		databean3.setTime("2014-10-06");
		databean3.setTotalPrice(45.0);	
		orderHestoryDataList.add(databean3);

		OrderHestoryDataBean databean4 = new OrderHestoryDataBean();
		databean4.setId(100004);
		databean4.setTime("2014-10-10");
		databean4.setTotalPrice(112.8);	
		orderHestoryDataList.add(databean4);
		
		OrderHestoryDataBean databean5 = new OrderHestoryDataBean();
		databean5.setId(100005);
		databean5.setTime("2014-10-25");
		databean5.setTotalPrice(199.8);	
		orderHestoryDataList.add(databean5);
																			
		OrderHestoryDataBean databean6 = new OrderHestoryDataBean();
		databean6.setId(100006);
		databean6.setTime("2014-11-23");
		databean6.setTotalPrice(1999.9);	
		orderHestoryDataList.add(databean6);
					
		Log.d(TAG, "orderHestoryDataList.size =" + orderHestoryDataList.size());

		updateUi();	
	}
	
}