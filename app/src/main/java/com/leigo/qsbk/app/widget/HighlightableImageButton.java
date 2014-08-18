package com.leigo.qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.leigo.qsbk.app.R;

/**
 * Created by Administrator on 2014/8/18.
 */
public class HighlightableImageButton extends ImageButton {

    private static final int[] additionalState =

            {
                    R.attr.state_highlighted
            };

    private boolean highlighted;

    public HighlightableImageButton(Context context) {
        super(context);
    }

    public HighlightableImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HighlightableImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        if (highlighted) {
            int[] baseState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(baseState, additionalState);
            return baseState;
        }
        return super.onCreateDrawableState(extraSpace);
    }

    public void setHighlighted(boolean highlighted) {
        if (this.highlighted != highlighted) {
            this.highlighted = highlighted;
            refreshDrawableState();
        }
    }
}
