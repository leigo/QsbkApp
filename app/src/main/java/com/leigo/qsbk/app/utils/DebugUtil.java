package com.leigo.qsbk.app.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2014/8/19.
 */
public class DebugUtil {

    public static boolean DEBUG = true;
    public static final String TAG = DebugUtil.class.getSimpleName();

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void error(String msg) {
        Log.e(TAG, msg);
    }

    public static void error(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
