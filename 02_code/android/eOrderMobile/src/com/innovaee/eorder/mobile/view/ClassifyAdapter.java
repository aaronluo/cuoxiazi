package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
				
public class ClassifyAdapter extends BaseAdapter {
	private List<Map<String, ClassifyDataBean>> listItemsData;
	private Context context;
	private LayoutInflater layoutInflater;
	private ClassifyDataBean classifyItemData;
						
	public final class ListItemView{               
        public ImageView image;    
        public TextView title;    
	}    
	
	public ClassifyAdapter(Context context) {
		this.context = context;
	}
		
	public ClassifyAdapter(Context context, List<Map<String, ClassifyDataBean>> list) {
		listItemsData = list;
		this.context = context;
	}	
		
	public ClassifyAdapter(Context context, ArrayList<String> list) {
		this.context = context;
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
		
        if (layoutInflater != null) {	        	
            view = layoutInflater.inflate(R.layout.classify_griditem, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.classify_image);
            TextView name = (TextView) view.findViewById(R.id.classify_name);
            		
            //获取自定义的类实例					
            classifyItemData = (ClassifyDataBean) listItemsData.get(position);				
            //imageView.setImageBitmap(classifyItemData.getId());
            name.setText(classifyItemData.getName());	      		      		
        }				
        return view;	
	}
	
	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub
							
	}

}
