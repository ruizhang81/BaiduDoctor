package com.baidu.doctor.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.baidu.doctor.R;


public class BodyViewManHead extends BodyView {

    public BodyViewManHead(Context context) {
        super(context);
    }

    public BodyViewManHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_body_man_head, this, true);
    }


}
