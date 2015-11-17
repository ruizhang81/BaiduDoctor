package com.baidu.doctor.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.baidu.doctor.R;


public class BodyViewWoManHead extends BodyView {

    public BodyViewWoManHead(Context context) {
        super(context);
    }

    public BodyViewWoManHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_body_woman_head, this, true);
    }


}
