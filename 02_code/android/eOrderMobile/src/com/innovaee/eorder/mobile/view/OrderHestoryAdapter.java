package com.innovaee.eorder.mobile.view;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
		
public class OrderHestoryAdapter extends BaseAdapter {
	private List<OrderHestoryDataBean> listItemsData;
	private Context context;			
	private LayoutInflater layoutInflater;
		
	public OrderHestoryAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public OrderHestoryAdapter(Context context, List<OrderHestoryDataBean> list) {
		this.listItemsData = list;
		this.context = context;		
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

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		Log.d("GoodsAdapter", "getView() position=" + position);
						
		//if (convertView == null) {
			if (layoutInflater != null) {						
				view = layoutInflater.inflate(R.layout.orderhestory_listitem, null);
						
				// 获取自定义的类实例		
				OrderHestoryDataBean orderHestoryDataBean = (OrderHestoryDataBean) listItemsData.get(position);
				
				TextView orderIdTxtView = (TextView) view.findViewById(R.id.order_id);
				TextView timeTxtView = (TextView) view.findViewById(R.id.order_time);
				TextView totalPriceTxtView = (TextView) view.findViewById(R.id.order_totalprice);	
							
				long longTime = Long.valueOf(orderHestoryDataBean.getTime()).longValue();
				Date d = new Date(longTime);	
				String time = DateFormat.getDateInstance().format(d);			
					
				orderIdTxtView.setText(context.getString(R.string.orderhestory_item_text_order) + String.valueOf(orderHestoryDataBean.getId()));				
				timeTxtView.setText(context.getString(R.string.orderhestory_item_text_time) + time);		
				totalPriceTxtView.setText(context.getString(R.string.orderhestory_item_text_totalprice) + String.valueOf(orderHestoryDataBean.getTotalPrice()));
			}									
		//} else {		
		//	Log.d("GoodsAdapter", "layoutInflater == null");
		//	view = convertView;
		//}		
						
		return view;
	}	
}
