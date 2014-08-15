package com.leigo.qsbk.app.activity.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2014/8/15.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity {

    private View view;

    protected abstract int getResource();

    protected abstract String getCustomTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        view = LayoutInflater.from(this).inflate(getResource(), null);
        setContentView(view);

        if (!TextUtils.isEmpty(getCustomTitle())) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(getCustomTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionbarBackable() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
}
