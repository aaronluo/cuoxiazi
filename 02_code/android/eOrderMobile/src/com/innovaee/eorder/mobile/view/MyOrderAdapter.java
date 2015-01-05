/***********************************************
 * Filename    : MyOrderAdapter.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.mobile.databean.GoodsDataBean;
import com.innovaee.eorder.mobile.util.RemoteImageView;

/**
 * 我的订单界面数据适配器
 *
 */
public class MyOrderAdapter extends BaseAdapter {
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
    public MyOrderAdapter(Context context) {
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
    public MyOrderAdapter(Context context, List<GoodsDataBean> list,
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

        view = layoutInflater.inflate(R.layout.myorder_listitem, null);

        // 获取自定义的类实例
        final GoodsDataBean goodsItemDataTemp = (GoodsDataBean) listItemsData
                .get(position);

        RemoteImageView imageView = (RemoteImageView) view
                .findViewById(R.id.goods_image);
        imageView.setImageUrl(goodsItemDataTemp.getBitmapUrl());
        TextView name = (TextView) view.findViewById(R.id.goods_name);
        final TextView priceTxtView = (TextView) view
                .findViewById(R.id.goods_allprice);
        final TextView countTxtView = (TextView) view
                .findViewById(R.id.count_text);
        Button addBtn = (Button) view.findViewById(R.id.count_add_button);
        Button cutDownBtn = (Button) view
                .findViewById(R.id.count_cutdown_button);

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                addMyOrderSelectCount(goodsItemDataTemp);
                int count = goodsItemDataTemp.getCount();
                countTxtView.setText(String.valueOf(count));
                priceTxtView.setText(String.valueOf(goodsItemDataTemp
                        .getPrice() * count));
                updateActivityCountUi();
            }
        });

        cutDownBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                boolean isReset = cutDownMyOrderSelectCount(goodsItemDataTemp);

                if (isReset) {
                    resetActivityAdapterUi();
                } else {
                    int count = goodsItemDataTemp.getCount();
                    countTxtView.setText(String.valueOf(count));
                    priceTxtView.setText(String.valueOf(goodsItemDataTemp
                            .getPrice() * count));
                    updateActivityCountUi();
                }
            }
        });

        name.setText(goodsItemDataTemp.getName());

        int count = goodsItemDataTemp.getCount();
        Double allPrice = goodsItemDataTemp.getPrice() * count;
        countTxtView.setText(String.valueOf(count));
        priceTxtView.setText(String.valueOf(allPrice));

        return view;
    }

    /**
     * 得到当前选择的所有菜品的总数
     * 
     * @return
     */
    private int getMyOrderSelectCount() {
        int count = 0;

        if (listItemsData == null) {
            return count;
        }

        for (GoodsDataBean dataBean : listItemsData) {
            count = count + dataBean.getCount();
        }

        return count;
    }

    /**
     * 增加当前选中的菜品数量+1
     * 
     * @param dataBean
     *            当前选中的菜品数据Bean
     */
    private void addMyOrderSelectCount(GoodsDataBean dataBean) {
        for (GoodsDataBean goodsDataBean : listItemsData) {
            if (goodsDataBean.getId() == dataBean.getId()) {
                int count = goodsDataBean.getCount() + 1;
                goodsDataBean.setCount(count);
            }
        }
    }

    /**
     * 减少当前选中的菜品数量-1 等于0则删除该项
     * 
     * @param dataBean
     *            当前选中的菜品数据Bean
     * @return 是否需要重新设置Adapter
     */
    private boolean cutDownMyOrderSelectCount(GoodsDataBean dataBean) {
        for (GoodsDataBean goodsDataBean : listItemsData) {
            if (goodsDataBean.getId() == dataBean.getId()) {
                int count = goodsDataBean.getCount();
                if (count > 0) {
                    count--;

                    if (count > 0) {
                        goodsDataBean.setCount(count);
                        return false;
                    } else {
                        listItemsData.remove(goodsDataBean);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 刷新主MyOrderActivity的ui count显示界面
     */
    private void updateActivityCountUi() {
        Message msg = Message.obtain();
        msg.what = MyOrderActivity.MSG_UPDATE_COUNT;
        msg.obj = (Object) listItemsData;
        handler.sendMessage(msg);
    }

    /**
     * 重新设置MyOrderActivity的ui显示界面
     */
    private void resetActivityAdapterUi() {
        Message msg = Message.obtain();
        msg.what = MyOrderActivity.MSG_RESET_ADAPTER;
        msg.obj = (Object) listItemsData;
        handler.sendMessage(msg);
    }

    @Override
    /**
     * 通知数据更新
     */
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
