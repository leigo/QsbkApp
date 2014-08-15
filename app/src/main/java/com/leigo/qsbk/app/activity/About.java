package com.leigo.qsbk.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.leigo.qsbk.app.Constants;
import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.activity.base.BaseActionBarActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2014/8/15.
 */
public class About extends BaseActionBarActivity {

    private static final String TAG = About.class.getSimpleName();

    WebView mWebView;

    private String data;
    private String targetPage;

    @Override
    protected void onInit(Bundle savedInstanceState) {
        setActionbarBackable();
        targetPage = getIntent().getStringExtra("targetPage");
        mWebView = (WebView) findViewById(R.id.about);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(About.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(About.this).setTitle("温馨提示：").setMessage(message).setPositiveButton(R.string.dialog_btn_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });
//        mWebView.addJavascriptInterface(, "stub");
        if (mWebView.getBackground() != null) {
            mWebView.getBackground().setAlpha(0);
        }
    }

    @Override
    protected String getCustomTitle() {
        Log.d(TAG, targetPage);
        if ("about".equals(targetPage)) {
            return getString(R.string.title_about);
        } else {
            return getString(R.string.title_feedback);
        }
    }

    @Override
    protected int getResource() {
        return R.layout.about;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("about".equals(targetPage)) {
            try {
                data = readData(getAssets().open("about.html"), "UTF-8");
                data = data.replace("#AppVersion#", Constants.versionName);
                mWebView.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "utf-8", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                data = readData(getAssets().open("feedback.html"), "UTF-8");
                data = data.replace("#PH_FEEDBACK_URL#", "http://m2.qiushibaike.com/feedback");
                data = data.replace("#PH_SOURCE#", "android_" + Constants.versionName);
                mWebView.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "utf-8", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param inputStream
     * @param encoding
     * @return 读取输入流数据
     * @throws Exception
     */
    public static String readData(InputStream inputStream, String encoding) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.close();
        inputStream.close();
        return new String(baos.toByteArray(), encoding);
    }
}
