package com.might.dwan.cashcalendar.utils;

import android.os.Build;

public class BuildUtils {

    public static int LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;
    public static int MARSHMALLOW = Build.VERSION_CODES.M;
    public static int NOUGAT = Build.VERSION_CODES.N;
    public static int OREO = Build.VERSION_CODES.O;

    public static boolean isPostLollipop() {
        return getCurrentApi() >= LOLLIPOP;
    }

    private static int getCurrentApi() {
        return Build.VERSION.SDK_INT;
    }

}
