package com.ewisnor.randomur.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by evan on 2015-01-04.
 */
public class ZoomableImageView extends ImageView implements View.OnTouchListener {

    public ZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
