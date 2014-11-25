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
	
	private TabHost mTabHost;
		
	private RadioButton mRadioBtn_goodsshop, mRadioBtn_userorder, mRadioBtn_settings;
									
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
		mRadioBtn_goodsshop = (RadioButton)findViewById(R.id.tab_goodsshop);
		mRadioBtn_userorder = (RadioButton)findViewById(R.id.tab_userorder);		
		mRadioBtn_settings = (RadioButton)findViewById(R.id.tab_settings);
					
		mRadioBtn_goodsshop.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				mTabHost.clearFocus();	
				mTabHost.setCurrentTabByTag(TAB_NAME_STRING_GOODSSHOP);
				mRadioBtn_userorder.setChecked(false);
				mRadioBtn_settings.setChecked(false);
			}	
		});	
										
		mRadioBtn_userorder.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				mTabHost.clearFocus();
				mTabHost.setCurrentTabByTag(TAB_NAME_STRING_USERORDER);
				mRadioBtn_goodsshop.setChecked(false);
				mRadioBtn_settings.setChecked(false);
			}	
		});
				
		mRadioBtn_settings.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				mTabHost.clearFocus();
				mTabHost.setCurrentTabByTag(TAB_NAME_STRING_SETTINGS);
				mRadioBtn_goodsshop.setChecked(false);
				mRadioBtn_userorder.setChecked(false);
			}
		});		
	}
		
	/**
	 * 初始化tab
	 */
	private void initTab(){
		mTabHost = getTabHost();
			
		mTabHost.addTab(mTabHost.newTabSpec(TAB_NAME_STRING_GOODSSHOP)
				.setIndicator(TAB_NAME_STRING_GOODSSHOP)
				.setContent(new Intent(this, FoodsShopActivity.class)));
		
		mTabHost.addTab(mTabHost.newTabSpec(TAB_NAME_STRING_USERORDER)
				.setIndicator(TAB_NAME_STRING_USERORDER)
				.setContent(new Intent(this, UserOrderActivity.class)));
		
		mTabHost.addTab(mTabHost.newTabSpec(TAB_NAME_STRING_SETTINGS)
				.setIndicator(TAB_NAME_STRING_SETTINGS)
				.setContent(new Intent(this, SettingsActivity.class)));
	}			

}