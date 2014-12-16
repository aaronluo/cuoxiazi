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
		
public class GoodsAdapter extends BaseAdapter {
	private List<GoodsDataBean> listItemsData;
	private Context context;
	private LayoutInflater layoutInflater;
	private GoodsDataBean goodsItemData;
	private Handler handler;
			
	private boolean  first = true;  
	private int mCount = 0;	
	private View positionView;
			
	public final class ListItemView {
		public ImageView image;
		public TextView title;
	}

	public GoodsAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
					
		if (position == 0)  
	    {  
	        mCount++;  
	        if (mCount > 1)  
		    {  
		        Log.v("GoodsAdapter", "<getView> drop !!!");  
		        return positionView;	  
		    }  	
	    }  
			
		if (convertView == null) {
			if (layoutInflater != null) {
				Log.d("GoodsAdapter", "layoutInflater != null");
				view = layoutInflater.inflate(R.layout.goods_griditem, null);
				RemoteImageView imageView = (RemoteImageView) view.findViewById(R.id.goods_image);
				TextView name = (TextView) view.findViewById(R.id.goods_name);
				TextView price = (TextView) view.findViewById(R.id.goods_price);		
							
				// 获取自定义的类实例
				goodsItemData = (GoodsDataBean) listItemsData.get(position);
				imageView.setImageUrl(listItemsData.get(position).getBitmapUrl());
				name.setText(goodsItemData.getName());			
				price.setText(context.getString(R.string.main_griditem_text_rmb) + String.valueOf(goodsItemData.getPrice()));
					
				convertView = view;
							
				if (position == 0) { 
					positionView = view;
				}
			}																									
		} else {
			Log.d("GoodsAdapter", "layoutInflater == null");
			view = convertView;
		}
				
		return view;
	}
		
	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub

	}
	
	@Override 	
	public void notifyDataSetChanged() { 		
		//因为notify的时候也会导致当前位置的getView重复调用 		
		first = true; 		
		super.notifyDataSetChanged(); 	
		}	
			
}
