package com.might.dwan.cashcalendar.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Might on 13.09.2017.
 */

public class DisplayUtils {

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.densityDpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

    public static float pxToDpi(Context c, int px) {
        Resources res = c.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, res.getDisplayMetrics());
    }
}
