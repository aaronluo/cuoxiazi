package com.innovaee.eorder.mobile.view;

import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
			
public class OrderAdapter extends BaseAdapter {
	private List<GoodsDataBean> listItemsData;
	private Context context;			
	private LayoutInflater layoutInflater;
	private Handler handler;
		
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
										
		//if (convertView == null) {
			if (layoutInflater != null) {						
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
			}													
		//} else {	
		//	Log.d("GoodsAdapter", "layoutInflater == null");
		//	view = convertView;
		//}		
				
		return view;
	}	
}
