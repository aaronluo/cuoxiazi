package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

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
public class GoodsListActivity extends Activity {
	private final static String TAG = "FoodsListActivity";
	
	private final static int MSG_UPDATE = 20001;

	private final static int MSG_INITDATA = 20002;

	private GridView gridView;

	private GoodsAdapter goodsAdapter;

	private List<Map<String, GoodsDataBean>> goodsDataList;

	private List<GoodsDataBean> goodsListData;
	
	private int selectItem;
			
	private Handler handler = new Handler(Looper.getMainLooper()) {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE: {
				Log.d(TAG, "MSG_UPDATE!!!");
				goodsListData = (List<GoodsDataBean>) msg.obj;
				goodsAdapter = new GoodsAdapter(GoodsListActivity.this, goodsListData, handler);
				gridView.setAdapter(goodsAdapter);	

				Log.d(TAG, "goodsListData.size =" + goodsListData.size());
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
		setContentView(R.layout.goodslist_activity);
									
		Intent i = this.getIntent();
        Bundle b = i.getExtras();
        selectItem = b.getInt("selectItme", 0);
        							
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
		goodsAdapter = new GoodsAdapter(this, goodsListData, handler);
				
		gridView.setAdapter(goodsAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});

		// 先屏蔽数据获取接口
		// loadData();
	}

	/**
	 * 加载数据
	 */
	private void loadData() {
							
		DataManager.getInstance(getApplicationContext()).getGoodsData(selectItem, 
				new IDataRequestListener<GoodsDataBean>() {
					@Override
					public void onRequestSuccess(
							final List<GoodsDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("FoodsShopActivity:", "onRequestSuccess!");
						if (data == null) {
							return;
						}

						goodsListData = data;
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
					public void onRequestSuccess(GoodsDataBean data) {
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
		msg.obj = (Object) goodsListData;
		handler.sendMessage(msg);
	}

	/**
	 * 初始化测试数据
	 */
	private void initTestData() {
		goodsListData = new ArrayList<GoodsDataBean>();

		GoodsDataBean databean1 = new GoodsDataBean();
		databean1.setId(0);
		databean1.setName("川菜");
		databean1.setBitmapUrl("http://food.hnr.cn/rmc/rmfl/201307/W020130719651943856865.jpg");
		goodsListData.add(databean1);

		GoodsDataBean databean2 = new GoodsDataBean();
		databean2.setId(0);
		databean2.setName("湘菜");
		databean2.setBitmapUrl("http://img5.imgtn.bdimg.com/it/u=2131026967,3181874696&fm=21&gp=0.jpg");
		goodsListData.add(databean2);

		GoodsDataBean databean3 = new GoodsDataBean();
		databean3.setId(0);
		databean3.setName("粤菜");
		databean3.setBitmapUrl("http://pic21.nipic.com/20120525/2194567_150416722000_2.jpg");
		goodsListData.add(databean3);

		GoodsDataBean databean4 = new GoodsDataBean();
		databean4.setId(0);
		databean4.setName("酒水");
		databean4.setBitmapUrl("http://pic21.nipic.com/20120513/4666865_132407922000_2.jpg");
		goodsListData.add(databean4);

		GoodsDataBean databean5 = new GoodsDataBean();
		databean5.setId(0);
		databean5.setName("小菜");
		databean5.setBitmapUrl("http://p1.ftuan.com/2012/1129/11/20121129112313883.jpg");
		goodsListData.add(databean5);

		GoodsDataBean databean6 = new GoodsDataBean();
		databean6.setId(0);
		databean6.setName("主食");
		databean6.setBitmapUrl("http://www.photophoto.cn/m77/161/002/1610020750.jpg");
		goodsListData.add(databean6);

		Log.d(TAG, "goodsListData.size =" + goodsListData.size());

		updateUi();
	}

}