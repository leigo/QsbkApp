package com.leigo.qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.GuideActivity;
import com.leigo.qsbk.app.activity.MainActivity;

/**
 * Created by Administrator on 2014/8/15.
 */
public class SplashGroup extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Task(), 2000L);
    }

    private class Task implements Runnable {

        @Override
        public void run() {
            Intent intent = new Intent(SplashGroup.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }
}
