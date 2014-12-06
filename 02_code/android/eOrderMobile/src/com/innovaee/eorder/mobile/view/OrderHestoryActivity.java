package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * 
 * @author wanglinglong
 * 
 */
public class OrderHestoryActivity extends Activity {
	private int userId;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderhestory_activity);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getInt("userid", 0);
        		
	}
}