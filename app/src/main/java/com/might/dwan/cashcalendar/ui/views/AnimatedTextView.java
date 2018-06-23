package com.might.dwan.cashcalendar.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;

import com.might.dwan.cashcalendar.utils.FloatUtils;

public class AnimatedTextView extends android.support.v7.widget.AppCompatTextView {
    public AnimatedTextView(Context context) {
        super(context);
    }

    public AnimatedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAnimatedValue(float value) {
        animateAmount(value);
    }

    private void animateAmount(float value) {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.setDuration(300);
        anim.addUpdateListener(
                it -> this.setText(FloatUtils.get(value * (float) it.getAnimatedValue())));
        anim.start();
    }
}
