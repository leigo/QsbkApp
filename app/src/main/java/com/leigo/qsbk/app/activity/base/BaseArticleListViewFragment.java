package com.leigo.qsbk.app.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leigo.qsbk.app.adapter.ArticleAdapter;

/**
 * Created by Administrator on 2014/8/18.
 */
public class BaseArticleListViewFragment extends Fragment {

    private static final String TAG = BaseArticleListViewFragment.class.getSimpleName();
    private ArticleAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
