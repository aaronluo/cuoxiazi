package com.innovaee.eorder.mobile.view;
	
								

import com.innovaee.eorder.R;
	
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
	
			
@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity {
	private final static String TAB_NAME_STRING_GOODSSHOP = "goodsshop";
	
	private final static String TAB_NAME_STRING_USERORDER = "userorder";
	
	private final static String TAB_NAME_STRING_SETTINGS = "settings";
	
	private TabHost tabHost;
		
	private RadioButton radioBtnGoodsShop, radioBtnUserOrder, radioBtnSettings;
									
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintab_activity);
		
		initTab();	
			
		initView();		
	}
		
	/**
	 * 初始化控件
	 */
	private void initView() {
		radioBtnGoodsShop = (RadioButton)findViewById(R.id.tab_goodsshop);
		radioBtnUserOrder = (RadioButton)findViewById(R.id.tab_userorder);		
		radioBtnSettings = (RadioButton)findViewById(R.id.tab_settings);
					
		radioBtnGoodsShop.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				tabHost.clearFocus();	
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_GOODSSHOP);
				radioBtnUserOrder.setChecked(false);
				radioBtnSettings.setChecked(false);
			}	
		});	
										
		radioBtnUserOrder.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_USERORDER);
				radioBtnGoodsShop.setChecked(false);
				radioBtnSettings.setChecked(false);
			}	
		});
				
		radioBtnSettings.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				tabHost.clearFocus();
				tabHost.setCurrentTabByTag(TAB_NAME_STRING_SETTINGS);
				radioBtnGoodsShop.setChecked(false);
				radioBtnUserOrder.setChecked(false);
			}
		});		
	}
		
	/**
	 * 初始化tab
	 */
	private void initTab(){
		tabHost = getTabHost();
			
		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_GOODSSHOP)
				.setIndicator(TAB_NAME_STRING_GOODSSHOP)
				.setContent(new Intent(this, FoodsShopActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_USERORDER)
				.setIndicator(TAB_NAME_STRING_USERORDER)
				.setContent(new Intent(this, UserOrderActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec(TAB_NAME_STRING_SETTINGS)
				.setIndicator(TAB_NAME_STRING_SETTINGS)
				.setContent(new Intent(this, SettingsActivity.class)));
	}			

}