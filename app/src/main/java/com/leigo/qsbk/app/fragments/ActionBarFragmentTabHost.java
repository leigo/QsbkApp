package com.leigo.qsbk.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.widget.ActionBarTabPanel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/16.
 */
public class ActionBarFragmentTabHost extends FrameLayout implements ActionBarTabPanel.OnTabClickListener {

    private ActionBar mActionBar;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private boolean mAttached;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private Context mContext;
    private ActionBarTabPanel mActionBarPanel;
    private OnTabClickListener mOnTabClickListener;

    public ActionBarFragmentTabHost(Context context) {
        super(context);
        this.mContext = context;
    }

    public ActionBarFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ActionBarFragmentTabHost(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    private FragmentTransaction doTabChanged(String tabId, FragmentTransaction ft) {
        TabInfo newTab = null;
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tab = mTabs.get(i);
            if (tab.tag.equals(tabId)) {
                newTab = tab;
            }
        }
        if (newTab == null) {
            throw new IllegalStateException("No tab known for tag " + tabId);
        }
        if (mLastTab != newTab) {
            if (ft == null) {
                ft = mFragmentManager.beginTransaction();
            }
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
//                  ft.detach(mLastTab.fragment);
                    ft.hide(mLastTab.fragment);
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(mContext,
                            newTab.clss.getName(), newTab.args);
                    ft.add(getId(), newTab.fragment, newTab.tag);
                } else {
//                  ft.attach(newTab.fragment);
                    ft.show(newTab.fragment);
                }
            }

            mLastTab = newTab;
        }
        return ft;
    }

    private void init() {
        mActionBarPanel = ((ActionBarTabPanel) LayoutInflater.from(mContext).inflate(R.layout.action_bar_tab_panel, null));
        mActionBarPanel.setOnTabClickListener(this);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setCustomView(mActionBarPanel, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT));
    }

    public void addTab(ActionBarTabPanel.TabBarItem tabBarItem, Class<?> clss, Bundle args) {
        if ((mActionBarPanel == null) || (mActionBar == null) || (mFragmentManager == null)) {
            throw new IllegalStateException("Did you forget to call 'public void setup(ActionBar ab, FragmentManager fm).");
        }
        String tag = tabBarItem.getId();

        TabInfo info = new TabInfo(tag, clss, args);

        if (mAttached) {
            // If we are already attached to the window, then check to make
            // sure this tab's fragment is inactive if it exists. This shouldn't
            // normally happen.
            info.fragment = mFragmentManager.findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
//              ft.detach(info.fragment);
                ft.hide(info.fragment);
                ft.commit();
            }
        }

        mTabs.add(info);
        mActionBarPanel.addTab(tabBarItem);
    }

    public String getCurrentTab() {
        return mActionBarPanel.getSelectedTab();
    }

    public Fragment getTabById(String tabId) {
        if ((mLastTab == null) || (mLastTab.fragment == null)) {
            FragmentTransaction fragmentTransaction = doTabChanged(tabId, null);
            if (fragmentTransaction != null) {
                fragmentTransaction.commit();
            }
        }
        return mLastTab.fragment;
    }

    public void hideTips(String tips) {
        mActionBarPanel.hideTips(tips);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        String currentTab = getCurrentTab();

        // Go through all tabs and make sure their fragments match
        // the correct state.
        FragmentTransaction ft = null;
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tab = mTabs.get(i);
            tab.fragment = mFragmentManager.findFragmentByTag(tab.tag);
//          if (tab.fragment != null && !tab.fragment.isDetached()) {
            if (tab.fragment != null) {
                if (tab.tag.equals(currentTab)) {
                    // The fragment for this tab is already there and
                    // active, and it is what we really want to have
                    // as the current tab. Nothing to do.
                    mLastTab = tab;
                } else {
                    // This fragment was restored in the active state,
                    // but is not the current tab. Deactivate it.
                    if (ft == null) {
                        ft = mFragmentManager.beginTransaction();
                    }
//                  ft.detach(tab.fragment);
                    ft.hide(tab.fragment);
                }
            }
        }

        // We are now ready to go. Make sure we are switched to the
        // correct tab.
        mAttached = true;
        ft = doTabChanged(currentTab, ft);
        if (ft != null) {
            ft.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttached = false;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setCurrentTab(ss.curTab);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.curTab = getCurrentTab();
        return ss;
    }

    @Override
    public void onTabClick(String tab) {
        if (mAttached) {
            FragmentTransaction fragmentTransaction = doTabChanged(tab, null);
            if (fragmentTransaction != null) {
                fragmentTransaction.commit();
            }
        }

        if (mOnTabClickListener != null) {
            mOnTabClickListener.OnTabClick(tab, getTabById(tab));
        }
    }

    public void setCurrentTab(String tab) {
        mActionBarPanel.setSelectedTab(tab);
    }

    public void setOnTabClickListener(ActionBarFragmentTabHost.OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    public void setTips(String tab, String text) {
        mActionBarPanel.setTips(tab, text);
    }

    public void setup(ActionBar actionBar, FragmentManager fragmentManager) {
        if (getId() < 0) {
            setId(R.id.container);
        }
        mFragmentManager = fragmentManager;
        mActionBar = actionBar;
        init();
    }

    public static abstract interface OnTabClickListener {
        public abstract void OnTabClick(String tab, Fragment fragment);
    }

    static class SavedState extends BaseSavedState {

        String curTab;

        public SavedState(Parcel source) {
            super(source);
            curTab = source.readString();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(curTab);
        }

        @Override
        public String toString() {
            return "ActionBarFragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + curTab + "}";
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;

        TabInfo(String _tag, Class<?> _clss, Bundle _args) {
            tag = _tag;
            clss = _clss;
            args = _args;
        }
    }
}
