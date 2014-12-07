package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.DisplayUtil;
import com.innovaee.eorder.mobile.util.FeedType;
import com.innovaee.eorder.mobile.util.FeedTypeAdapter;
	
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;  				
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
	
@SuppressWarnings("deprecation")
public class MainViewActivity extends Activity {
	//Log输出Tag
	private final static String TAG = "MainViewActivity";
			
	//消息定义
	public final static int MSG_UPDATE = 20001;
	public final static int MSG_INITDATA = 20002;	
	public final static int MSG_UPDATE_POPMENU = 20003;
	public final static int MSG_ORDER = 20004;
		
	//自定义ActionBar
	private ActionBar actionBar;

	//订单数量显示view
	private View orderActionView;
	
	//订单数量显示view里数量TextView
	private TextView orderCountView;
	
	//弹出菜单Adapter
	private FeedTypeAdapter feedTypeAdapter; 
	
	//分类选择弹出菜单
	private PopupWindow feedTypePopup;
	
	//ActionBar上显示的当前查看分类
	private TextView feedTypeName;
	
	//最近查看的分类类型
	private FeedType lastFeedType;
						
	//所有分类类型列表
	private List<FeedType> feedTypeList;
	
	//分类弹出菜单中listview
	private ListView popupMenuList;
	
	//显示工具类
	private DisplayMetrics displayMetrics;	
	 
	//菜品显示GridView
	private GridView gridView;
	
	//菜品显示数据绑定器
	private GoodsAdapter goodsAdapter;	
		
	//当前分类的菜品列表
	private List<GoodsDataBean> goodsListData;
		
	//所有分类列表
	private List<ClassifyDataBean> classifyListData;		
		
	//已经选择的菜品
	private List<GoodsDataBean> selectOrderGoods;	
	
	EditText orderHestoryInput;
			
	private Handler handler = new Handler(Looper.getMainLooper()) {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE: {
				Log.d(TAG, "MSG_UPDATE!!!");
				goodsListData = (List<GoodsDataBean>) msg.obj;
				goodsAdapter = new GoodsAdapter(MainViewActivity.this, goodsListData, handler);
				gridView.setAdapter(goodsAdapter);
				
				Log.d(TAG, "goodsListData.size =" + goodsListData.size());
				}
				break;
				
			case MSG_INITDATA:	
				Log.d(TAG, "MSG_INITDATA!!!");
				break;
				
			case MSG_UPDATE_POPMENU:
				Log.d(TAG, "MSG_UPDATE_POPMENU!!!");
				classifyListData = (List<ClassifyDataBean>) msg.obj;
				feedTypeList.clear();
				feedTypeList = changeClassifyToFeedType(classifyListData);				
				feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this, feedTypeList);
				break;				

			case MSG_ORDER:
				Log.d(TAG, "MSG_ORDER!!!");
				int select = msg.arg1;		
				if (selectOrderGoods == null) {
					selectOrderGoods = new ArrayList<GoodsDataBean>();
				}	
					
				if ((goodsListData != null) && (goodsListData.size() != 0)) {
					selectOrderGoods.add(goodsListData.get(select));																
					updateMyOrderCount(selectOrderGoods.size());
				}				
				break;		
					
			default:
				break;
			}
		}
			
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintab_activity);		
					
		initView();
			
		initData();	
		
		initTestData();				
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu paramMenu) {	
		getMenuInflater().inflate(R.menu.main_activity_actions, paramMenu);
				
	    //订单数量显示View
	    final MenuItem localMenuItem2 = paramMenu.findItem(R.id.action_order);
	    orderActionView = localMenuItem2.getActionView();
	    orderCountView = ((TextView)this.orderActionView.findViewById(R.id.order_count));										
	    orderActionView.setOnClickListener(new View.OnClickListener()
	    {	
	    	public void onClick(View paramAnonymousView)
	    	{
	    		Log.d(TAG, "orderCountView.onClick");
	    		if ((selectOrderGoods != null) && (selectOrderGoods.size() > 0)) {
	    			openMyOrder();
	    		} else {
	    			Toast.makeText(MainViewActivity.this, R.string.toast_please_order_first, Toast.LENGTH_SHORT).show();
	    		}	    		
	    	}	
	    });
	    			
	    orderActionView.setOnLongClickListener(new View.OnLongClickListener()
	    {
	    	public boolean onLongClick(View paramAnonymousView)
	    	{		
	    		Log.d(TAG, "orderCountView.onLongClick");
	    		if ((selectOrderGoods != null) && (selectOrderGoods.size() > 0)) {
	    			openMyOrder();
	    		} else {
	    			Toast.makeText(MainViewActivity.this, R.string.toast_please_order_first, Toast.LENGTH_SHORT).show();
	    		}		
	    		return true;
	    	}		
	    });		
	    	
	    return super.onCreateOptionsMenu(paramMenu);
	}	
		
			
	@Override	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Log.d(TAG, "onOptionsItemSelected item.getItemId()=" + item.getItemId());
				
	    switch (item.getItemId()) {
	        case R.id.action_menu_settings:
	            openSetting();
	            return true;			
	            						
	        case R.id.action_menu_about:
	            openAbout();
	            return true;
	            		
	        case R.id.action_menu_fresh:
	        	return true;
	        			        				
	        default:	
	            return super.onOptionsItemSelected(item);
	    }
	}
		
	/**
	 * 初始化控件
	 */
	private void initView() {
		displayMetrics = new DisplayMetrics(); 
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
		
		actionBar = getActionBar();	
		
		gridView = (GridView) findViewById(R.id.goods_gridview);
	}

	/**
	 * 初始化Data
	 */	
	private void initData() {
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(true);
			 									
		initActionBarCustomView();
		
		goodsAdapter = new GoodsAdapter(this, goodsListData, handler);
			
		gridView.setAdapter(goodsAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Log.d(TAG, "gridView.setOnItemClickListener!");			
				if (selectOrderGoods == null) {
					selectOrderGoods = new ArrayList<GoodsDataBean>();
				}
											
				if ((goodsListData != null) && (goodsListData.size() != 0)) {
					selectOrderGoods.add(goodsListData.get(position));																
					updateMyOrderCount(selectOrderGoods.size());
				}			
			}				
		});				
							
		changeFeedType(lastFeedType);
			
		loadFeedTypeListData();
	}																
		
	/**
	 * 初始化ActionBar自定义View
	 */
	private void initActionBarCustomView() {									
		LayoutInflater inflater;			
		inflater = (LayoutInflater) MainViewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
		View localView = inflater.inflate(R.layout.order_hestory_view, null);		
		orderHestoryInput = ((EditText)localView.findViewById(R.id.order_hestory_input));
		ImageView orderHestroySearch = ((ImageView)localView.findViewById(R.id.order_hestory_search));
			
		feedTypeName = ((TextView)localView.findViewById(R.id.feed_type_name));
		feedTypeName.setText(R.string.feed_type_hot);
				
		actionBar.setCustomView(localView);
		actionBar.setDisplayShowCustomEnabled(true);	
			
		orderHestroySearch.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	 	 
				Log.d(TAG, "orderHestroySearch.setOnClickListener!");
				clickOnOrderHestory((View)actionBar.getCustomView());
			}								
		});
							
		feedTypeName.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	  
				Log.d(TAG, "feedTypeName.setOnClickListener!");
				//clickOnFeedType((View)actionBar.getCustomView());
				clickOnFeedType((View)paramAnonymousView.getParent());		
			}										
		});
	}	

	
	/**
	 * 进去设置界面
	 */
	private void openSetting() {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();									
		intent.putExtras(bundle);						
		intent.setClass(MainViewActivity.this, SettingsActivity.class);	             						
        startActivity(intent);			
	}		
					
	/**
	 * 进入关于界面
	 */	
	private void openAbout() {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();		
		intent.putExtras(bundle);						
		intent.setClass(MainViewActivity.this, AboutActivity.class);	  						
        startActivity(intent);	
	}	
					
	/**
	 * 进入订单记录
	 */
	private void oepnOrderHestory(int userId) {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();
		
		bundle.putInt("userid", userId);			
							
		intent.putExtras(bundle);						
		intent.setClass(MainViewActivity.this, OrderHestoryActivity.class);	  
			
        startActivity(intent);	
	}	
		
	/**
	 * 进入我的订单
	 */
	private void openMyOrder() {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();
		
		//这个list用于在budnle中传递 需要传递的ArrayList<Object>
		ArrayList list = new ArrayList();

		list.add(selectOrderGoods);
			
		bundle.putParcelableArrayList("list",list);
					
		intent.putExtras(bundle);						
		intent.setClass(MainViewActivity.this, MyOrderActivity.class);	             						
        startActivity(intent);
	}
	
	/**
	 * 切换当前的FeedType，并更新显示
	 * @param paramFeedType
	 */
	private void changeFeedType(FeedType paramFeedType)
	{
	    int i;	
	   
	    if (this.lastFeedType != paramFeedType)
	    {
	    	lastFeedType = paramFeedType;
	    	this.feedTypeName.setText(lastFeedType.getTypeName());
	    		
	    	loadClassifyData();
	    }		    	
	}					
		
	/**
	 * 初始化Popmenu菜单
	 */
	private void initFeedTypePopup()
	{	
		//初始化PipMenu测试数据
		initPopMenuData();		
		feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this, feedTypeList);
		
		
		LayoutInflater inflater;	
		inflater = (LayoutInflater) MainViewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		popupMenuList = (ListView)inflater.inflate(R.layout.drop_down_list, null);	
	    //Utils.disableOverScroll(localListView);	
		popupMenuList.setAdapter(this.feedTypeAdapter);			
	    		
		popupMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener()
	    {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
			{
				MainViewActivity.this.changeFeedType(feedTypeList.get(paramAnonymousInt));	
				new Handler().postDelayed(new Runnable()
				{
					public void run()
					{
						MainViewActivity.this.feedTypePopup.dismiss();
					}	
				}	
				, 50L);
			}		
	    });
		
	    this.feedTypePopup = new PopupWindow(popupMenuList, this.displayMetrics.widthPixels / 3, -2, true);
	    this.feedTypePopup.setBackgroundDrawable(new BitmapDrawable());
	    this.feedTypePopup.setOutsideTouchable(true);
	}
				
	/**
	 * 处理Actionbar中FeedType自定义View点击事件
	 * @param paramView
	 */	
	public void clickOnFeedType(View paramView)
	{
		if(this.feedTypePopup == null) {
			initFeedTypePopup();
		}
		
	    if(this.feedTypePopup.isShowing())
	    {
	    	this.feedTypePopup.dismiss();
	    	return;
	    }
	    
	    this.feedTypePopup.showAsDropDown(paramView, -DisplayUtil.dipToPixels(this.displayMetrics, 10.0F), -DisplayUtil.dipToPixels(this.displayMetrics, 5.0F));
	}										
							
	/**
	 * 处理ActionBar中orderHestory自定义View点击事件
	 * @param paramView
	 */
	public void clickOnOrderHestory(View paramView)
	{				
		Log.d(TAG, "clickOnOrderHestory!");		
		if(orderHestoryInput != null) {
			String inputString = orderHestoryInput.getText().toString();
			
			if(inputString.equals("")) {
				Toast.makeText(getApplicationContext(), R.string.toast_please_input_userid, Toast.LENGTH_SHORT).show();
			} else {	
				int inputNumber = Integer.parseInt(inputString);
				
				oepnOrderHestory(inputNumber);
			}	
		}
	}						
							
	/**
	 * 更新我的订单数目
	 * @param count
	 */
	private void updateMyOrderCount(int count) {
	    String str = "";
	    		
	    if (count > 99) {
	    	str = "99+";
	    } else if (count > 0) {
	    	str = String.valueOf(count);	
	    } else {	
	    	orderCountView.setText(str);
	    	orderCountView.setVisibility(View.INVISIBLE);
	    }				
	    	
		orderCountView.setText(str);	
		orderCountView.setVisibility(View.VISIBLE);
	}																						
		
	/**	
	 * 更新GridView ui
	 */
	private void updateUi() {
		Message msg = Message.obtain();
		msg.what = MSG_UPDATE;
		msg.obj = (Object) goodsListData;
		handler.sendMessage(msg);
	}

	/**
	 * 更新Popmenu ui
	 */
	private void updatePopmenuUi() {
		Message msg = Message.obtain();
		msg.what = MSG_UPDATE_POPMENU;	
		msg.obj = (Object) classifyListData;
		handler.sendMessage(msg);
	}	
						
	/**	
	 * 加载某个分类菜品列表数据
	 */								
	private void loadClassifyData() {										
		DataManager.getInstance(MainViewActivity.this).getGoodsData(lastFeedType.getClassifyId(), 
				new IDataRequestListener<GoodsDataBean>() {
					@Override
					public void onRequestSuccess(
							final List<GoodsDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("MainViewActivity:", "onRequestSuccess!");
						if (data == null) {
							return;
						}

						goodsListData = data;
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
					public void onRequestSuccess(GoodsDataBean data) {
						// TODO Auto-generated method stub
						Log.d("MainViewActivity:", "onRequestSuccess!");
						updateUi();
					}
				});
	}	
		
	/**
	 * 加载所有分类列表信息
	 */	
	private void loadFeedTypeListData() {					
		DataManager.getInstance(MainViewActivity.this).getClassifyData(
				new IDataRequestListener<ClassifyDataBean>() {
					@Override
					public void onRequestSuccess(
							final List<ClassifyDataBean> data) {
						// TODO Auto-generated method stub
						Log.d("MainViewActivity:", "onRequestSuccess!");
						if (data == null) {
							return;
						}
						
						classifyListData = data;
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
					public void onRequestSuccess(ClassifyDataBean data) {
						// TODO Auto-generated method stub
						Log.d("MainViewActivity:", "onRequestSuccess!");
						updatePopmenuUi();
					}	
				});

	}
	
	/**	
	 * 转换分类数据为切换分类所需的数据类型
	 * @param classifyListData
	 * @return
	 */
	private List<FeedType> changeClassifyToFeedType(List<ClassifyDataBean> classifyListData) {
		List<FeedType> feedTypeList = new ArrayList<FeedType>();
						
		for(ClassifyDataBean databean: classifyListData) {
			FeedType feedType = new FeedType(databean.getId(), databean.getName());
			feedTypeList.add(feedType);	
		}
			
		return feedTypeList;
	}
		
		
	//***********************************************************************************************
	//测试数据，调试使用
	/**
	 * 初始化测试数据
	 */
	private void initPopMenuData() {
		feedTypeList = new ArrayList<FeedType>();
			
		FeedType databean1 = new FeedType(0, "最热");
		feedTypeList.add(databean1);
				
		FeedType databean2 = new FeedType(1, "最新");
		feedTypeList.add(databean2);
		
		FeedType databean3 = new FeedType(2, "湘菜");
		feedTypeList.add(databean3);

		FeedType databean4 = new FeedType(3, "粤菜");
		feedTypeList.add(databean4);

		FeedType databean5 = new FeedType(4, "川菜");
		feedTypeList.add(databean5);
				
		FeedType databean6 = new FeedType(5, "酒水");
		feedTypeList.add(databean6);
		
		FeedType databean7 = new FeedType(6, "炖汤");
		feedTypeList.add(databean7);
		
		FeedType databean8 = new FeedType(7, "主食");
		feedTypeList.add(databean8);
		
		lastFeedType = feedTypeList.get(0);	
	}			
		
	/**
	 * 初始化测试数据
	 */
	private void initTestData() {
		goodsListData = new ArrayList<GoodsDataBean>();
			
		GoodsDataBean databean1 = new GoodsDataBean();
		databean1.setId(100001);
		databean1.setName("川菜");
		databean1.setBitmapUrl("http://food.hnr.cn/rmc/rmfl/201307/W020130719651943856865.jpg");
		databean1.setPrice(19.8);
		goodsListData.add(databean1);
				
		GoodsDataBean databean2 = new GoodsDataBean();
		databean2.setId(100002);
		databean2.setName("湘菜");
		databean2.setPrice(38.0);	
		databean2.setBitmapUrl("http://img5.imgtn.bdimg.com/it/u=2131026967,3181874696&fm=21&gp=0.jpg");
		goodsListData.add(databean2);

		GoodsDataBean databean3 = new GoodsDataBean();
		databean3.setId(100003);
		databean3.setName("粤菜");
		databean3.setPrice(26.5);
		databean3.setBitmapUrl("http://pic21.nipic.com/20120525/2194567_150416722000_2.jpg");
		goodsListData.add(databean3);

		GoodsDataBean databean4 = new GoodsDataBean();
		databean4.setId(100004);
		databean4.setName("酒水");
		databean4.setPrice(18.0);
		databean4.setBitmapUrl("http://pic21.nipic.com/20120513/4666865_132407922000_2.jpg");
		goodsListData.add(databean4);
		
		GoodsDataBean databean5 = new GoodsDataBean();
		databean5.setId(100005);
		databean5.setName("小菜");
		databean5.setPrice(28.0);
		databean5.setBitmapUrl("http://p1.ftuan.com/2012/1129/11/20121129112313883.jpg");
		goodsListData.add(databean5);
																			
		GoodsDataBean databean6 = new GoodsDataBean();
		databean6.setId(100006);
		databean6.setName("主食");
		databean6.setPrice(48.0);
		databean6.setBitmapUrl("http://www.photophoto.cn/m77/161/002/1610020750.jpg");
		goodsListData.add(databean6);
																		
		Log.d(TAG, "goodsListData.size =" + goodsListData.size());

		updateUi();	
	}

	
	
}