package com.might.dwan.cashcalendar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Might on 12.09.2017.
 */

public class DateUtils {

    public static String stampToYMD(long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        return sdf.format(new Date(stamp));
    }

    public static String stampToYMDHMS(long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        return sdf.format(new Date(stamp));
    }
}
