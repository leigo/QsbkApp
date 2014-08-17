package com.leigo.qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leigo.qsbk.app.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/16.
 */
public class ActionBarTabPanel extends HorizontalScrollView implements View.OnClickListener,
        Recyclable {

    public static final String TAB_UNKNOW = "UNKNOW";
    private static int mFadingEdgelength;
    private LinearLayout mLinearLayout;
    private OnTabClickListener mOnTabClickListener;
    private int height = -1;
    private int width = -1;
    private boolean isShow = true;
    private String tagName = "UNKNOW";
    private ArrayList<TabBarItem> tabBarItems = new ArrayList<TabBarItem>();

    public ActionBarTabPanel(Context context) {
        super(context);
        init();
    }

    public ActionBarTabPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActionBarTabPanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (mFadingEdgelength == 0) {
            mFadingEdgelength = getResources().getDimensionPixelOffset(R.dimen.profile_header_tab_fading_edge_length);
        }
    }

    public final void addTab(String tabId, int backgroundRes, int imageRes) {
        addTab(new TabBarItem(tabId, backgroundRes, imageRes));
    }

    public final void addTab(TabBarItem tabBarItem) {
        Resources resources = getResources();
        tabBarItem.frameLayout = new FrameLayout(getContext());
        tabBarItem.imageView = new ImageView(getContext());
        tabBarItem.imageView.setScaleType(ImageView.ScaleType.CENTER);
        tabBarItem.imageView.setTag(tabBarItem.getId());
        tabBarItem.imageView.setOnClickListener(this);

        Drawable backgroundDrawable = resources.getDrawable(tabBarItem.backgroundRes);
        Drawable imageDrawable = resources.getDrawable(tabBarItem.imageRes);

        tabBarItem.imageView.setBackgroundDrawable(backgroundDrawable);
        tabBarItem.imageView.setImageDrawable(imageDrawable);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(height, width);
        layoutParams.gravity = Gravity.CENTER;

        tabBarItem.frameLayout.addView(tabBarItem.imageView, layoutParams);

        tabBarItem.textView = new TextView(getContext());
        tabBarItem.textView.setVisibility(View.INVISIBLE);

        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(resources.getDimensionPixelSize(R.dimen.message_tip), resources.getDimensionPixelSize(R.dimen.message_tip));
        layoutParams1.gravity = Gravity.CENTER | Gravity.CENTER_VERTICAL;
        layoutParams1.topMargin = (resources.getDimensionPixelSize(R.dimen.actionbar_height) / 8);
        layoutParams1.rightMargin = (resources.getDimensionPixelSize(R.dimen.actionbar_navigation_item_width) / 8);
        tabBarItem.frameLayout.addView(tabBarItem.textView, layoutParams1);
        tabBarItems.add(tabBarItem);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mLinearLayout.addView(tabBarItem.frameLayout, layoutParams2);
    }

    public final String getSelectedTab() {
        return tagName;
    }

    public final void hideTips(String tips) {
        for (int i = 0; i < tabBarItems.size(); i++) {
            TabBarItem tabBarItem = tabBarItems.get(i);
            if (tips.equalsIgnoreCase(tabBarItem.tabId)) {
                tabBarItem.textView.setVisibility(View.INVISIBLE);
            }
        }
    }

    public final void initialFeature() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(true);
        setFadingEdgeLength(mFadingEdgelength);
        setFillViewport(true);
        setBackgroundDrawable(null);
        mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams layoutParams = generateDefaultLayoutParams();
        addView(mLinearLayout, layoutParams);
        height = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
        width = getResources().getDimensionPixelSize(R.dimen.actionbar_navigation_item_width);
    }


    @Override
    public void onClick(View v) {
        if (mOnTabClickListener == null) {
            return;
        }
        String tab = String.valueOf(v.getTag());
        setSelectedTab(tab);
        mOnTabClickListener.onTabClick(tab);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initialFeature();
    }

    @Override
    public void recycle() {
        mLinearLayout.removeAllViews();
        tabBarItems.clear();
        mOnTabClickListener = null;
    }

    public final void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.mOnTabClickListener = onTabClickListener;
    }

    public final void setSelectedTab(String tab) {
        for (int i = 0; i < tabBarItems.size(); i++) {
            TabBarItem tabBarItem = tabBarItems.get(i);
            if (tab.equalsIgnoreCase(tabBarItem.tabId)) {
                tagName = tab;
                tabBarItem.frameLayout.setSelected(true);
                if (isShow) {
                    tabBarItem.textView.setVisibility(View.INVISIBLE);
                }
            } else {
                tabBarItem.frameLayout.setSelected(false);
            }
        }
    }

    public final void setTips(String tab, String text) {
        for (int i = 0; i < tabBarItems.size(); i++) {
            TabBarItem tabBarItem = tabBarItems.get(i);
            if (tab.equalsIgnoreCase(tabBarItem.tabId)) {
                tabBarItem.textView.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(text)) {
                    tabBarItem.textView.setText(text);
                }
            }
        }
    }

    public final void setWillAutoCancelTipsWhenSelected(boolean flag) {
        isShow = flag;
    }

    public static abstract interface OnTabClickListener {
        public abstract void onTabClick(String tab);
    }

    public static final class TabBarItem {
        private String tabId;
        private int backgroundRes;
        private int imageRes;
        private ImageView imageView;
        private TextView textView;
        private FrameLayout frameLayout;


        public TabBarItem(String tabId, int backgroundRes, int imageRes) {
            this.tabId = tabId;
            this.backgroundRes = backgroundRes;
            this.imageRes = imageRes;
        }

        public String getId() {
            return tabId;
        }
    }
}
