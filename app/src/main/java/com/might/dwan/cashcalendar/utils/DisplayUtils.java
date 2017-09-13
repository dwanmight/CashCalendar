package com.might.dwan.cashcalendar.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Might on 13.09.2017.
 */

public class DisplayUtils {

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
