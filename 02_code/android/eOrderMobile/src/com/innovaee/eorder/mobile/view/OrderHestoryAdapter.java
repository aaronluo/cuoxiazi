/***********************************************
 * Filename    : OrderHestoryAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.OrderHestoryDataBean;

/**
 * 订单历史查询界面数据适配器
 *
 */
public class OrderHestoryAdapter extends BaseAdapter {
    // 会员历史记录数据
    private List<OrderHestoryDataBean> listItemsData;

    //调用者Context
    private Context context;
    
    //xml文件加载器
    private LayoutInflater layoutInflater;

    // 缓存Item位置信息
    List<Integer> listPosition = new ArrayList<Integer>();

    // 缓存ItemView
    List<View> listView = new ArrayList<View>();

    /**
     * 构造函数
     * @param context 调用者Context
     */
    public OrderHestoryAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 构造函数
     * @param context 调用者Context
     * @param list 绑定数据
     */
    public OrderHestoryAdapter(Context context, List<OrderHestoryDataBean> list) {
        this.listItemsData = list;
        this.context = context;
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

        // 检查是否包含在缓存内
        if (listPosition.contains(position) == false) {
            // 这里设置缓存的Item数量
            if (listPosition.size() > 50) {
                // 删除第一项
                listPosition.remove(0);
                listView.remove(0);
            }

            view = layoutInflater.inflate(R.layout.orderhestory_listitem, null);

            // 获取自定义的类实例
            OrderHestoryDataBean orderHestoryDataBean = (OrderHestoryDataBean) listItemsData
                    .get(position);

            TextView timeTxtView = (TextView) view
                    .findViewById(R.id.order_time);
            TextView totalPriceTxtView = (TextView) view
                    .findViewById(R.id.order_totalprice);

            String orderSeq = orderHestoryDataBean.getorderSeq();
            
            timeTxtView.setText(context			
                    .getString(R.string.orderhestory_item_text_orderseq) + orderSeq);
            totalPriceTxtView.setText(context		
                    .getString(R.string.orderhestory_item_text_totalprice)
                    + String.valueOf(orderHestoryDataBean.getTotalPrice()));
            					
            // 添加最新项
            listPosition.add(position);
            listView.add(view);
        } else {
            view = listView.get(listPosition.indexOf(position));
        }

        return view;
    }
}
