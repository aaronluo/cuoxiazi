/***********************************************
 * Filename    : OrderAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

/**
 * 下订单界面数据适配器
 *
 */
public class OrderAdapter extends BaseAdapter {
    //绑定数据
    private List<GoodsDataBean> listItemsData;
    
    //调用者Context
    private Context context;
    
    //xml文件加载器
    private LayoutInflater layoutInflater;
    
    //消息Handler
    private Handler handler;

    //缓存Item
    List<Integer> listPosition = new ArrayList<Integer>();
        
    //缓存View
    List<View> listView = new ArrayList<View>();

    /**
     * 构造函数    
     * @param context 调用者Context
     */
    public OrderAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 构造函数
     * @param context 调用者Context
     * @param list 绑定数据
     * @param handler 消息Handler
     */
    public OrderAdapter(Context context, List<GoodsDataBean> list,
            Handler handler) {
        this.listItemsData = list;
        this.context = context;
        this.handler = handler;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 得到绑定数据总数
     */
    public int getCount() {
        if (listItemsData != null) {
            return listItemsData.size();
        } else {
            return 0;
        }
    }

    /**
     * 得到每一项
     */
    public Object getItem(int position) {
        return listItemsData.get(position);
    }

    /**
     * 得到每一项id
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到每一项的View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        // 判断该项是否在缓存内
        if (listPosition.contains(position) == false) {
            // 这里设置缓存的Item数量
            if (listPosition.size() > 50) {
                // 删除第一项
                listPosition.remove(0);
                listView.remove(0);
            }

            view = layoutInflater.inflate(R.layout.order_listitem, null);

            // 获取自定义的类实例
            GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listItemsData
                    .get(position);

            TextView nameTxtView = (TextView) view
                    .findViewById(R.id.order_name);
            TextView countTxtView = (TextView) view
                    .findViewById(R.id.order_count);
            TextView totalPirceTxtView = (TextView) view
                    .findViewById(R.id.order_totalprice);

            nameTxtView.setText(goodsItemDataTemp.getName());

            int count = goodsItemDataTemp.getCount();
            Double allPrice = goodsItemDataTemp.getPrice() * count;
            BigDecimal df = new BigDecimal(allPrice);	
            double rate2 = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            String allPriceStr = String.valueOf(rate2);			
            countTxtView.setText(context
                    .getString(R.string.order_item_text_count)
                    + String.valueOf(count));
            totalPirceTxtView.setText(context
                    .getString(R.string.order_item_text_totalprice)
                    + allPriceStr);
            				
            // 添加最新项
            listPosition.add(position);
            listView.add(view);
        } else {
            view = listView.get(listPosition.indexOf(position));
        }

        return view;
    }
}
