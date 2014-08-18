package com.leigo.qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leigo.qsbk.app.R;

/**
 * Created by Administrator on 2014/8/16.
 */
public class SettingItem extends RelativeLayout {

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.setting_item, this, true);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItem, 0, 0);
        String mainTitle = typedArray.getString(R.styleable.SettingItem_mainTitle);
        String subTitle = typedArray.getString(R.styleable.SettingItem_subTitle);
        boolean showCheckbox = typedArray.getBoolean(R.styleable.SettingItem_showCheckbox, false);
        boolean showDivide = typedArray.getBoolean(R.styleable.SettingItem_showDivide, true);
        setTitle(mainTitle);
        if (!TextUtils.isEmpty(subTitle)) {
            setSubTitle(subTitle);
        } else {
            getSubTitle().setVisibility(View.GONE);
        }

        if (showCheckbox) {
            getCheckBox().setVisibility(View.VISIBLE);
        } else {
            getCheckBox().setVisibility(View.GONE);
        }

        if (showDivide) {
            getDivideLine().setVisibility(View.VISIBLE);
        } else {
            getDivideLine().setVisibility(View.GONE);
        }
    }

    private TextView getTitle() {
        return (TextView) findViewById(R.id.title);
    }

    private TextView getSubTitle() {
        return (TextView) findViewById(R.id.sub_title);
    }

    private CheckBox getCheckBox() {
        return (CheckBox) findViewById(R.id.checkbox);
    }

    private ImageView getDivideLine() {
        return (ImageView) findViewById(R.id.divide_line);
    }

    public void setTitle(String text) {
        getTitle().setText(text);
    }

    public void setSubTitle(String text) {
        getSubTitle().setText(text);
    }

    public void setChecked(boolean checked) {
        getCheckBox().setChecked(checked);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        getCheckBox().setOnCheckedChangeListener(onCheckedChangeListener);
    }


}
