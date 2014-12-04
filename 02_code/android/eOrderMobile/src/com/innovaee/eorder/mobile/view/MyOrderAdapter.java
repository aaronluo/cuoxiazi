package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

import android.content.Context;
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
	private List<GoodsDataBean> listchangeItemsData;
	private List<Map<String,Object>> itemList = new ArrayList<Map<String,Object>>();
	private Context context;			
	private LayoutInflater layoutInflater;
	private GoodsDataBean goodsItemData;
	private Map itemNumMap;
	private Handler handler;
	  
    private static final String[] selectNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};  
   
    ArrayAdapter<String> adapter;
    
    List allNum = null;  	
		
	public final class ListItemView {
		public ImageView image;
		public TextView title;
	}

	public MyOrderAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public MyOrderAdapter(Context context, List<GoodsDataBean> list, Handler handler) {
		this.listItemsData = list;
		this.context = context;
		this.handler = handler;	
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		itemNumMap = getItemData(list);
		listchangeItemsData = removeDuplicateWithOrder(list);
		
		allNum = new ArrayList<String>();  
        for(int i = 0; i < selectNum.length; i++){  
        	allNum.add(selectNum[i]);  	
        }  		
        
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, allNum);  
        adapter.setDropDownViewResource(R.layout.myspinner_dropdown);  	
	}					
	
	public MyOrderAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		if (listchangeItemsData != null) {
			return listchangeItemsData.size();
		} else {
			return 0;
		}
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listchangeItemsData.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		Log.d("GoodsAdapter", "getView()");

		if (convertView == null) {
			if (layoutInflater != null) {
				Log.d("GoodsAdapter", "layoutInflater != null");				
				view = layoutInflater.inflate(R.layout.order_listitem, null);
					
				// 获取自定义的类实例		
				goodsItemData = (GoodsDataBean) listchangeItemsData.get(position);
				
				RemoteImageView imageView = (RemoteImageView) view.findViewById(R.id.goods_image);
				TextView name = (TextView) view.findViewById(R.id.goods_name);
				final TextView price = (TextView) view.findViewById(R.id.goods_allprice);
				Spinner numberSpinner = (Spinner) view.findViewById(R.id.number_Spinner);		
				
				numberSpinner.setAdapter(adapter);   
				numberSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView parent, View view,
			                    int position, long id) {
			            // 在选中之后触发
						Log.d("MyOrderAdapter", "numberSpinner position=" + position);
		                Double allPrice = goodsItemData.getPrice() * position;
		                price.setText(String.valueOf(allPrice));
			        }										

			        public void onNothingSelected(AdapterView parent) {
			        	// 这个一直没有触发，我也不知道什么时候被触发。
			            //在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
			        }				
			    });
								
			
				imageView.setImageUrl(goodsItemData.getBitmapUrl());
				name.setText(goodsItemData.getName());			
				price.setText(String.valueOf(goodsItemData.getPrice()));
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
									
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getItemData(List<GoodsDataBean> list) {
		Map map = new HashMap();  
						
		for (GoodsDataBean dataBean: list) {
			int id = dataBean.getId();	
			String idString = String.valueOf(id);
			
			Integer count = (Integer) map.get(idString); 		 
	        map.put(idString, (count == null) ? 1 : count + 1);  	          
		}
					
		return map;
	}
		
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<GoodsDataBean> removeDuplicateWithOrder(List<GoodsDataBean> list) {
        Set set = new HashSet();
        List<GoodsDataBean> newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add((GoodsDataBean)element);
        }			
        			
        return newList;
    }		
	
}
