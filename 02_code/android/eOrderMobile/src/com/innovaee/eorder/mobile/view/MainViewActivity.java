package com.innovaee.eorder.mobile.view;
	
import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.DisplayUtil;
import com.innovaee.eorder.mobile.util.FeedType;
import com.innovaee.eorder.mobile.util.FeedTypeAdapter;
	
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.ProgressBar;
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
	public final static int MSG_DELALL = 20005;
	public final static int MSG_UPDATE_COUNT = 20006;
					
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
	private List<CategoryDataBean> categoryListData;		
		
	//已经选择的菜品
	private List<GoodsDataBean> selectOrderGoods;	
	
	//历史记录查询订单菜品，仅供测试
	private List<GoodsDataBean> orderHestoryGoods;
	
	//订单记录查询会员号输入编辑器
	EditText orderHestoryInput;
	
	//广播接收器
	BroadcastReceiver receiver;
	
	//load数据progressBar	
	ProgressBar progressBar;
	
	private Handler handler = new Handler(Looper.getMainLooper()) {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE: {
				Log.d(TAG, "MSG_UPDATE!!!");
				progressBar.setVisibility(View.GONE);
				gridView.setVisibility(View.VISIBLE);			
				goodsListData = (List<GoodsDataBean>) msg.obj;
				goodsAdapter = new GoodsAdapter(MainViewActivity.this, goodsListData, handler);
				gridView.setAdapter(goodsAdapter);
					
				Log.d(TAG, "goodsListData.size =" + goodsListData.size());
				getOrderHestoryTestData();
				}	
				break;
					
			case MSG_INITDATA:	
				Log.d(TAG, "MSG_INITDATA!!!");
				break;
				
			case MSG_UPDATE_POPMENU:
				Log.d(TAG, "MSG_UPDATE_POPMENU!!!");			
				feedTypeList = changeCategoryToFeedType(categoryListData);				
				feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this, feedTypeList);
				popupMenuList.setAdapter(feedTypeAdapter);
				changeFeedType(feedTypeList.get(0));				
				break;																		
					
			case MSG_ORDER:
				Log.d(TAG, "MSG_ORDER!!!");
				int select = msg.arg1;		
				if (selectOrderGoods == null) {
					selectOrderGoods = new ArrayList<GoodsDataBean>();
				}	
						
				if ((goodsListData != null) && (goodsListData.size() != 0)) {
					addGoodsToSelect(goodsListData.get(select));
					int count = getMyOrderSelectCount();
					updateMyOrderCount(count);
				}							
				break;		
					
			case MSG_DELALL:
				if(selectOrderGoods != null) {
					selectOrderGoods.clear();
					selectOrderGoods = null;
				}	
				break;
				
			case MSG_UPDATE_COUNT:
				Log.d(TAG, "MSG_UPDATE_COUNT!!!");	
				selectOrderGoods = (List<GoodsDataBean>) msg.obj;
				int count = getMyOrderSelectCount();
				updateMyOrderCount(count);							
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
	            	
	        case R.id.action_menu_refresh:
	        	refreshActivity();
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
			
		progressBar = (ProgressBar)findViewById(R.id.loading_data_progressbar);
			
		receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				Log.d(TAG, "BroadcastReceiver:onReceive()");	
				if (intent.getAction().equals("com.eorder.action.delAll")) {
					Log.d(TAG, "BroadcastReceiver:com.eorder.action.delAll");
					if (selectOrderGoods != null) {
						selectOrderGoods.clear();
						selectOrderGoods = null;	
								
						updateMyOrderCount(0);
					}	
				} else if (intent.getAction().equals("com.eorder.action.changeCount")) {
					Log.d(TAG, "BroadcastReceiver:com.eorder.action.changeCount");
					Bundle bundle = intent.getExtras();
					
					ArrayList list = bundle.getParcelableArrayList("list");
					selectOrderGoods = (List<GoodsDataBean>) list.get(0);	
																
					int count = getMyOrderSelectCount();
					updateMyOrderCount(count);
				}											
			}
		};
																
		IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.eorder.action.delAll");
        intentFilter.addAction("com.eorder.action.changeCount");
        registerReceiver(receiver, intentFilter);        
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
					addGoodsToSelect(goodsListData.get(position));	
					int count = getMyOrderSelectCount();
					updateMyOrderCount(count);
					Log.d(TAG, "count=" + count);
				}						
			}				
		});				
					
		initFeedTypePopup();
			
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
		feedTypeName.setText(R.string.feed_type_all);
					
		actionBar.setCustomView(localView);
		actionBar.setDisplayShowCustomEnabled(true);	
			
		orderHestroySearch.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	 	 
				Log.d(TAG, "orderHestroySearch.setOnClickListener!");
				String inputString = orderHestoryInput.getText().toString();
						
				if(inputString != null && !inputString.equals("")) {
					oepnOrderHestory(inputString);
				} else {	
					Toast.makeText(getApplicationContext(), R.string.toast_please_input_userid, Toast.LENGTH_SHORT).show();
				}		
			}														
		});
							
		feedTypeName.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View paramAnonymousView)
			{	  
				Log.d(TAG, "feedTypeName.setOnClickListener!");
				clickOnFeedType(paramAnonymousView);		
			}												
		});
	}	

	@Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    };    	
	
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
	private void oepnOrderHestory(String userId) {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();
		
		bundle.putString("userid", userId);			
			
		ArrayList list = new ArrayList();
		list.add(orderHestoryGoods);	
		bundle.putParcelableArrayList("list",list);
			
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
	    if (this.lastFeedType != paramFeedType)
	    {
	    	lastFeedType = paramFeedType;
	    	this.feedTypeName.setText(lastFeedType.getTypeName());
	    				
	    	loadCategoryData(lastFeedType.getCategoryId());
	    }				    		
	}												
		
	/**
	 * 初始化Popmenu菜单
	 */
	private void initFeedTypePopup()
	{					
		//初始化PipMenu测试数据
		feedTypeList = new ArrayList<FeedType>();
		feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this, feedTypeList);
																
			
		LayoutInflater inflater;	
		inflater = (LayoutInflater) MainViewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		popupMenuList = (ListView)inflater.inflate(R.layout.drop_down_list, null);		
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
				oepnOrderHestory(inputString);
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
	    	return;
	    }								
	    			
		orderCountView.setText(str);	
		orderCountView.setVisibility(View.VISIBLE);
	}																						
				
	/**
	 * 得到当前下单菜品的总数量
	 * @return
	 */
	private int getMyOrderSelectCount() {
		int count = 0;
		
		if(selectOrderGoods == null) {
			return count;
		}		
		
		for (GoodsDataBean dataBean: selectOrderGoods) {
			count = count + dataBean.getCount();
		}	
		
		return count;
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
		msg.obj = (Object) categoryListData;
		handler.sendMessage(msg);
	}	
						
	/**	
	 * 加载某个分类菜品列表数据
	 */								
	private void loadCategoryData(final int id) {
		Log.d(TAG, "loadCategoryData()");			
		new Thread(){
			@Override
			public void run(){
				DataManager.getInstance(MainViewActivity.this).getGoodsData(id, 
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
						public void onRequestSuccess(GoodsDataBean data) {
							// TODO Auto-generated method stub
							Log.d("MainViewActivity:", "onRequestSuccess!");
							updateUi();
						}
				});
						
				handler.sendEmptyMessage(0);
			}
		}.start();						
	}	
		
	/**
	 * 加载所有分类列表信息
	 */	
	private void loadFeedTypeListData() {		
		Log.d(TAG, "loadFeedTypeListData()");
		progressBar.setVisibility(View.VISIBLE);
					
		new Thread() {
			@Override
			public void run(){
				DataManager.getInstance(MainViewActivity.this).getCategoryData(
						new IDataRequestListener<CategoryDataBean>() {
							@Override
							public void onRequestSuccess(
									final List<CategoryDataBean> data) {
								// TODO Auto-generated method stub
								Log.d("MainViewActivity:", "onRequestSuccess!");
								if (data == null) {
									return;
								}
									
								Log.d(TAG, "data.szie=" + data.size());	
								categoryListData = data;	
								updatePopmenuUi();
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
							public void onRequestSuccess(CategoryDataBean data) {
								// TODO Auto-generated method stub
								Log.d("MainViewActivity:", "onRequestSuccess!");
							}		
						});
				
				handler.sendEmptyMessage(0);
			}
		}.start();	
	}
	
	/**	
	 * 转换分类数据为切换分类所需的数据类型
	 * @param categoryListData
	 * @return
	 */
	private List<FeedType> changeCategoryToFeedType(List<CategoryDataBean> categoryListData) {
		List<FeedType> feedTypeList = new ArrayList<FeedType>();
						
		for(CategoryDataBean databean: categoryListData) {
			FeedType feedType = new FeedType(databean.getId(), databean.getName());
			feedTypeList.add(feedType);	
			Log.d(TAG, "id=" + databean.getId());
			Log.d(TAG, "id=" + databean.getName());
		}	
					
		Log.d(TAG, "feedTypeList.size()=" + feedTypeList.size());
		return feedTypeList;
	}
			
	/**
	 * 添加菜品到选择列表
	 * 如果该菜品已经存在则改变数量，如果没有则添加菜品到list中
	 * @param dataBean
	 */
	private void addGoodsToSelect(GoodsDataBean dataBean) {
		boolean isAddCount = false;
		Log.d(TAG, "selectOrderGoods.size()=" + selectOrderGoods.size());
			
		for (GoodsDataBean goodsDataBean: selectOrderGoods) {
			if (goodsDataBean.getId() == dataBean.getId()) {
				int count = goodsDataBean.getCount() + 1;
				goodsDataBean.setCount(count); 
				isAddCount = true;
				Log.d(TAG, "count=" + count);
			}	
		}
		
		if(!isAddCount) {
			Log.d(TAG, "add(dataBean)");
			//第一次添加到selectList需要设置该对象count=1
			dataBean.setCount(1);									
			selectOrderGoods.add(dataBean);
		}								
	}
	
	/**
	 * 重新刷新界面
	 */		
	private void refreshActivity() {
		loadFeedTypeListData();
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
		
	/**
	 * 生成订单历史记录的测试数据
	 */
	private void getOrderHestoryTestData() {
		orderHestoryGoods = new ArrayList<GoodsDataBean>();
		
		if(goodsListData != null) {
			for(GoodsDataBean databean : goodsListData) {
				orderHestoryGoods.add(databean);
				
				if(orderHestoryGoods.size() >= 4)
					return;
			}	
		}
	}	
	
}