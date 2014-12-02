package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
	
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.test.ImagesUrl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author wanglinglong
 * 
 */
public class ClassifyActivity extends Activity {
	private final static String TAG = "FoodsShopActivity";

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
				Log.d(TAG, "MSG_UPDATE!!!");
				classifyListData = (List<ClassifyDataBean>) msg.obj;
				classifyAdapter = new ClassifyAdapter(ClassifyActivity.this,
						classifyListData);
				gridView.setAdapter(classifyAdapter);

				Log.d(TAG, "classifyListData.size =" + classifyListData.size());
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

		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.classify_activity);

		initView();

		initData();

		initTestData();
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
		classifyAdapter = new ClassifyAdapter(this, classifyListData);

		gridView.setAdapter(classifyAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Log.d(TAG, "gridView.setOnItemClickListener!");
				Bundle bundle = new Bundle();
    			
        		bundle.putInt("selectItme", position);			
        		Intent intent = new Intent();
        									
        		intent.putExtras(bundle);
        					
        		intent.setClass(ClassifyActivity.this, GoodsListActivity.class);	             						
                startActivity(intent);	
			}
		});	
		
		// 先屏蔽数据获取接口
		// loadData();
	}

	/**
	 * 加载数据
	 */
	private void loadData() {

		DataManager.getInstance(getApplicationContext()).getClassifyData(
				new IDataRequestListener<ClassifyDataBean>() {
					@Override
					public void onRequestSuccess(
							final List<ClassifyDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("FoodsShopActivity:", "onRequestSuccess!");
						if (data == null) {
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
		msg.obj = (Object) classifyListData;
		handler.sendMessage(msg);
	}
	
	/**
	 * 初始化测试数据
	 */
	private void initTestData() {
		classifyListData = new ArrayList<ClassifyDataBean>();
			
		ClassifyDataBean databean1 = new ClassifyDataBean();
		databean1.setId(1);
		databean1.setName("川菜");
		databean1.setBitmapUrl(ImagesUrl.Urls[0]);
		classifyListData.add(databean1);
						
		ClassifyDataBean databean2 = new ClassifyDataBean();
		databean2.setId(2);
		databean2.setName("湘菜");
		databean2.setBitmapUrl(ImagesUrl.Urls[3]);
		classifyListData.add(databean2);
		
		ClassifyDataBean databean3 = new ClassifyDataBean();
		databean3.setId(3);
		databean3.setName("粤菜");
		databean3.setBitmapUrl("http://pic21.nipic.com/20120525/2194567_150416722000_2.jpg");
		classifyListData.add(databean3);

		ClassifyDataBean databean4 = new ClassifyDataBean();
		databean4.setId(4);
		databean4.setName("酒水");
		databean4.setBitmapUrl("http://pic21.nipic.com/20120513/4666865_132407922000_2.jpg");
		classifyListData.add(databean4);

		ClassifyDataBean databean5 = new ClassifyDataBean();
		databean5.setId(5);
		databean5.setName("小菜");
		databean5.setBitmapUrl("http://p1.ftuan.com/2012/1129/11/20121129112313883.jpg");
		classifyListData.add(databean5);

		ClassifyDataBean databean6 = new ClassifyDataBean();
		databean6.setId(6);
		databean6.setName("主食");
		databean6.setBitmapUrl("http://www.photophoto.cn/m77/161/002/1610020750.jpg");
		classifyListData.add(databean6);

		ClassifyDataBean databean7 = new ClassifyDataBean();
		databean7.setId(7);
		databean7.setName("炖汤");
		databean7.setBitmapUrl(ImagesUrl.Urls[7]);
		classifyListData.add(databean7);
		
		ClassifyDataBean databean8 = new ClassifyDataBean();
		databean8.setId(8);
		databean8.setName("海鲜");
		databean8.setBitmapUrl(ImagesUrl.Urls[8]);
		classifyListData.add(databean8);
		
		ClassifyDataBean databean9 = new ClassifyDataBean();
		databean9.setId(9);
		databean9.setName("点心");
		databean9.setBitmapUrl(ImagesUrl.Urls[9]);
		classifyListData.add(databean9);
		
		ClassifyDataBean databean10 = new ClassifyDataBean();
		databean10.setId(10);
		databean10.setName("刺身");
		databean10.setBitmapUrl(ImagesUrl.Urls[10]);
		classifyListData.add(databean10);
						
		ClassifyDataBean databean11 = new ClassifyDataBean();
		databean11.setId(11);
		databean11.setName("小炒");
		databean11.setBitmapUrl(ImagesUrl.Urls[11]);
		classifyListData.add(databean11);
					
		ClassifyDataBean databean12 = new ClassifyDataBean();
		databean12.setId(12);
		databean12.setName("饮料");
		databean12.setBitmapUrl(ImagesUrl.Urls[12]);
		classifyListData.add(databean12);
						
		Log.d(TAG, "classifyListData.size =" + classifyListData.size());
				
		updateUi();
	}

}