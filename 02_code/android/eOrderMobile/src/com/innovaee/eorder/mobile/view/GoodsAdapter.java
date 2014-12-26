/***********************************************
 * Filename		: GoodsAdapter.java																									
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
		
/**
 * 菜品数据适配器
 * @author leon.wang
 *
 */
public class GoodsAdapter extends BaseAdapter {
	private List<GoodsDataBean> listItemsData;
	private Context context;
	private LayoutInflater layoutInflater;
	private GoodsDataBean goodsItemData;
	private Handler handler;
	
	//缓存Item View
	List<Integer> listPosition = new ArrayList<Integer>();  
    List<View> listView = new ArrayList<View>();  
    			
	public final class ListItemView {
		public ImageView image;
		public TextView title;
	}
	
	public GoodsAdapter(Context context, List<GoodsDataBean> list, Handler handler) {
		this.listItemsData = list;
		this.context = context;
		this.handler = handler;	
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public GoodsAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if (listPosition.contains(position) == false) {  
			//这里设置缓存的Item数量
			if(listPosition.size() > 50)  
			{  	
				//删除第一项
				listPosition.remove(0);  
				listView.remove(0);  
			}  		
				
			view = layoutInflater.inflate(R.layout.goods_griditem, null);
			RemoteImageView imageView = (RemoteImageView) view.findViewById(R.id.goods_image);
			TextView name = (TextView) view.findViewById(R.id.goods_name);
			TextView price = (TextView) view.findViewById(R.id.goods_price);		
							
			// 获取自定义的类实例
			goodsItemData = (GoodsDataBean) listItemsData.get(position);
			imageView.setImageUrl(listItemsData.get(position).getBitmapUrl());
			name.setText(goodsItemData.getName());			
			price.setText(context.getString(R.string.main_griditem_text_rmb) + String.valueOf(goodsItemData.getPrice()));
					
			//添加最新项
			listPosition.add(position);  
	        listView.add(view);  	            	
		} else {  		
			view = listView.get(listPosition.indexOf(position));
		}		  			
		
		return view;
	}
		
	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub

	}
	
	@Override 	
	public void notifyDataSetChanged() { 		 		 		
		super.notifyDataSetChanged(); 	
	}	
	
}
