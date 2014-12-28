/***********************************************
 * Filename		: OrderAdapter.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
			
/**
 * 下订单界面数据适配器
 *
 */
public class OrderAdapter extends BaseAdapter {
	private List<GoodsDataBean> listItemsData;
	private Context context;			
	private LayoutInflater layoutInflater;
	private Handler handler;
		
	//缓存Item View
	List<Integer> listPosition = new ArrayList<Integer>();  
	List<View> listView = new ArrayList<View>();
		
	public OrderAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public OrderAdapter(Context context, List<GoodsDataBean> list, Handler handler) {
		this.listItemsData = list;
		this.context = context;
		this.handler = handler;	
		layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
	}					
	
	public int getCount() {
		if (listItemsData != null) {
			return listItemsData.size();
		} else {
			return 0;
		}
	}

	public Object getItem(int position) {
		return listItemsData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		
		//判断该项是否在缓存内
		if (listPosition.contains(position) == false) {  
			//这里设置缓存的Item数量
			if(listPosition.size() > 50)  
			{  	
				//删除第一项
				listPosition.remove(0);  
				listView.remove(0);  
			}  		
									
			view = layoutInflater.inflate(R.layout.order_listitem, null);
							
			// 获取自定义的类实例		
			GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listItemsData.get(position);
								
			TextView nameTxtView = (TextView) view.findViewById(R.id.order_name);
			TextView countTxtView = (TextView) view.findViewById(R.id.order_count);
			TextView totalPirceTxtView = (TextView) view.findViewById(R.id.order_totalprice);	
							
			nameTxtView.setText(goodsItemDataTemp.getName());		
				
			int count = goodsItemDataTemp.getCount();				
			Double allPrice = goodsItemDataTemp.getPrice() * count;									
			countTxtView.setText(context.getString(R.string.order_item_text_count) + String.valueOf(count));				
			totalPirceTxtView.setText(context.getString(R.string.order_item_text_totalprice) + String.valueOf(allPrice));

			//添加最新项
			listPosition.add(position);  
	        listView.add(view);  
		} else {  		
			view = listView.get(listPosition.indexOf(position));
		}																		
					
		return view;
	}	
}
