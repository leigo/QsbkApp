package com.leigo.qsbk.app.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.leigo.qsbk.app.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            goAbout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goAbout() {
        Intent intent = new Intent(this, About.class);
        intent.putExtra("targetPage", "about");
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_up_in, R.anim.still_when_up);
    }
}
