package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
public class OrderActivity extends Activity {
	//调试Tag
	private final static String TAG = "OrderActivity";
		
	//内部消息定义
	private final static int MSG_UPDATE = 10001;
	private final static int MSG_INITDATA = 10002;
			
	//已经选择菜品list
	private List<GoodsDataBean> selectOrderGoods;	
	
	//列表显示listview
	private ListView listView;
	
	//我的订单数据绑定器
	private MyOrderAdapter myOrderAdapter;
		
	//ActionBar
	private ActionBar actionBar;
		
	//消息handler
	private Handler mHandler = new Handler(Looper.getMainLooper()) {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE:	
				Log.d("leonwang:", "FolderListManagerActivity:MSG_UPDATE");								
				break;					
						
            case MSG_INITDATA:
				break;
						
			default:
				break;
			}
		};				
	};
			
	/** Called when the activity is first created. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_activity);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
				
		ArrayList list = bundle.getParcelableArrayList("list");
		selectOrderGoods = (List<GoodsDataBean>) list.get(0);			
			
		initView();
		
		initData();		
	}
	
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		listView = (ListView) findViewById(R.id.myorder_listView);
		
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
		
		myOrderAdapter = new MyOrderAdapter(OrderActivity.this, selectOrderGoods, mHandler);//对应R中的id 
		
		listView.setAdapter(myOrderAdapter);		
	}							
		
}