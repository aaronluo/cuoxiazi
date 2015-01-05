/***********************************************
 * Filename    : AboutActivity.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.view;


/**
 * 关于界面
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AboutActivity extends Activity {
    //显示文本编辑器
    TextView aboutText;
    
    //窗口ActionBar
    ActionBar actionBar;

    /**
     * onCreate方法，系统默认调用
     */
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

    /**
     * 系统ActionBar点击处理
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

}