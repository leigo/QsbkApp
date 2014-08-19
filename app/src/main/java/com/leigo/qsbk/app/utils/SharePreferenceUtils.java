package com.leigo.qsbk.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.leigo.qsbk.app.QsbkApp;

/**
 * Created by Administrator on 2014/8/19.
 */
public class SharePreferenceUtils {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    private static SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getSharedPreferences(QsbkApp.mContext).edit();
        }
        return editor;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharedPreferences;
    }

    public static void setSharedPreferencesIntValue(String key, int value) {
        getEditor().putInt(key, value);
        getEditor().commit();
    }

    public static int getSharedPreferencesIntValue(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(QsbkApp.mContext);
        int value = 0;
        if (sharedPreferences != null) {
            value = sharedPreferences.getInt(key, 0);
        }
        return value;
    }

    public static void setSharedPreferencesValue(String key, String value) {
        getEditor().putString(key, value);
        getEditor().commit();
    }

    public static String getSharedPreferencesValue(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(QsbkApp.mContext);
        String value = "";
        if (sharedPreferences != null) {
            value = sharedPreferences.getString(key, "");
        }
        return value;
    }

    public static void remove(String key) {
        getEditor().remove(key).commit();
    }

}
