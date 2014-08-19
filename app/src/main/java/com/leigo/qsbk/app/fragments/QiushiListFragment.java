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
import com.leigo.qsbk.app.model.ArticleListConfig;
import com.leigo.qsbk.app.widget.BottomOperationBar;
import com.leigo.qsbk.app.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/16.
 */
public class QiushiListFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private static final String TAG = QiushiListFragment.class.getSimpleName();

    private ArrayList<ArticleListConfig> mConfig = null;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private BottomOperationBar bottomOperationBar;
    private FragmentPagerAdapter adapter;


    public View getBottomView() {
        return bottomOperationBar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(mConfig);
    }

    private void init(ArrayList<ArticleListConfig> configs) {
        if (configs == null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qiushi, container, false);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new QiushiViewPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
        tabs.setOnPageChangeListener(this);

        bottomOperationBar = (BottomOperationBar) view.findViewById(R.id.id_bottom_bar);
        bottomOperationBar.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class QiushiViewPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"图文", "纯文", "纯图", "最新", "精华"};

        public QiushiViewPagerAdapter(FragmentManager fm) {
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
            if(position == 1) {
                return HotTextFragment.newInstance();
            }else {
                return SuperAwesomeCardFragment.newInstance(position);
            }
        }

    }
}
