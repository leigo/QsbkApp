package com.leigo.qsbk.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2014/8/19.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    protected List<T> mList;

    protected Context mContext;

    protected LayoutInflater mInflater;

    protected ImageLoader mImageLoader;

    public DefaultAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.getInstance();
    }

    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    public long getItemId(int position) {
        return mList == null ? 0 : position;
    }

    public void setList(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public void add(T t) {
        if (mList != null) {
            mList.add(t);
            notifyDataSetChanged();
        }
    }

    public void addAll(List<T> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addAll(List<T> list, int position) {
        if (mList != null) {
            mList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (mList != null) {
            mList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(T t) {
        if (mList != null) {
            mList.remove(t);
            notifyDataSetChanged();
        }
    }

    public void removeAll() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

}
