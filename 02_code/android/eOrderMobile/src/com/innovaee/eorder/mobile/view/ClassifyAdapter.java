package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;
import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.ClassifyDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class ClassifyAdapter extends BaseAdapter {
	private List<ClassifyDataBean> listItemsData;
	private Context context;
	private LayoutInflater layoutInflater;
	private ClassifyDataBean classifyItemData;

	public final class ListItemView {
		public ImageView image;
		public TextView title;
	}

	public ClassifyAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ClassifyAdapter(Context context, List<ClassifyDataBean> list) {
		this.listItemsData = list;
		this.context = context;
		layoutInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ClassifyAdapter(Context context, ArrayList<String> list) {
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
		Log.d("ClassifyAdapter", "getView()");

		if (convertView == null) {
			if (layoutInflater != null) {
				Log.d("ClassifyAdapter", "layoutInflater != null");
				view = layoutInflater.inflate(R.layout.classify_griditem, null);
				RemoteImageView imageView = (RemoteImageView) view
						.findViewById(R.id.classify_image);
					
				TextView name = (TextView) view
						.findViewById(R.id.classify_name);

				// 获取自定义的类实例
				classifyItemData = (ClassifyDataBean) listItemsData
						.get(position);
				imageView.setImageUrl(listItemsData.get(position)
						.getBitmapUrl());
				name.setText(classifyItemData.getName());
			}
		} else {
			Log.d("ClassifyAdapter", "layoutInflater == null");
			view = convertView;
		}

		return view;
	}

	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub

	}

}
