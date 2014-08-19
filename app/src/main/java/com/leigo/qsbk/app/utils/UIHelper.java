package com.leigo.qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.leigo.qsbk.app.QsbkApp;
import com.leigo.qsbk.app.R;

/**
 * Created by Administrator on 2014/8/16.
 */
public class UIHelper {

    private static Boolean isNightTheme = null;
    private static String theme = null;
    private static DisplayMetrics metrics;
    private static final int[] one_profile_bg1 = {R.drawable.one_profile_bg1};
    private static final int[] one_profile_bg1_dark = {R.drawable.one_profile_bg1_dark};

    /**
     * 根据手机分辨率从 dp 单位 转成 px(像素)
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int getActionBarItemBackground() {
        if (isNightTheme()) {
            return R.drawable.ic_action_bar_item_bg_selector_dark;
        }
        return R.drawable.ic_action_bar_item_bg_selector;
    }

    public static int getColor(int resId) {
        return QsbkApp.mContext.getResources().getColor(resId);
    }

    public static ColorStateList getColorStateList(int resId) {
        return QsbkApp.mContext.getResources().getColorStateList(resId);
    }

    public static int getDefaultAdImageTileBackgroud() {
        if (isNightTheme()) {
            return R.drawable.bg_dark_ad;
        }
        return R.drawable.bg_light_ad;
    }

    public static int getDefaultAvatar() {
        if (isNightTheme()) {
            return R.drawable.default_users_avatar_night;
        }
        return R.drawable.default_users_avatar;
    }

    public static int getDefaultImageTileBackground() {
        if (isNightTheme()) {
            return R.drawable.bg_dark_tile;
        }
        return R.drawable.bg_light_tile;
    }

    public static int getDefaultOneProfileCover() {
        if (isNightTheme()) {
            return one_profile_bg1[new java.util.Random().nextInt(one_profile_bg1.length)];
        }
        return one_profile_bg1_dark[new java.util.Random().nextInt(one_profile_bg1_dark.length)];
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        if (metrics == null) {
            metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        return metrics;
    }

    public static Drawable getDrawable(int resId) {
        return QsbkApp.mContext.getResources().getDrawable(resId);
    }

    public static int getFemale() {
        if (isNightTheme()) {
            return R.drawable.one_profile_female_left_dark;
        }
        return R.drawable.one_profile_female_left;
    }

    public static int getFemaleBackground() {
        if (isNightTheme()) {
            return R.color.transparent;
        }
        return R.drawable.one_profile_gender_female;
    }

    public static int getMale() {
        if (isNightTheme()) {
            return R.drawable.one_profile_male_left_dark;
        }
        return R.drawable.one_profile_male_left;
    }

    public static int getMaleBackground() {
        if (isNightTheme()) {
            return R.color.transparent;
        }
        return R.drawable.one_profile_gender_male;
    }

    public static int getMessageTabSelector() {
        if (isNightTheme()) {
            return R.drawable.ic_message_selector_dark;
        }
        return R.drawable.ic_message_selector;
    }

    public static int getNearTabSelector() {
        if (isNightTheme()) {
            return R.drawable.ic_nearby_selector_dark;
        }
        return R.drawable.ic_nearby_selector;
    }

    public static int getNewMessageTips() {
        if (isNightTheme()) {
            return R.drawable.message_tips_dark;
        }
        return R.drawable.message_tips;
    }

    public static int getOneProfileEditSelector() {
        if (isNightTheme()) {
            return R.drawable.my_profile_edit_normal_dark;
        }
        return R.drawable.my_profile_edit_normal;
    }

    public static int getProfileSelectedTabSelector() {
        if (isNightTheme()) {
            return R.drawable.profile_tab_active_item_selector_dark;
        }
        return R.drawable.profile_tab_active_item_selector;
    }

    public static int getProfileUnselectedTabSelector() {
        if (isNightTheme()) {
            return R.drawable.profile_tab_inactive_item_selector_dark;
        }
        return R.drawable.profile_tab_inactive_item_selector;
    }

    public static int getQiushiTabSelector() {
        if (isNightTheme()) {
            return R.drawable.ic_qiushi_selector_dark;
        }
        return R.drawable.ic_qiushi_selector;
    }

//    public static int getSelectedTabTextColor() {
//        if (isNightTheme()) {
//            return -4359140;
//        }
//        return -17899;
//    }

    public static int getSexTexColor(Resources paramResources, String paramString) {
        if (isNightTheme()) {
            if ("M".equalsIgnoreCase(paramString)) {
                return paramResources.getColor(R.color.male_dark);
            }
            return paramResources.getColor(R.color.female_dark);
        }
        return paramResources.getColor(R.color.g_txt_big);
    }

    public static ForegroundColorSpan getSupportAndCommentTextColor() {
        if (isNightTheme()) {
            return new ForegroundColorSpan(Color.parseColor("#6a6c7e"));
        }
        return new ForegroundColorSpan(Color.parseColor("#63625e"));
    }


    public static String getTheme() {
        if (theme == null) {
            theme = SharePreferenceUtils.getSharedPreferencesValue(Theme.THEME_ID);
        }
        if (Theme.THEME_NIGHT.equalsIgnoreCase(theme)) {
            return Theme.THEME_NIGHT;
        }
        return Theme.THEME_DAY;
    }

    public static boolean isNightTheme() {
        if (isNightTheme != null) {
            return isNightTheme;
        }
        theme = SharePreferenceUtils.getSharedPreferencesValue(Theme.THEME_ID);
        if (TextUtils.isEmpty(theme) || Theme.THEME_DAY.equalsIgnoreCase(theme)) {
            isNightTheme = false;
        } else {
            isNightTheme = true;
        }
        return isNightTheme;
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideKeyboard(Activity activity) {
        hideKeyboard(activity, activity.getWindow());
    }

    public static void hideKeyboard(Activity activity, Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
        }
    }

    public static abstract interface Theme {
        public static final String THEME_DAY = "day";
        public static final String THEME_ID = "themeid";
        public static final String THEME_NIGHT = "night";
    }
}
