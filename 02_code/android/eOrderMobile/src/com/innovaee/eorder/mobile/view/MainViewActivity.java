/***********************************************
 * Filename    : MainViewActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.controller.DataManager;
import com.innovaee.eorder.mobile.controller.DataManager.IDataRequestListener;
import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.DisplayUtil;
import com.innovaee.eorder.mobile.util.FeedType;
import com.innovaee.eorder.mobile.util.FeedTypeAdapter;

/**
 * 主程序入口展示界面 展示菜品某分类下所有菜品
 *
 */
public class MainViewActivity extends Activity {
    private static final String TAG = "MainViewActivity";

    // 消息定义，界面刷新消息
    public static final int MSG_UPDATE = 20001;

    // 初始化数据完成消息
    public static final int MSG_INITDATA = 20002;

    // 更新popmenu消息
    public static final int MSG_UPDATE_POPMENU = 20003;

    // 下单消息
    public static final int MSG_ORDER = 20004;

    // 删除所有的消息
    public static final int MSG_DELALL = 20005;

    // 更新下单的数目的消息
    public static final int MSG_UPDATE_COUNT = 20006;

    // 提示失败的消息
    public static final int MSG_UPDATE_FAILUI = 20007;
    
    // 增加菜品消息
    public static final int MSG_ADD_TO_ORDER = 20008;	
    
    // 自定义ActionBar
    private ActionBar actionBar;

    // 订单数量显示view
    private View orderActionView;

    // 订单数量显示view里数量TextView
    private TextView orderCountView;

    // 弹出菜单Adapter
    private FeedTypeAdapter feedTypeAdapter;

    // 分类选择弹出菜单
    private PopupWindow feedTypePopup;

    // ActionBar上显示的当前查看分类
    private TextView feedTypeName;

    // 最近查看的分类类型
    private FeedType lastFeedType;

    // 所有分类类型列表
    private List<FeedType> feedTypeList;

    // 分类弹出菜单中listview
    private ListView popupMenuList;

    // 显示工具类
    private DisplayMetrics displayMetrics;

    // 显示失败刷新文字和按钮
    private RelativeLayout failLayout;

    // 失败刷新按钮
    private Button refreshBtn;

    // 菜品显示GridView
    private GridView gridView;

    // 菜品显示数据绑定器
    private GoodsAdapter goodsAdapter;

    // 当前分类的菜品列表
    private List<GoodsDataBean> goodsListData;

    // 所有分类列表
    private List<CategoryDataBean> categoryListData;

    // 已经选择的菜品
    private List<GoodsDataBean> selectOrderGoods;

    // 历史记录查询订单菜品，仅供测试
    //private List<GoodsDataBean> orderHestoryGoods;

    // 订单记录查询会员号输入编辑器
    EditText orderHestoryInput;

    // 广播接收器
    BroadcastReceiver receiver;

    // load数据progressBar
    ProgressBar progressBar;

    // 消息Handler
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressWarnings("unchecked")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            // 刷新整个UI显示
            case MSG_UPDATE: {
                progressBar.setVisibility(View.GONE);
                failLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                goodsListData = (List<GoodsDataBean>) msg.obj;
                goodsAdapter = new GoodsAdapter(MainViewActivity.this,
                        goodsListData, handler);
                gridView.setAdapter(goodsAdapter);
                				
                //getOrderHestoryTestData();
            }
                break;

            // 初始化数据成功
            case MSG_INITDATA:
                break;

            // 重新设置PopMenu
            case MSG_UPDATE_POPMENU:
                progressBar.setVisibility(View.GONE);
                failLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                feedTypeList = changeCategoryToFeedType(categoryListData);
                feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this,
                        feedTypeList);
                popupMenuList.setAdapter(feedTypeAdapter);
                changeFeedType(feedTypeList.get(0));
                break;

            // 点击某项菜品，添加到选中列表
            case MSG_ORDER:
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

            // 删除所有选中菜品
            case MSG_DELALL:
                if (selectOrderGoods != null) {
                    selectOrderGoods.clear();
                    selectOrderGoods = null;
                }
                break;

            // 更新菜品显示数量
            case MSG_UPDATE_COUNT:
                selectOrderGoods = (List<GoodsDataBean>) msg.obj;
                int count = getMyOrderSelectCount();
                updateMyOrderCount(count);
                break;

            // 显示失败UI提示
            case MSG_UPDATE_FAILUI:
                progressBar.setVisibility(View.GONE);
                failLayout.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                break;

            default:
                break;
            }
        }
    };

    /**
     * onCreate方法，系统默认调用
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintab_activity);

        initView();

        initData();
    }

    /**
     * onCreateOptionsMenu方法，系统默认调用 设置ActionBar上要显示的View
     */
    @Override
    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, paramMenu);

        // 订单数量显示View
        final MenuItem localMenuItem2 = paramMenu.findItem(R.id.action_order);
        orderActionView = localMenuItem2.getActionView();
        orderCountView = ((TextView) this.orderActionView
                .findViewById(R.id.order_count));
        orderActionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if ((selectOrderGoods != null) && (selectOrderGoods.size() > 0)) {
                    openMyOrder();
                } else {
                    Toast.makeText(MainViewActivity.this,
                            R.string.toast_please_order_first,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderActionView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View paramAnonymousView) {
                if ((selectOrderGoods != null) && (selectOrderGoods.size() > 0)) {
                    openMyOrder();
                } else {
                    Toast.makeText(MainViewActivity.this,
                            R.string.toast_please_order_first,
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(paramMenu);
    }

    /**
     * onOptionsItemSelected方法，系统默认调用 弹出更多菜单点击处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        failLayout = (RelativeLayout) findViewById(R.id.fail_layout);

        refreshBtn = (Button) findViewById(R.id.refresh_btn);

        progressBar = (ProgressBar) findViewById(R.id.loading_data_progressbar);

        receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("com.eorder.action.delAll")) {
                    if (selectOrderGoods != null) {
                        selectOrderGoods.clear();
                        selectOrderGoods = null;

                        updateMyOrderCount(0);
                    }
                } else if (intent.getAction().equals(
                        "com.eorder.action.changeCount")) {
                    Bundle bundle = intent.getExtras();

                    ArrayList list = bundle.getParcelableArrayList("list");
                    selectOrderGoods = (List<GoodsDataBean>) list.get(0);

                    int count = getMyOrderSelectCount();
                    updateMyOrderCount(count);
                } else if (intent.getAction().equals(
                        "com.eorder.action.addToOrder")) {
                	Log.d(TAG, "com.eorder.action.addToOrder!");
                		
                	Bundle bundle = intent.getExtras();
                														
                	ArrayList list = bundle.getParcelableArrayList("list");
                	List<GoodsDataBean> addToOrderGoods = (List<GoodsDataBean>) list.get(0);
                	GoodsDataBean addGoodsData = addToOrderGoods.get(0);
                	addGoodsData.setCount(1);
                		
                	boolean isInclude = ifContainsIt(addGoodsData);
                								
                	if(isInclude) {									
                		Log.d(TAG, "isInclude == true");
                		for(GoodsDataBean data: goodsListData) {
                    		if(data.getId() == addGoodsData.getId()) {
                    			int count = data.getCount();
                    			data.setCount(count + 1);
                    			break;
                    		}				
                    	}	
                								
                        int count = getMyOrderSelectCount();
                        updateMyOrderCount(count);			
                	} else {															
                		Log.d(TAG, "isInclude == false");
                		selectOrderGoods.add(addGoodsData);	
                		int count = getMyOrderSelectCount();
                        updateMyOrderCount(count);
                	}										
                }	
            }
        };

        // 初始化广播事件
        IntentFilter intentFilter = new IntentFilter();

        // 删除所有的广播事件
        intentFilter.addAction("com.eorder.action.delAll");

        // 改变当前显示的数量事件
        intentFilter.addAction("com.eorder.action.changeCount");
        
        // 增加菜品到当前订单事件
        intentFilter.addAction("com.eorder.action.addToOrder");        	

        // 注册相应广播事件
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 初始化Data
     */
    private void initData() {
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);

        initActionBarCustomView();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                refreshActivity();
            }
        });

        goodsAdapter = new GoodsAdapter(this, goodsListData, handler);

        gridView.setAdapter(goodsAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                if (selectOrderGoods == null) {
                    selectOrderGoods = new ArrayList<GoodsDataBean>();
                }

                if ((goodsListData != null) && (goodsListData.size() != 0)) {
                    addGoodsToSelect(goodsListData.get(position));
                    int count = getMyOrderSelectCount();
                    updateMyOrderCount(count);
                }
            }
        });

        // 初始化Popmenu菜单
        initFeedTypePopup();

        // 显示ProgressBar,然后开始从网络加载数据
        progressBar.setVisibility(View.VISIBLE);

        // 加载所有分类数据
        loadFeedTypeListData();
    }

    /**
     * 初始化ActionBar自定义View
     */
    private void initActionBarCustomView() {
        LayoutInflater inflater;
        inflater = (LayoutInflater) MainViewActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View localView = inflater.inflate(R.layout.order_hestory_view, null);
        orderHestoryInput = ((EditText) localView
                .findViewById(R.id.order_hestory_input));
        ImageView orderHestroySearch = ((ImageView) localView
                .findViewById(R.id.order_hestory_search));

        feedTypeName = ((TextView) localView.findViewById(R.id.feed_type_name));
        feedTypeName.setText(R.string.feed_type_all);

        actionBar.setCustomView(localView);
        actionBar.setDisplayShowCustomEnabled(true);

        orderHestroySearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String inputString = orderHestoryInput.getText().toString();

                if (inputString != null && !inputString.equals("")) {
                    orderHestoryInput.setText("");
                    oepnOrderHestory(inputString);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.toast_please_input_userid,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        feedTypeName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                clickOnFeedType(paramAnonymousView);
            }
        });
    }

    /**
     * onDestroy方法，系统默认调用
     */
    @Override
    protected void onDestroy() {

        // 必须在onDestroy中卸载监听器
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
     * 
     * @param 会员id
     */
    private void oepnOrderHestory(String userId) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();

        bundle.putString("userid", userId);

        // 传递历史订单记录数据
        //ArrayList list = new ArrayList();
        //list.add(orderHestoryGoods);
        //bundle.putParcelableArrayList("list", list);
        
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

        // 这个list用于在budnle中传递 需要传递的ArrayList<Object>
        ArrayList list = new ArrayList();

        list.add(selectOrderGoods);

        // 传递已经选中的菜品列表
        bundle.putParcelableArrayList("list", list);

        intent.putExtras(bundle);
        intent.setClass(MainViewActivity.this, MyOrderActivity.class);
        startActivity(intent);
    }

    /**
     * 切换当前的FeedType，并更新显示
     * 
     * @param paramFeedType
     *            需要切换的菜品分类类型数据
     */
    private void changeFeedType(FeedType paramFeedType) {
        // 当前需要改变的类型和上次不相同
        if (this.lastFeedType != paramFeedType) {
            lastFeedType = paramFeedType;

            // 为了避免超长，截取前面三个字符显示
            if (lastFeedType.getTypeName().length() > 3) {
                this.feedTypeName.setText(lastFeedType.getTypeName().substring(
                        0, 3));
            } else {
                this.feedTypeName.setText(lastFeedType.getTypeName());
            }

            // 隐藏ProgressBar,显示GridView
            progressBar.setVisibility(View.VISIBLE);
            failLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);

            // 重新加载改变type的菜品类型数据
            loadCategoryData(lastFeedType.getCategoryId());
        }
    }

    /**
     * 初始化Popmenu菜单
     */
    private void initFeedTypePopup() {
        // 初始化PipMenu测试数据
        feedTypeList = new ArrayList<FeedType>();
        feedTypeAdapter = new FeedTypeAdapter(MainViewActivity.this,
                feedTypeList);

        LayoutInflater inflater;
        inflater = (LayoutInflater) MainViewActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        popupMenuList = (ListView) inflater.inflate(R.layout.drop_down_list,
                null);
        popupMenuList.setAdapter(this.feedTypeAdapter);

        popupMenuList
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(
                            AdapterView<?> paramAnonymousAdapterView,
                            View paramAnonymousView, int paramAnonymousInt,
                            long paramAnonymousLong) {
                        MainViewActivity.this.changeFeedType(feedTypeList
                                .get(paramAnonymousInt));
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                MainViewActivity.this.feedTypePopup.dismiss();
                            }
                        }, 50L);
                    }
                });

        this.feedTypePopup = new PopupWindow(popupMenuList,
                this.displayMetrics.widthPixels / 3, -2, true);
        this.feedTypePopup.setBackgroundDrawable(new BitmapDrawable());
        this.feedTypePopup.setOutsideTouchable(true);
    }

    /**
     * 处理Actionbar中FeedType自定义View点击事件
     * 
     * @param paramView
     *            点击view区域
     */
    public void clickOnFeedType(View paramView) {
        if (this.feedTypePopup == null) {
            initFeedTypePopup();
        }

        if (this.feedTypePopup.isShowing()) {
            this.feedTypePopup.dismiss();
            return;
        }

        this.feedTypePopup.showAsDropDown(paramView,
                -DisplayUtil.dipToPixels(this.displayMetrics, 10.0F),
                -DisplayUtil.dipToPixels(this.displayMetrics, 5.0F));
    }

    /**
     * 处理ActionBar中orderHestory自定义View点击事件
     * 
     * @param paramView
     *            点击view区域
     */
    public void clickOnOrderHestory(View paramView) {
        if (orderHestoryInput != null) {
            String inputString = orderHestoryInput.getText().toString();

            if (inputString.equals("")) {
                Toast.makeText(getApplicationContext(),
                        R.string.toast_please_input_userid, Toast.LENGTH_SHORT)
                        .show();
            } else {
                oepnOrderHestory(inputString);
            }
        }
    }

    /**
     * 更新我的订单数目
     * 
     * @param count
     *            需要更新的数目
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
     * 
     * @return 当前的总数量
     */
    private int getMyOrderSelectCount() {
        int count = 0;

        if (selectOrderGoods == null) {
            return count;
        }

        for (GoodsDataBean dataBean : selectOrderGoods) {
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
     * 更新fail ui
     */
    private void updateFailUi() {
        Message msg = Message.obtain();
        msg.what = MSG_UPDATE_FAILUI;
        handler.sendMessage(msg);
    }

    /**
     * 加载某个分类菜品列表数据
     * 
     * @param 菜品分类id
     */
    private void loadCategoryData(final int id) {
        // 必须放在子线程下执行，放在UI线程中执行会导致不响应
        new Thread() {
            @Override
            public void run() {
                DataManager.getInstance(MainViewActivity.this).getGoodsData(id,
                        new IDataRequestListener<GoodsDataBean>() {

                            // 请求成功后的回调处理函数，返回多个情况
                            @Override
                            public void onRequestSuccess(
                                    final List<GoodsDataBean> data) {
                                if (data == null) {
                                    return;
                                }
                                goodsListData = data;
                                updateUi();
                            }

                            // 请求开始的回调处理函数
                            @Override
                            public void onRequestStart() {
                            }

                            // 请求失败的回调处理函数
                            @Override
                            public void onRequestFailed(String error) {
                            }

                            // 请求成功的回调处理函数，返回单个情况
                            @Override
                            public void onRequestSuccess(GoodsDataBean data) {
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
        // 必须放在子线程下执行，放在UI线程中执行会导致不响应
        new Thread() {
            @Override
            public void run() {
                DataManager.getInstance(MainViewActivity.this).getCategoryData(
                        new IDataRequestListener<CategoryDataBean>() {
                            // 请求成功后的回调处理函数，返回多个情况
                            @Override
                            public void onRequestSuccess(
                                    final List<CategoryDataBean> data) {
                                if (data == null) {
                                    return;
                                }

                                categoryListData = data;
                                updatePopmenuUi();
                            }

                            // 请求开始的回调处理函数
                            @Override
                            public void onRequestStart() {
                            }

                            // 请求失败的回调处理函数
                            @Override
                            public void onRequestFailed(String error) {
                                updateFailUi();
                            }

                            // 请求成功的回调处理函数，返回单个情况
                            @Override
                            public void onRequestSuccess(CategoryDataBean data) {
                            }
                        });

                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 转换分类数据为切换分类所需的数据类型
     * 
     * @param categoryListData
     *            需要转换的原始分类数据
     * @return 转换后的菜品分类数据
     */
    private List<FeedType> changeCategoryToFeedType(
            List<CategoryDataBean> categoryListData) {
        List<FeedType> feedTypeList = new ArrayList<FeedType>();

        for (CategoryDataBean databean : categoryListData) {
            FeedType feedType = new FeedType(databean.getId(),
                    databean.getName());
            feedTypeList.add(feedType);
        }

        return feedTypeList;
    }

    /**
     * 添加菜品到选择列表 如果该菜品已经存在则改变数量，如果没有则添加菜品到list中
     * 
     * @param dataBean
     *            当前选中的菜品数据Bean
     */
    private void addGoodsToSelect(GoodsDataBean dataBean) {
        boolean isAddCount = false;

        if(selectOrderGoods != null) {
	        for (GoodsDataBean goodsDataBean : selectOrderGoods) {
	            if (goodsDataBean.getId() == dataBean.getId()) {
	                int count = goodsDataBean.getCount() + 1;
	                goodsDataBean.setCount(count);
	                isAddCount = true;
	                break;
	            }
	        }
        } else {
        	selectOrderGoods = new ArrayList<GoodsDataBean>();
        }			
        				
        if (!isAddCount) {
            // 第一次添加到selectList需要设置该对象count=1
            dataBean.setCount(1);
            selectOrderGoods.add(dataBean);
        }
    }
    	
    /**
     * 重新刷新界面
     */
    private void refreshActivity() {
        progressBar.setVisibility(View.VISIBLE);
        failLayout.setVisibility(View.GONE);
        gridView.setVisibility(View.GONE);

        // 如果分类为null或者size为0说明分类加载也失败
        if (feedTypeList == null || feedTypeList.size() == 0) {
            loadFeedTypeListData();
        } else {
            loadCategoryData(lastFeedType.getCategoryId());
        }
    }

    /**
     * 生成订单历史记录的测试数据
     */
    /*
    private void getOrderHestoryTestData() {
        orderHestoryGoods = new ArrayList<GoodsDataBean>();

        if (goodsListData != null) {
            for (GoodsDataBean databean : goodsListData) {
                orderHestoryGoods.add(databean);

                if (orderHestoryGoods.size() >= 4) {
                    return;
                }
            }
        }
    }
    */
    	
    /**	
     * 判断该list是否包含该菜品
     * 因为count可能不相等，所以用该函数判断是否包含
     * @return 返回是否包含该菜品
     */	
    private boolean ifContainsIt(GoodsDataBean databean) {
    		
    	for(GoodsDataBean data: goodsListData) {
    		if(data.getId() == databean.getId()) {
    			return true;
    		}		
    	}					
    		
    	return false;    	
    }

}