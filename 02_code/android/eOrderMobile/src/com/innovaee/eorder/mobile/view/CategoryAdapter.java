package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.CategoryDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	private List<CategoryDataBean> listItemsData;
	private Context context;
	private LayoutInflater layoutInflater;
	private CategoryDataBean categoryItemData;

	//缓存Item View
	List<Integer> listPosition = new ArrayList<Integer>();  
	List<View> listView = new ArrayList<View>();  
	    
	public final class ListItemView {
		public ImageView image;
		public TextView title;
	}

	public CategoryAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CategoryAdapter(Context context, List<CategoryDataBean> list) {
		this.listItemsData = list;
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CategoryAdapter(Context context, ArrayList<String> list) {
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
		Log.d("categoryAdapter", "getView()");
		if (listPosition.contains(position) == false) {  
			//这里设置缓存的Item数量
			if(listPosition.size() > 50)  
			{  	
				//删除第一项
				listPosition.remove(0);  
				listView.remove(0);  
			}  		
			
			view = layoutInflater.inflate(R.layout.category_griditem, null);
			RemoteImageView imageView = (RemoteImageView) view.findViewById(R.id.category_image);
			TextView name = (TextView) view.findViewById(R.id.category_name);

			//获取自定义的类实例
			categoryItemData = (CategoryDataBean) listItemsData.get(position);
			imageView.setImageUrl(listItemsData.get(position).getBitmapUrl());
			name.setText(categoryItemData.getName());
			
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

}
