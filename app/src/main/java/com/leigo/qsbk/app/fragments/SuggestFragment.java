package com.leigo.qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.leigo.qsbk.app.Constants;
import com.leigo.qsbk.app.QsbkApp;
import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.adapter.ArticleAdapter;
import com.leigo.qsbk.app.model.Article;
import com.leigo.qsbk.app.model.QbBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2014/8/26.
 */
public class SuggestFragment extends Fragment {

    private static final String TAG = SuggestFragment.class.getSimpleName();

    private ArticleAdapter mAdapter;

    private ListView xListView;

    public static SuggestFragment newInstance() {
        SuggestFragment f = new SuggestFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xlistview, null);

        xListView = (ListView) view.findViewById(R.id.xListView);
        initData();
        mAdapter = new ArticleAdapter(getActivity());
        return view;
    }

    private void initData() {
        JsonObjectRequest req = new JsonObjectRequest(Constants.SUGGEST, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                QbBean qbBean = gson.fromJson(response.toString(), QbBean.class);
                List<Article> articleList = qbBean.items;
                mAdapter.setList(articleList);
                xListView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        QsbkApp.getInstance().addToRequestQueue(req, TAG);
    }
}
