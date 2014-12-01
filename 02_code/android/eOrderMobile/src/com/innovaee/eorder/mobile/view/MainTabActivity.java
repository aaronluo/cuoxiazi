package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity {
	private final static String TAB_NAME_STRING_GOODSSHOP = "goodsshop";

	private final static String TAB_NAME_STRING_USERORDER = "userorder";

	private TabHost tabHost;

	private RadioButton radioBtnGoodsShop, radioBtnUserOrder;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maintab_activity);

		initTab();

		initView();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }
	
		
	/**
	 * 初始化控件
	 */
	private void initView() {
		radioBtnGoodsShop = (RadioButton) findViewById(R.id.tab_goodsshop);
		radioBtnUserOrder = (RadioButton) findViewById(R.id.tab_userorder);

		radioBtnGoodsShop.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_GOODSSHOP);
				radioBtnUserOrder.setChecked(false);
				radioBtnGoodsShop.setChecked(true);
			}
		});

		radioBtnUserOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_USERORDER);
				radioBtnGoodsShop.setChecked(false);
				radioBtnUserOrder.setChecked(true);
			}		
		});
	}

	/**
	 * 初始化tab
	 */
	private void initTab() {
		tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_GOODSSHOP)
				.setIndicator(TAB_NAME_STRING_GOODSSHOP)
				.setContent(new Intent(this, ClassifyActivity.class)));

		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_USERORDER)
				.setIndicator(TAB_NAME_STRING_USERORDER)
				.setContent(new Intent(this, UserOrderActivity.class)));
	}

}