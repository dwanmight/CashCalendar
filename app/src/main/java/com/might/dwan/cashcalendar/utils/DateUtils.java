package com.might.dwan.cashcalendar.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Might on 12.09.2017.
 */

public class DateUtils {

    public static String stampToYMD(long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        return sdf.format(new Date(stamp));
    }



    /**
     * Get current month name
     *
     * @return month name
     * @see #stampToMonth(long)
     */
    public static String stampToMonth() {
        return stampToMonth(System.currentTimeMillis());
    }

    public static String getMonthNameFromCurrent(int monthDiff) {
        return stampToMonth(getMonthStamp(monthDiff));
    }

    /**
     * Get month name from timestamp
     *
     * @return month name
     */
    public static String stampToMonth(long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM", Locale.US);
        return sdf.format(new Date(stamp));
    }


    public static String stampToYMDHMS(long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        return sdf.format(new Date(stamp));
    }



    public static long parseTimeStampToUnix(long timestamp) {
        return parseTimeStampToUnix(String.valueOf(timestamp));
    }

    public static long parseTimeStampToUnix(String timestamp) {
        return Long.parseLong(timestamp) / 1000L;
    }

    public static long parseTimeStampFromUnix(String timestamp) {
        return Long.parseLong(timestamp) * 1000L;
    }


    public static long getMonthStamp(int monthDiff) {
        return getPrevMonthCalendar(monthDiff).getTimeInMillis();
    }


    public static long getPrevMonthStamp() {
        return getPrevMonthCalendar(-1).getTimeInMillis();
    }

    public static Calendar getPrevMonthCalendar(int monthDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, monthDiff);
        return calendar;
    }
}
