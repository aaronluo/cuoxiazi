package com.innovaee.eorder.mobile;
	
								
import com.innovaee.eorder.R;
	
import android.app.TabActivity;
import android.os.Bundle;
	
			
@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintab);
		initTab();		
		initView();		
	}
											
	/**
	 * 初始化控件
	 */
	private void initView() {
	}
		
	/**
	 * 初始化tab
	 */
	private void initTab(){
	}			

}