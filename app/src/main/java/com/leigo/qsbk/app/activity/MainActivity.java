package com.leigo.qsbk.app.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.base.BaseActionBarActivity;
import com.leigo.qsbk.app.fragments.ActionBarFragmentTabHost;
import com.leigo.qsbk.app.fragments.MessageListFragment;
import com.leigo.qsbk.app.fragments.NearbyListFragment;
import com.leigo.qsbk.app.fragments.QiushiListFragment;
import com.leigo.qsbk.app.utils.UIHelper;
import com.leigo.qsbk.app.widget.ActionBarTabPanel;


public class MainActivity extends BaseActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String SELECTED_PAGE_ID = "selected_page";
    public static final String SELECTED_TAB_ID = "selected_tab";
    public static final String TAB_MESSAGE_ID = "tab_message";
    public static final String TAB_NEARBY_ID = "tab_nearby";
    public static final String TAB_QIUSHI_ID = "tab_qiushi";

    private ActionBarFragmentTabHost mActionBarFragmentTabHost;


    private String selected_tab;
    private Fragment mFragment;
    private int selected_page;

    @Override
    protected int getResource() {
        return R.layout.main_activity;
    }

    @Override
    protected void onInit(Bundle paramBundle) {
        ActionBar actionBar = getSupportActionBar();
        mActionBarFragmentTabHost = (ActionBarFragmentTabHost) findViewById(R.id.container);
        mActionBarFragmentTabHost.setup(actionBar, getSupportFragmentManager());
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        initTabHost();
    }

    private void initTabHost() {
        ActionBarTabPanel.TabBarItem tabBarItem1 = new ActionBarTabPanel.TabBarItem(TAB_QIUSHI_ID, UIHelper.getActionBarItemBackground(), UIHelper.getQiushiTabSelector());
        mActionBarFragmentTabHost.addTab(tabBarItem1, QiushiListFragment.class, v());

        ActionBarTabPanel.TabBarItem tabBarItem2 = new ActionBarTabPanel.TabBarItem(TAB_NEARBY_ID, UIHelper.getActionBarItemBackground(), UIHelper.getNearTabSelector());
        mActionBarFragmentTabHost.addTab(tabBarItem2, NearbyListFragment.class, null);

        ActionBarTabPanel.TabBarItem tabBarItem3 = new ActionBarTabPanel.TabBarItem(TAB_MESSAGE_ID, UIHelper.getActionBarItemBackground(), UIHelper.getMessageTabSelector());
        mActionBarFragmentTabHost.addTab(tabBarItem3, MessageListFragment.class, null);

        selected_tab = getIntent().getStringExtra(SELECTED_TAB_ID);
        if (TextUtils.isEmpty(selected_tab)) {
            selected_tab = TAB_QIUSHI_ID;
        }

        mActionBarFragmentTabHost.setOnTabClickListener(new ActionBarFragmentTabHost.OnTabClickListener() {
            @Override
            public void OnTabClick(String tab, Fragment fragment) {
                if (Build.VERSION.SDK_INT >= 11) {
                    getWindow().invalidatePanelMenu(0);
                }
                if (TAB_MESSAGE_ID.equalsIgnoreCase(tab)) {
                    mActionBarFragmentTabHost.hideTips(TAB_MESSAGE_ID);
                }
            }
        });
        mActionBarFragmentTabHost.setCurrentTab(selected_tab);
        mFragment = mActionBarFragmentTabHost.getTabById(selected_tab);
    }

    private Bundle v() {
        Bundle bundle = new Bundle();
        if (selected_page == 0) {
            selected_page = getIntent().getIntExtra(SELECTED_PAGE_ID, 0);
        }
        bundle.putInt("selected_item", selected_page);
        return bundle;
    }

    @Override
    protected String getCustomTitle() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //反馈
            case R.id.action_feedback:
                goFeedback();
                break;
            case R.id.action_setting:
                goSetting();
                break;
            //关于
            case R.id.action_about:
                goAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goFeedback() {
        Intent intent = new Intent(this, About.class);
        intent.putExtra("targetPage", "feedback");
        startActivity(intent);
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }

    private void goSetting() {
        Intent intent = new Intent(this, ActionBarUserSettingNavi.class);
        startActivity(intent);
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }

    private void goAbout() {
        Intent intent = new Intent(this, About.class);
        intent.putExtra("targetPage", "about");
        startActivity(intent);
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }
}
