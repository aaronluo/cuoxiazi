package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 
 * @author wanglinglong
 * 
 */
public class MyOrderActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myorder_activity);
	}
}