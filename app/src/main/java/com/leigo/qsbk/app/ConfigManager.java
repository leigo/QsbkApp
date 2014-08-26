package com.leigo.qsbk.app;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2014/8/26.
 */
public class ConfigManager {

    public static String KEY_CHANNEL = "channel";
    public static String KEY_DEBUG = "debug";
    public static String KEY_PROMOTE = "promote_channels";
    public static String KEY_SHOW_GUIDE = "showGuide";
    private static ConfigManager configManager = null;
    private Properties properties = new Properties();

    private ConfigManager() {
        init();
    }

    private void init() {
        AssetManager assetManager = QsbkApp.mContext.getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("all_config");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    public String getConfig(String name) {
        return properties.getProperty(name);
    }

    public String getConfig(String name, String defaultValue) {
        return properties.getProperty(name, defaultValue);
    }

    public boolean isPromoteChannle() {
        String channel = getInstance().getConfig(KEY_CHANNEL, "1");
        String promote_channels = getInstance().getConfig(KEY_PROMOTE, "");
        if (!TextUtils.isEmpty(promote_channels)) {
            String[] promotes = promote_channels.split(",");
            for (int i = 0; i < promotes.length; i++) {
                if (channel.equals(promotes[i])) {
                    return true;
                }
            }
        }
        return false;
    }

}
