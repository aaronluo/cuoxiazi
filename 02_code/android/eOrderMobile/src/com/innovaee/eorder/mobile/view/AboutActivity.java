package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
	
/**
 * 
 * @author wanglinglong
 * 
 */	
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AboutActivity extends Activity {
	TextView aboutText;	
	ActionBar actionBar;	
			
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
		
		initView();
			
		initData();			
	}	
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		actionBar = getActionBar(); 
			
		aboutText = (TextView) findViewById(R.id.about_text);			
	}								
				
	/**
	 * 初始化Data
	 */	
	private void initData() {					
		actionBar.setDisplayHomeAsUpEnabled(false);	
		actionBar.setHomeButtonEnabled(true);	
	}	
	
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
	    switch (item.getItemId()) {  
	    case android.R.id.home:  
	        finish();  
	        return true;  

	    default:	
            return super.onOptionsItemSelected(item);    	
	    }  
	}  
		
}