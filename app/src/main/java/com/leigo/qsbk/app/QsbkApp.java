package com.leigo.qsbk.app;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.leigo.qsbk.app.utils.DebugUtil;
import com.leigo.qsbk.app.utils.SharePreferenceUtils;
import com.leigo.qsbk.app.utils.UIHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Administrator on 2014/8/15.
 */
public class QsbkApp extends Application {

    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyPatterns";

    private static QsbkApp mInstance;
    public static Context mContext;

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;


    public static QsbkApp getInstance() {
        if (mInstance == null) {
            mInstance = new QsbkApp();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        getImageLoader();
    }

    public ImageLoader getImageLoader() {
        if (ImageLoader.getInstance().isInited()) {
            return ImageLoader.getInstance();
        }
        initImageLoader(this);
        return ImageLoader.getInstance();
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context, 12000, 40000))
                .tasksProcessingOrder(QueueProcessingType.FIFO);
        if (DebugUtil.DEBUG) {
            builder.writeDebugLogs();
        }
        ImageLoaderConfiguration config = builder.build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public DisplayImageOptions getAvatarDisplayOptions() {
        int resId;
        if (UIHelper.isNightTheme()) {
            resId = R.drawable.default_users_avatar_night;
        } else {
            resId = R.drawable.default_users_avatar;
        }
        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(resId)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public void gotoMarket(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=qsbk.app")));
            SharePreferenceUtils.setSharedPreferencesValue("isRated", "true");
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "感谢您的支持，我们会更加努力。", Toast.LENGTH_SHORT).show();
        }
    }
}
