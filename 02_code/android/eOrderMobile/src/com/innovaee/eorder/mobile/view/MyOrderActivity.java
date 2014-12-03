package com.innovaee.eorder.mobile.view;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.R;
import com.innovaee.eorder.mobile.databean.GoodsDataBean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * @author wanglinglong
 * 
 */
public class MyOrderActivity extends Activity {
	private List<GoodsDataBean> selectOrderGoods;	
		
	/** Called when the activity is first created. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.myorder_activity);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
				
		ArrayList list = bundle.getParcelableArrayList("list");
		selectOrderGoods = (List<GoodsDataBean>) list.get(0);
						
	}
}