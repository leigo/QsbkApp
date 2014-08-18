package com.leigo.qsbk.app.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2014/8/16.
 */
public class ActionBarUserSettingNavi extends BaseActionBarActivity {
    @Override
    protected int getResource() {
        return R.layout.actionbar_activity_usersetting_navi;
    }

    @Override
    protected String getCustomTitle() {
        return "设置";
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setActionbarBackable();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
