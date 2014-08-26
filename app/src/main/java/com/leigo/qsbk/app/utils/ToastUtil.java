package com.leigo.qsbk.app.utils;

import android.widget.Toast;

import com.leigo.qsbk.app.QsbkApp;

/**
 * Created by Administrator on 2014/8/26.
 */
public class ToastUtil {

    public static void Long(int resId) {
        Toast.makeText(QsbkApp.mContext, resId, Toast.LENGTH_LONG).show();
    }

    public static void Long(String text) {
        Toast.makeText(QsbkApp.mContext, text, Toast.LENGTH_LONG).show();
    }

    public static void Short(int resId) {
        Toast.makeText(QsbkApp.mContext, resId, Toast.LENGTH_SHORT).show();
    }

    public static void Short(String text) {
        Toast.makeText(QsbkApp.mContext, text, Toast.LENGTH_SHORT).show();
    }
}
