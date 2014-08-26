package com.leigo.qsbk.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.group.SplashGroup;

/**
 * Created by Administrator on 2014/8/26.
 */
public class DeviceUtils {

    public static void addShortcut(Context context) {
        if (Build.MODEL.startsWith("MI") || context.getSharedPreferences("qiushibaike", 0).getBoolean("qiushibaike", false)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CREATE_SHORTCUT);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_launcher));
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, R.string.app_name);
        intent.putExtra("duplicate", false);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.setClass(context, SplashGroup.class);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, mainIntent);
        context.sendBroadcast(intent);
        SharedPreferences.Editor editor = context.getSharedPreferences("qiushibaike", Context.MODE_PRIVATE).edit();
        editor.putBoolean("qiushibaike", true);
        editor.commit();
    }

    public static String getSDPath() {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().toString();
        }
        return path;
    }
}
