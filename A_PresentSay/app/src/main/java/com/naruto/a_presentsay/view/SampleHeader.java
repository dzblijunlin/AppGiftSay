package com.naruto.a_presentsay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.naruto.a_presentsay.R;

/**
 * Created by dllo on 16/12/2.
 */

public class SampleHeader extends RelativeLayout {

    public SampleHeader(Context context) {
        super(context);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.item_gift_top, this);
    }
}