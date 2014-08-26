package com.leigo.qsbk.app.activity;

import android.os.Bundle;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2014/8/26.
 */
public class ActionBarLoginActivity extends BaseActionBarActivity{
    @Override
    protected int getResource() {
        return R.layout.actionbar_activity_login;
    }

    @Override
    protected String getCustomTitle() {
        return null;
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }
}
