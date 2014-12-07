package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
	
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

/**
 * 
 * @author wanglinglong
 * 
 */
public class MyOrderActivity extends Activity {
	private final static String TAG = "MyOrderActivity";
	
	private final static int MSG_UPDATE = 10001;
	private final static int MSG_INITDATA = 10002;
	
	private List<GoodsDataBean> selectOrderGoods;	
		
	private ListView listView;
	
	private MyOrderAdapter myOrderAdapter;
	
	private ActionBar actionBar;
	
	private TextView allPirce;
	
	private Button delAllBtn;
	
	private Button cancelBtn;
			
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
		setContentView(R.layout.myorder_activity);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
				
		ArrayList list = bundle.getParcelableArrayList("list");
		selectOrderGoods = (List<GoodsDataBean>) list.get(0);			
			
		initView();
		
		initData();							
	}
							
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
		
		cancelBtn = (Button) findViewById(R.id.cancel_button);
	    	
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
		
		myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this, selectOrderGoods, mHandler);//对应R中的id 
		
		listView.setAdapter(myOrderAdapter);
		
		delAllBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	 	 
				Log.d(TAG, "orderHestroySearch.setOnClickListener!");
				Handler handler = MainViewActivity.this.getMessageHandler();
				handler.sendEmptyMessage(MainViewActivity.MSG_DELALL);	
			}													
		});	
				
		cancelBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	 	 
				Log.d(TAG, "orderHestroySearch.setOnClickListener!");
				finish();
			}												
		});
							
		allPirce.setText(MyOrderActivity.this.getApplicationContext().getString(R.string.main_griditem_text_rmb) + String.valueOf(getAllPrice()));				
	}																												

	/**
	 * 得到总价格
	 * @return
	 */
	private Double getAllPrice() {
		Double allPrice = 0.0;
		
		for(GoodsDataBean datebean : selectOrderGoods) {
			allPrice = datebean.getPrice() + allPrice;
		}	
			
		return allPrice;
	}
	
}
