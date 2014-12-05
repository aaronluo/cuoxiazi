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
		listchangeItemsData = rmTwiceData(list, itemNumMap);
													
		//Log.d("GoodsAdapter", "listchangeItemsData.size()" + listchangeItemsData.size());
			
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
		Log.d("GoodsAdapter", "getView() position=" + position);
						
		if (convertView == null) {
			if (layoutInflater != null) {						
				view = layoutInflater.inflate(R.layout.order_listitem, null);
					
				// 获取自定义的类实例		
				final GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listchangeItemsData.get(position);
						
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
		                Double allPrice = goodsItemDataTemp.getPrice() * (position + 1);
		                price.setText(String.valueOf(allPrice));	
			        }												
						
			        public void onNothingSelected(AdapterView parent) {
			        }				
			    });
					
				imageView.setImageUrl(goodsItemDataTemp.getBitmapUrl());
				name.setText(goodsItemDataTemp.getName());		
						
				Double allPrice = 0.0;	
				int count = 1;
				if(itemNumMap != null && itemNumMap.size() > 0) {
					count = (Integer) itemNumMap.get(String.valueOf(goodsItemDataTemp.getId()));
					allPrice = goodsItemDataTemp.getPrice() * count;							 									
				}																								
					
				price.setText(String.valueOf(allPrice));																					
				numberSpinner.setSelection(count-1);
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
		//Map map = new HashMap(); 								
		Map<String, Integer> map = new HashMap<String, Integer>();
					
		if(list != null) {
			for (GoodsDataBean dataBean: list) {
				int id = dataBean.getId();	
				String idString = String.valueOf(id);
			
				Integer count = (Integer) map.get(idString); 		 
				map.put(idString, (count == null) ? 1 : count + 1);  	          
			}
		}
			
		Log.d("GoodsAdapter", "map.size()" + map.size());
		printMap(map);
							
		return map;
	}
		
		    		    	
	public static void printMap(Map map) {  
		Log.d("GoodsAdapter", "通过Map.keySet遍历key和value：");	
					
		Set<Object> keySet = map.keySet();//获取map的key值的集合，set集合
			
		for(Object obj:keySet) {
			//遍历key
		    Log.d("GoodsAdapter", "key:"+ obj + ",Value:" + map.get(obj));
		}		
    } 
	
	public List<GoodsDataBean> rmTwiceData(List<GoodsDataBean> list, Map map) {
		List<GoodsDataBean> newList = new ArrayList<GoodsDataBean>();
			
		Set<Object> keySet = map.keySet();//获取map的key值的集合，set集合
				
		for(Object obj:keySet) {
			//遍历key
		    Log.d("GoodsAdapter", "key:"+ obj + ",Value:" + map.get(obj));
		    for (GoodsDataBean dataBean: list) {	
				int id = dataBean.getId();	
				String idString = String.valueOf(id);			
				 		
				if(obj.equals(idString)) {
					newList.add(dataBean);
					break;
				}																  	          
			}				    
		}		
				
		Log.d("GoodsAdapter", "list.size()" + list.size());
		Log.d("GoodsAdapter", "newList.size()" + newList.size());				
		return newList;
	}	
	
}
