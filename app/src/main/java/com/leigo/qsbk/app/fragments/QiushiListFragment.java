package com.leigo.qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.widget.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2014/8/16.
 */
public class QiushiListFragment extends BaseFragment {


    private static final String TAG = QiushiListFragment.class.getSimpleName();

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qiushi, container, false);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
        return view;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"图文", "纯文", "纯图", "最新", "精华"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

    }
}
