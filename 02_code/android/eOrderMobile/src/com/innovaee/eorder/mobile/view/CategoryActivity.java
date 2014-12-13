package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
	
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.CategoryDataBean;
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
public class CategoryActivity extends Activity {
	private final static String TAG = "FoodsShopActivity";

	private final static int MSG_UPDATE = 10001;

	private final static int MSG_INITDATA = 10002;

	private GridView gridView;

	private CategoryAdapter categoryAdapter;

	private List<Map<String, CategoryDataBean>> categoryDataList;

	private List<CategoryDataBean> categoryListData;

	private Handler handler = new Handler(Looper.getMainLooper()) {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE: {
				Log.d(TAG, "MSG_UPDATE!!!");
				categoryListData = (List<CategoryDataBean>) msg.obj;
				categoryAdapter = new CategoryAdapter(CategoryActivity.this,
						categoryListData);
				gridView.setAdapter(categoryAdapter);

				Log.d(TAG, "categoryListData.size =" + categoryListData.size());
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
		setContentView(R.layout.category_activity);

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
		categoryAdapter = new CategoryAdapter(this, categoryListData);

		gridView.setAdapter(categoryAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Log.d(TAG, "gridView.setOnItemClickListener!");
				Bundle bundle = new Bundle();
    				
        		bundle.putInt("selectItme", position);			
        		Intent intent = new Intent();
        									
        		intent.putExtras(bundle);
        					
        		intent.setClass(CategoryActivity.this, GoodsListActivity.class);	             						
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

		DataManager.getInstance(getApplicationContext()).getCategoryData(
				new IDataRequestListener<CategoryDataBean>() {
					@Override
					public void onRequestSuccess(
							final List<CategoryDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("FoodsShopActivity:", "onRequestSuccess!");
						if (data == null) {
							return;
						}

						categoryListData = data;
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
					public void onRequestSuccess(CategoryDataBean data) {
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
		msg.obj = (Object) categoryListData;
		handler.sendMessage(msg);
	}
	
	/**
	 * 初始化测试数据
	 */
	private void initTestData() {
		categoryListData = new ArrayList<CategoryDataBean>();
			
		CategoryDataBean databean1 = new CategoryDataBean();
		databean1.setId(1);
		databean1.setName("川菜");
		databean1.setBitmapUrl(ImagesUrl.Urls[0]);
		categoryListData.add(databean1);
						
		CategoryDataBean databean2 = new CategoryDataBean();
		databean2.setId(2);
		databean2.setName("湘菜");
		databean2.setBitmapUrl(ImagesUrl.Urls[3]);
		categoryListData.add(databean2);
		
		CategoryDataBean databean3 = new CategoryDataBean();
		databean3.setId(3);
		databean3.setName("粤菜");
		databean3.setBitmapUrl("http://pic21.nipic.com/20120525/2194567_150416722000_2.jpg");
		categoryListData.add(databean3);

		CategoryDataBean databean4 = new CategoryDataBean();
		databean4.setId(4);
		databean4.setName("酒水");
		databean4.setBitmapUrl("http://pic21.nipic.com/20120513/4666865_132407922000_2.jpg");
		categoryListData.add(databean4);

		CategoryDataBean databean5 = new CategoryDataBean();
		databean5.setId(5);
		databean5.setName("小菜");
		databean5.setBitmapUrl("http://p1.ftuan.com/2012/1129/11/20121129112313883.jpg");
		categoryListData.add(databean5);

		CategoryDataBean databean6 = new CategoryDataBean();
		databean6.setId(6);
		databean6.setName("主食");
		databean6.setBitmapUrl("http://www.photophoto.cn/m77/161/002/1610020750.jpg");
		categoryListData.add(databean6);

		CategoryDataBean databean7 = new CategoryDataBean();
		databean7.setId(7);
		databean7.setName("炖汤");
		databean7.setBitmapUrl(ImagesUrl.Urls[7]);
		categoryListData.add(databean7);
		
		CategoryDataBean databean8 = new CategoryDataBean();
		databean8.setId(8);
		databean8.setName("海鲜");
		databean8.setBitmapUrl(ImagesUrl.Urls[8]);
		categoryListData.add(databean8);
		
		CategoryDataBean databean9 = new CategoryDataBean();
		databean9.setId(9);
		databean9.setName("点心");
		databean9.setBitmapUrl(ImagesUrl.Urls[9]);
		categoryListData.add(databean9);
		
		CategoryDataBean databean10 = new CategoryDataBean();
		databean10.setId(10);
		databean10.setName("刺身");
		databean10.setBitmapUrl(ImagesUrl.Urls[10]);
		categoryListData.add(databean10);
						
		CategoryDataBean databean11 = new CategoryDataBean();
		databean11.setId(11);
		databean11.setName("小炒");
		databean11.setBitmapUrl(ImagesUrl.Urls[11]);
		categoryListData.add(databean11);
					
		CategoryDataBean databean12 = new CategoryDataBean();
		databean12.setId(12);
		databean12.setName("饮料");
		databean12.setBitmapUrl(ImagesUrl.Urls[12]);
		categoryListData.add(databean12);
						
		Log.d(TAG, "categoryListData.size =" + categoryListData.size());
				
		updateUi();
	}

}