/***********************************************
 * Filename    : GoodsAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

/**
 * 菜品数据适配器
 *
 */
public class GoodsAdapter extends BaseAdapter {
    //绑定数据
    private List<GoodsDataBean> listItemsData;
    
    //调用者Context
    private Context context;
    
    //xml文件加载器
    private LayoutInflater layoutInflater;
    
    //每一项数据
    private GoodsDataBean goodsItemData;
    
    //消息Handler
    private Handler handler;

    //缓存Item
    List<Integer> listPosition = new ArrayList<Integer>();
    
    //缓存View
    List<View> listView = new ArrayList<View>();
    
    /**
     * 构造函数
     * @param context 调用者Context
     * @param list 绑定数据
     * @param handler 消息Handler
     */
    public GoodsAdapter(Context context, List<GoodsDataBean> list,
            Handler handler) {
        this.listItemsData = list;
        this.context = context;
        this.handler = handler;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 构造函数
     * @param context 调用者Context
     * @param list 绑定数据
     */
    public GoodsAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 得到绑定数据数目
     */
    public int getCount() {
        if (listItemsData != null) {
            return listItemsData.size();
        } else {
            return 0;
        }
    }

    /**
     * 得到绑定数据的每一项
     */
    public Object getItem(int position) {
        return listItemsData.get(position);
    }

    /**
     * 得到每一项的id
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到每一项的View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (listPosition.contains(position) == false) {
            // 这里设置缓存的Item数量
            if (listPosition.size() > 50) {
                // 删除第一项
                listPosition.remove(0);
                listView.remove(0);
            }

            view = layoutInflater.inflate(R.layout.goods_griditem, null);
            RemoteImageView imageView = (RemoteImageView) view
                    .findViewById(R.id.goods_image);
            TextView name = (TextView) view.findViewById(R.id.goods_name);
            TextView price = (TextView) view.findViewById(R.id.goods_price);

            // 获取自定义的类实例
            goodsItemData = (GoodsDataBean) listItemsData.get(position);
            imageView.setImageUrl(listItemsData.get(position).getBitmapUrl());
            name.setText(goodsItemData.getName());
            price.setText(context.getString(R.string.main_griditem_text_rmb)
                    + String.valueOf(goodsItemData.getPrice()));

            // 添加最新项
            listPosition.add(position);
            listView.add(view);
        } else {
            view = listView.get(listPosition.indexOf(position));
        }

        return view;
    }
}
