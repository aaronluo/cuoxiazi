package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
	
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;

import android.app.Activity;
import android.content.Intent;
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
		databean1.setId(0);
		databean1.setName("川菜");
		databean1.setBitmapUrl("http://food.hnr.cn/rmc/rmfl/201307/W020130719651943856865.jpg");
		classifyListData.add(databean1);

		ClassifyDataBean databean2 = new ClassifyDataBean();
		databean2.setId(0);
		databean2.setName("湘菜");
		databean2.setBitmapUrl("http://img5.imgtn.bdimg.com/it/u=2131026967,3181874696&fm=21&gp=0.jpg");
		classifyListData.add(databean2);

		ClassifyDataBean databean3 = new ClassifyDataBean();
		databean3.setId(0);
		databean3.setName("粤菜");
		databean3.setBitmapUrl("http://pic21.nipic.com/20120525/2194567_150416722000_2.jpg");
		classifyListData.add(databean3);

		ClassifyDataBean databean4 = new ClassifyDataBean();
		databean4.setId(0);
		databean4.setName("酒水");
		databean4.setBitmapUrl("http://pic21.nipic.com/20120513/4666865_132407922000_2.jpg");
		classifyListData.add(databean4);

		ClassifyDataBean databean5 = new ClassifyDataBean();
		databean5.setId(0);
		databean5.setName("小菜");
		databean5.setBitmapUrl("http://p1.ftuan.com/2012/1129/11/20121129112313883.jpg");
		classifyListData.add(databean5);

		ClassifyDataBean databean6 = new ClassifyDataBean();
		databean6.setId(0);
		databean6.setName("主食");
		databean6.setBitmapUrl("http://www.photophoto.cn/m77/161/002/1610020750.jpg");
		classifyListData.add(databean6);

		Log.d(TAG, "classifyListData.size =" + classifyListData.size());

		updateUi();
	}

}