package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.test.ImagesUrl;
import com.innovaee.eorder.mobile.util.DisplayUtil;
import com.innovaee.eorder.mobile.util.FeedType;
import com.innovaee.eorder.mobile.util.FeedTypeAdapter;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;  				
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
		
@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity {
	private final static String TAB_NAME_STRING_GOODSSHOP = "goodsshop";

	private final static String TAB_NAME_STRING_USERORDER = "userorder";

	private TabHost tabHost;

	private RadioButton radioBtnGoodsShop, radioBtnUserOrder;
	
	private ActionBar actionBar;

	private View notificationActionView;
	 
	private TextView notificationCountView;
	 
	private View chatActionView;
	
	private TextView chatCountView;
	 
	private MenuItem moreItem;
	 
	private MenuItem settingsItem;
				
	private FeedTypeAdapter feedTypeAdapter; 
	
	private PopupWindow feedTypePopup;
					
	private DisplayMetrics displayMetrics;
	
	private List<FeedType> feedTypeList;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintab_activity);		
		
		displayMetrics = new DisplayMetrics(); 
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
			
		initView();
			
		initData();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu paramMenu)
	{	
		getMenuInflater().inflate(R.menu.main_activity_actions, paramMenu);
	    final MenuItem localMenuItem1 = paramMenu.findItem(R.id.action_notification);	
	    this.notificationActionView = localMenuItem1.getActionView();
	    this.notificationCountView = ((TextView)this.notificationActionView.findViewById(R.id.notification_count));	
	    //updateUreadNotificationCount(this.notificationCountView, "unread_notification_count");
	    this.notificationActionView.setOnClickListener(new View.OnClickListener()
	    {
	      public void onClick(View paramAnonymousView)
	      {	     
	      }
	    });
	    this.notificationActionView.setOnLongClickListener(new View.OnLongClickListener()
	    {
	      public boolean onLongClick(View paramAnonymousView)
	      {
	        return true;
	      }
	    });
	    					
	    final MenuItem localMenuItem2 = paramMenu.findItem(R.id.action_chat);
	    this.chatActionView = localMenuItem2.getActionView();
	    this.chatCountView = ((TextView)this.chatActionView.findViewById(R.id.notification_count));
	    //updateUreadNotificationCount(this.chatCountView, "unread_chat_message_count");
	    this.chatActionView.setOnClickListener(new View.OnClickListener()
	    {
	      public void onClick(View paramAnonymousView)
	      {
	      }
	    });
	    this.chatActionView.setOnLongClickListener(new View.OnLongClickListener()
	    {
	      public boolean onLongClick(View paramAnonymousView)
	      {
	        return true;
	      }
	    });
	    
	    this.moreItem = paramMenu.findItem(R.id.action_more);
	    this.settingsItem = paramMenu.findItem(R.id.action_settings);
	    return super.onCreateOptionsMenu(paramMenu);
	  }
				
			
	@Override	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.item_setting:
	            openSetting();
	            return true;
	            						
	        case R.id.item_about:
	            openAbout();
	            return true;
	            			
	        case R.id.item_refresh:
	        	return true;
	        					
	        case R.id.item_myorderhestory:
	        	oepnMyOrderHestory();
	        	return true;
	        		
	        default:	
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		actionBar = getActionBar();
			
		tabHost = getTabHost();
			
		radioBtnGoodsShop = (RadioButton) findViewById(R.id.tab_goodsshop);
		radioBtnUserOrder = (RadioButton) findViewById(R.id.tab_userorder);

		radioBtnGoodsShop.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_GOODSSHOP);
				radioBtnUserOrder.setChecked(false);
				radioBtnGoodsShop.setChecked(true);
			}
		});

		radioBtnUserOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_USERORDER);
				radioBtnGoodsShop.setChecked(false);
				radioBtnUserOrder.setChecked(true);
			}		
		});
	}

	/**
	 * 初始化tab
	 */
	private void initData() {
		initPopMenuData();
		feedTypeAdapter = new FeedTypeAdapter(MainTabActivity.this, feedTypeList);
		//actionBar.setDisplayShowTitleEnabled(true);				
		
		//actionBar.setTitle(R.string.action_title);	
			
		//actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient_bg));
				
		//actionBar.setDisplayUseLogoEnabled(true);
		LayoutInflater inflater;			
	    ActionBar localActionBar = getActionBar();	
	    //localActionBar.setHomeButtonEnabled(true);		
	    inflater = (LayoutInflater) MainTabActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View localView = inflater.inflate(R.layout.feed_type_view, null);		
	    TextView feedTypeName = ((TextView)localView.findViewById(R.id.feed_type_name));
	    feedTypeName.setText(R.string.feed_type_hot);	
	    localActionBar.setCustomView(localView);
	    localActionBar.setDisplayShowCustomEnabled(true);	
	    		
	    localView.setOnClickListener(new View.OnClickListener()
	    {
	      public void onClick(View paramAnonymousView)
	      {	  
	    	  clickOnFeedType((View)actionBar.getCustomView());
	      }							
	    });
	    							
		
		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_GOODSSHOP)
				.setIndicator(TAB_NAME_STRING_GOODSSHOP)
				.setContent(new Intent(this, ClassifyActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_USERORDER)
				.setIndicator(TAB_NAME_STRING_USERORDER)
				.setContent(new Intent(this, MyOrderActivity.class)));
	}
		
	/**
	 * 进去设置界面
	 */
	private void openSetting() {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();									
		intent.putExtras(bundle);						
		intent.setClass(MainTabActivity.this, SettingsActivity.class);	             						
        startActivity(intent);			
	}		
					
	/**
	 * 进入关于界面
	 */	
	private void openAbout() {
		
	}
					
	/**
	 * 进入我的订单记录
	 */
	private void oepnMyOrderHestory() {
		Bundle bundle = new Bundle();					
		Intent intent = new Intent();									
		intent.putExtras(bundle);						
		intent.setClass(MainTabActivity.this, OrderHestoryActivity.class);	             						
        startActivity(intent);	
	}
			

	private void changeFeedType(FeedType paramFeedType)
	{
		/*
	    int i;
	    if (this.lastFeedType != paramFeedType)
	    {
	      i = 1;
	      this.feedTypeName.setText(paramFeedType.titleResId());
	      if ((!paramFeedType.needRefresh()) && (!this.hasNewFeeds) && ((!this.feedTypeAdapter.isHasNewSecretOfFriend()) || (paramFeedType == FeedType.HOT)))
	        break label74;
	      paramFeedType.setNeedRefresh(false);
	      this.loadSecretsTask.execute(paramFeedType, LoadMode.REFRESH);
	    }
	    label74: 
	    while (i == 0)
	    {
	      return;
	      i = 0;
	      break;
	    }
	    this.loadSecretsTask.execute(paramFeedType, LoadMode.RELOAD);
	    */
	  }	
	
	private void initFeedTypePopup()
	{
		LayoutInflater inflater;	
		inflater = (LayoutInflater) MainTabActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
	    ListView localListView = (ListView)inflater.inflate(R.layout.drop_down_list, null);	
	    //Utils.disableOverScroll(localListView);	
	    localListView.setAdapter(this.feedTypeAdapter);	
	    
	    /*
	    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	    {
	      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
	      {
	        MainActivity.this.changeFeedType(FeedType.values()[paramAnonymousInt]);
	        new Handler().postDelayed(new Runnable()
	        {
	          public void run()
	          {
	            MainActivity.this.feedTypePopup.dismiss();
	          }
	        }
	        , 50L);
	      }	
	    });
	    */
	    	
	    this.feedTypePopup = new PopupWindow(localListView, this.displayMetrics.widthPixels / 3, -2, true);
	    this.feedTypePopup.setBackgroundDrawable(new BitmapDrawable());
	    this.feedTypePopup.setOutsideTouchable(true);
	  }
								
	private void initListViewListener()
	{
		/*
	    this.listView.setOnRefreshListener(new XListView.OnRefreshListener()
	    {
	      public void onRefresh()
	      {
	        MainActivity.this.loadSecretsTask.execute(MainActivity.this.lastFeedType, LoadMode.REFRESH);
	      }
	    });
	    this.listView.setOnLoadMoreListener(new XListView.OnLoadMoreListener()
	    {
	      public void onLoadMore()
	      {
	        MainActivity.this.loadSecretsTask.execute(MainActivity.this.lastFeedType, LoadMode.LOADMORE);
	      }
	    });
	    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	    {
	      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
	      {
	        Object localObject = paramAnonymousView.getTag(2131230723);
	        if ((localObject == null) || (!(localObject instanceof MobileSecret)))
	          return;
	        ParcelableSecret localParcelableSecret = new ParcelableSecret((MobileSecret)localObject);
	        if (localParcelableSecret.isAutoRemove())
	          localParcelableSecret.setRemainTimeInMs(Long.valueOf(localParcelableSecret.getRemainTimeInMs().longValue() - MainActivity.this.adapter.getIntervalToSecretRemainTime()));
	        SecretActivity.startActivityForResult(MainActivity.this, localParcelableSecret, false);
	      }
	    });
	    if (!isAllGuidancesShown())
	    {
	      this.listView.setOnScrollListener(new AbsListView.OnScrollListener()
	      {
	        private int lastFirstVisibleItem;

	        public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
	        {
	          if (MainActivity.this.isBackToTopGuidanceShown());
	          do
	          {
	            return;
	            if ((this.lastFirstVisibleItem - paramAnonymousInt1 > 2) && (this.lastFirstVisibleItem > 1))
	            {
	              MainActivity.this.showBackToTopGuidance();
	              this.lastFirstVisibleItem = paramAnonymousInt1;
	            }
	          }
	          while (paramAnonymousInt1 <= this.lastFirstVisibleItem);
	          this.lastFirstVisibleItem = paramAnonymousInt1;
	        }

	        public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
	        {
	          if ((paramAnonymousInt != 0) || (MainActivity.this.adapter.isAllCardGuidancesShown()))
	            return;
	          MainActivity.this.displayMaskGuidance();
	        }
	      });
	      Set localSet = (Set)this.prefHelper.get(new TypeReference()
	      {
	      }
	      , "my_secret_ids", new HashSet());
	      this.adapter.setMySecretIds(localSet);
	    }
	    */
			
	  }
	
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
	 * 初始化测试数据
	 */
	private void initPopMenuData() {
		feedTypeList = new ArrayList<FeedType>();
			
		FeedType databean1 = new FeedType(0, "热门");
		feedTypeList.add(databean1);
		
		FeedType databean2 = new FeedType(1, "川菜");
		feedTypeList.add(databean2);
		
		FeedType databean3 = new FeedType(2, "湘菜");
		feedTypeList.add(databean3);

		FeedType databean4 = new FeedType(3, "粤菜");
		feedTypeList.add(databean4);

		FeedType databean5 = new FeedType(4, "小吃");
		feedTypeList.add(databean5);			
	}
		
	
	
}