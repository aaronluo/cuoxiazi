/***********************************************
 * Filename    : OrderHestoryDetailAdapter.java 
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
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

/**
 * 我的历史订单详情界面数据适配器
 *
 */
public class OrderHestoryDetailAdapter extends BaseAdapter {
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
    public OrderHestoryDetailAdapter(Context context) {
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
    public OrderHestoryDetailAdapter(Context context, List<GoodsDataBean> list,
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

        view = layoutInflater.inflate(R.layout.orderhestorydetail_listitem, null);
        
        // 获取自定义的类实例
        final GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listItemsData
                .get(position);

        RemoteImageView imageView = (RemoteImageView) view
                .findViewById(R.id.goods_image);
        imageView.setImageUrl(goodsItemDataTemp.getBitmapUrl());
        TextView goods_name = (TextView) view.findViewById(R.id.goods_name);
        final TextView priceTxtView = (TextView) view
                .findViewById(R.id.goods_allprice);
        final TextView countTxtView = (TextView) view
                .findViewById(R.id.count_text);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
            }
        });
        
        goods_name.setText(goodsItemDataTemp.getName());

        int count = goodsItemDataTemp.getCount();
        Double allPrice = goodsItemDataTemp.getPrice() * count;
        BigDecimal df = new BigDecimal(allPrice);	
        double rate2 = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String allPriceStr = String.valueOf(rate2);	
        countTxtView.setText(String.valueOf(count));
        priceTxtView.setText(allPriceStr);	
        				
        return view;
    }
    	
    @Override
    /**
     * 通知数据更新
     */
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
