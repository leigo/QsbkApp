package com.leigo.qsbk.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.leigo.qsbk.app.R;

/**
 * Created by Administrator on 2014/8/15.
 */
public class GuideActivity extends FragmentActivity implements View.OnClickListener {

    private ImageView close;
    private ImageButton known;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        close = (ImageView) findViewById(R.id.close);
        known = (ImageButton) findViewById(R.id.known);
        close.setOnClickListener(this);
        known.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.known:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }
    }
}
