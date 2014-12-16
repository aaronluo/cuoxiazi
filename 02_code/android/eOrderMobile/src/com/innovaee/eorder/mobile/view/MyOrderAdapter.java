package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Spinner;
import android.widget.TextView;
	
public class MyOrderAdapter extends BaseAdapter {
	private List<GoodsDataBean> listItemsData;
	private Context context;			
	private LayoutInflater layoutInflater;
	private Handler handler;
		
	private boolean  first = true;  
	private int mCount = 0;	
	private View positionView;
	
	public MyOrderAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public MyOrderAdapter(Context context, List<GoodsDataBean> list, Handler handler) {
		this.listItemsData = list;
		this.context = context;
		this.handler = handler;	
		layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
	}					
	
	public int getCount() {
		// TODO Auto-generated method stub
		if (listItemsData != null) {
			return listItemsData.size();
		} else {
			return 0;
		}
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItemsData.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		Log.d("GoodsAdapter", "getView() position=" + position);
		
		//后续需要优化
		//if (convertView == null) {
			if (layoutInflater != null) {						
				view = layoutInflater.inflate(R.layout.myorder_listitem, null);
					
				// 获取自定义的类实例		
				final GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listItemsData.get(position);
								
				Log.d("GoodsAdapter", "goodsItemDataTemp.getId()=" + goodsItemDataTemp.getId());
				Log.d("GoodsAdapter", "goodsItemDataTemp.getName()=" + goodsItemDataTemp.getName());	
						
				RemoteImageView imageView = (RemoteImageView) view.findViewById(R.id.goods_image);
				TextView name = (TextView) view.findViewById(R.id.goods_name);
				final TextView priceTxtView = (TextView) view.findViewById(R.id.goods_allprice);
				final TextView countTxtView = (TextView) view.findViewById(R.id.count_text);	
				Button addBtn = (Button) view.findViewById(R.id.count_add_button);
				Button cutDownBtn = (Button) view.findViewById(R.id.count_cutdown_button);
							
				addBtn.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View paramAnonymousView)
					{	
						addMyOrderSelectCount(goodsItemDataTemp);
						int count = goodsItemDataTemp.getCount();	
						countTxtView.setText(String.valueOf(count));
						priceTxtView.setText(String.valueOf(goodsItemDataTemp.getPrice() * count));	
						updateActivityCountUi();
					}																							
				});	
					
				cutDownBtn.setOnClickListener(new View.OnClickListener()
				{	
					public void onClick(View paramAnonymousView)
					{	 	 
						boolean isReset = cutDownMyOrderSelectCount(goodsItemDataTemp);
						
						if (isReset) {
							resetActivityAdapterUi(); 
						} else {
							int count = goodsItemDataTemp.getCount();		
							countTxtView.setText(String.valueOf(count));
							priceTxtView.setText(String.valueOf(goodsItemDataTemp.getPrice() * count));	
							updateActivityCountUi();
						}		
					}																				
				});	
					
				imageView.setImageUrl(goodsItemDataTemp.getBitmapUrl());
				name.setText(goodsItemDataTemp.getName());		
				
				int count = goodsItemDataTemp.getCount();				
				Double allPrice = goodsItemDataTemp.getPrice() * count;									
				countTxtView.setText(String.valueOf(count));				
				priceTxtView.setText(String.valueOf(allPrice));	
				
				convertView = view;
				
				if (position == 0) { 
					positionView = view;
				}
			}								
		//} else {	
		//	Log.d("GoodsAdapter", "layoutInflater == null");
		//	view = convertView;
		//}		
						
		return view;
	}
	
	/**
	 * 得到当前选择的所有菜品的总数
	 * @return
	 */	
	private int getMyOrderSelectCount() {
		int count = 0;
		
		if(listItemsData == null) {
			return count;
		}		
			
		for (GoodsDataBean dataBean: listItemsData) {
			count = count + dataBean.getCount();
		}	
		
		return count;
	}
	
	/**
	 * 增加当前选中的菜品数量+1
	 */
	private void addMyOrderSelectCount(GoodsDataBean dataBean) {
		for (GoodsDataBean goodsDataBean: listItemsData) {
			if(goodsDataBean.getId() == dataBean.getId()) {
				int count = goodsDataBean.getCount() + 1;
				goodsDataBean.setCount(count);
			}
		}
	}
	
	/**
	 * 减少当前选中的菜品数量-1
	 * 等于0则删除该项
	 * @param dataBean
	 * @return 是否需要重新设置Adapter
	 */
	private boolean cutDownMyOrderSelectCount(GoodsDataBean dataBean) {
		for (GoodsDataBean goodsDataBean: listItemsData) {
			if(goodsDataBean.getId() == dataBean.getId()) {
				int count = goodsDataBean.getCount();
				if(count > 0) {
					count--;
							
					if(count > 0) {
						goodsDataBean.setCount(count);
						return false;
					} else {
						listItemsData.remove(goodsDataBean);
						return true;
					}
				} 		
			}
		}	
			
		return false;
	}
				
	/**
	 * 刷新主MyOrderActivity的ui count显示界面
	 */												
	private void updateActivityCountUi() {		
		Message msg = Message.obtain();
		msg.what = MyOrderActivity.MSG_UPDATE_COUNT;	
		msg.obj = (Object)listItemsData;	
		handler.sendMessage(msg);	
	}			

	/**	
	 * 重新设置MyOrderActivity的ui显示界面
	 */													
	private void resetActivityAdapterUi() {		
		Message msg = Message.obtain();
		msg.what = MyOrderActivity.MSG_RESET_ADAPTER;	
		msg.obj = (Object)listItemsData;	
		handler.sendMessage(msg);		
	}
}
