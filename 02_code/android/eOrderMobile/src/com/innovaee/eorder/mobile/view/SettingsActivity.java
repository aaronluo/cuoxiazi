/***********************************************
 * Filename		: SettingsActivity.java	
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.view;

import com.innovaee.eorder.R;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 设置界面
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class SettingsActivity extends Activity {
    EditText serviceUrl;
    Button okBtn;
    ActionBar actionBar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_activity);

        initView();

        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        actionBar = getActionBar();

        serviceUrl = (EditText) findViewById(R.id.service_url);
        String url = readServiceUrl();
        serviceUrl.setText(url);

        okBtn = (Button) findViewById(R.id.service_btn);
    }

    /**
     * 初始化Data
     */
    private void initData() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(true);

        okBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String url = serviceUrl.getText().toString();

                if (url != null && !url.equals("")) {
                    saveServiceUrl(url);
                    Toast.makeText(SettingsActivity.this,
                            R.string.setting_activity_text_successful,
                            Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                } else {
                    Toast.makeText(SettingsActivity.this,
                            R.string.setting_activity_text_input,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 系统自动调用
     */
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

    /**
     * 写入服务器地址
     * 
     * @param url
     *            输入的服务器地址
     */
    private void saveServiceUrl(String url) {
        SharedPreferences mySharedPreferences = getSharedPreferences("setting",
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = mySharedPreferences.edit();

        editor.putString("serviceUrl", url);
        editor.commit();
    }

    /**
     * 读取服务器地址
     * 
     * @return 返回服务器地址
     */
    private String readServiceUrl() {
        SharedPreferences sharedPreferences = getSharedPreferences("setting",
                Activity.MODE_PRIVATE);

        String url = sharedPreferences.getString("serviceUrl", "");

        return url;
    }
}