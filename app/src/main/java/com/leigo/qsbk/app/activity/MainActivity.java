package com.leigo.qsbk.app.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.base.BaseActionBarActivity;


public class MainActivity extends BaseActionBarActivity {

    @Override
    protected int getResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInit(Bundle paramBundle) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
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
            case R.id.action_feedback:
                goFeedback();
                break;
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
        overridePendingTransition(R.anim.fade_in_up, R.anim.still_when_up);
    }

    private void goAbout() {
        Intent intent = new Intent(this, About.class);
        intent.putExtra("targetPage", "about");
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_up, R.anim.still_when_up);
    }
}
