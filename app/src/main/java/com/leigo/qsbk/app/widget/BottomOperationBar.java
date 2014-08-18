package com.leigo.qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leigo.qsbk.app.R;

/**
 * Created by Administrator on 2014/8/18.
 */
public class BottomOperationBar extends RelativeLayout {

    private ViewGroup mButton1;
    private ViewGroup mButton2;
    private ViewGroup mButton3;
    private View mDivide1;
    private View mDivide2;

    public BottomOperationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_bottom_operation_bar, this, true);
        initFromAttributes(context, attrs);
    }


    private void initFromAttributes(Context context, AttributeSet attrs) {
        mButton1 = (ViewGroup) findViewById(R.id.button1);
        mButton2 = (ViewGroup) findViewById(R.id.button2);
        mButton3 = (ViewGroup) findViewById(R.id.button3);
        mDivide1 = findViewById(R.id.divide1);
        mDivide2 = findViewById(R.id.divide2);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomOperationBar);
        String txt1 = a.getString(R.styleable.BottomOperationBar_txt1);
        if (!TextUtils.isEmpty(txt1)) {
            setTxt(mButton1, txt1);
        }

        Drawable icon1 = a.getDrawable(R.styleable.BottomOperationBar_icon1);
        if (icon1 != null) {
            setIcon(mButton1, icon1);
        }

        String txt2 = a.getString(R.styleable.BottomOperationBar_txt2);
        if (!TextUtils.isEmpty(txt2)) {
            setTxt(mButton2, txt2);
            mButton2.setVisibility(View.VISIBLE);
            mDivide1.setVisibility(View.VISIBLE);
        }

        Drawable icon2 = a.getDrawable(R.styleable.BottomOperationBar_icon2);
        if (icon2 != null) {
            setIcon(mButton2, icon2);
        }

        String txt3 = a.getString(R.styleable.BottomOperationBar_txt3);
        if (!TextUtils.isEmpty(txt3)) {
            setTxt(mButton3, txt3);
            mButton3.setVisibility(View.VISIBLE);
            mDivide2.setVisibility(View.VISIBLE);
        }

        Drawable icon3 = a.getDrawable(R.styleable.BottomOperationBar_icon3);
        if (icon3 != null) {
            setIcon(mButton3, icon3);
        }
    }


    private BottomOperationBar setTxt(ViewGroup viewGroup, String text) {
        ((TextView) viewGroup.findViewById(R.id.id_txt)).setText(text);
        return this;
    }

    public BottomOperationBar setIcon(ViewGroup viewGroup, Drawable icon) {
        ((ImageView) viewGroup.findViewById(R.id.id_icon)).setImageDrawable(icon);
        return this;
    }

    public BottomOperationBar setBtnClickListener(View.OnClickListener onClickListener) {
        return setBtnClickListener(onClickListener, null, null);
    }

    public BottomOperationBar setBtnClickListener(View.OnClickListener onClickListener1, View.OnClickListener onClickListener2) {
        return setBtnClickListener(onClickListener1, onClickListener2, null);
    }

    public BottomOperationBar setBtnClickListener(View.OnClickListener onClickListener1, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3) {
        mButton1.setOnClickListener(onClickListener1);
        mButton2.setOnClickListener(onClickListener2);
        mButton3.setOnClickListener(onClickListener3);
        return this;
    }

}
