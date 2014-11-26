package com.innovaee.eorder.mobile.view;


import java.util.List;
import java.util.Map;
	
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
	
/**
 * 			
 * @author wanglinglong
 *
 */
public class FoodsShopActivity extends Activity {
	private final static int MSG_UPDATE = 10001;
		
	private final static int MSG_INITDATA = 10002;
	
	private GridView gridView;
	
	private ClassifyAdapter classifyAdapter;
	
	private List<Map<String, ClassifyDataBean>> classifyDataList;
	
	private List<ClassifyDataBean> classifyListData;
					
	
	private Handler handler = new Handler(Looper.getMainLooper()) {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE: {	
					classifyDataList = (List<Map<String, ClassifyDataBean>>) msg.obj;	
					classifyAdapter = new ClassifyAdapter(FoodsShopActivity.this, classifyDataList);										
					gridView.setAdapter(classifyAdapter);															
				}																																				
				break;					
					
            case MSG_INITDATA:
				break;
						
			default:
				break;
			}
		}	
				
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.foodsshop_activity);  
        
        initView();
        
        initData();
        
    }	
    
    /**
     * init View
     */
    private void initView() {
    	gridView = (GridView) findViewById(R.id.goods_gridview);     	
    }
    	
    /**
     * init data
     */
    private void initData() {
    	classifyAdapter = new ClassifyAdapter(this, classifyDataList);
    					
    	gridView.setAdapter(classifyAdapter);  
    	  		
    	gridView.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {  
        		
            }				
        });
    			
    	loadData();
    }
    	
    /**
     * 加载数据
     */				
    private void loadData() {
    		
    	DataManager.getInstance(getApplicationContext()).getClassifyData(
				new IDataRequestListener<ClassifyDataBean>() {
					@Override	
					public void onRequestSuccess(final List<ClassifyDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("FoodsShopActivity:", "onRequestSuccess!");
						if (data == null ) {							
							return;
						}					
						
						classifyListData = data;
					}
							
					@Override
					public void onRequestStart() {
						// TODO
						Log.d("FoodsShopActivity:", "onRequestStart!");
					}
						
					@Override
					public void onRequestFailed() {
						// TODO	
						Log.d("FoodsShopActivity:", "onRequestFailed!");
					}
							
					@Override
					public void onRequestSuccess(ClassifyDataBean data) {
						// TODO Auto-generated method stub
						Log.d("FoodsShopActivity:", "onRequestSuccess!");
						updateUi();
					}			
				});
    	
    }
    
    /**
     * 更新ui
     */
    private void updateUi() {
    	Message msg = Message.obtain();
  	  	msg.what = MSG_UPDATE;
  	  	msg.obj = (Object)classifyListData;	
  	  	handler.sendMessage(msg);  
    }				
}	