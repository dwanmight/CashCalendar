package com.might.dwan.cashcalendar.data.db;

public class DBDateUtils {

    public static String getPrevMonthStart() {
        return " strftime('%s', timestamp, 'unixepoch', 'start of month', '-1 month' ) ";
    }

    public static String getPrevMonthEnd() {
        return " strftime('%s', timestamp, 'unixepoch', 'start of month', '-1 day' ) ";
    }



    public static String getCurrentMonthStart() {
        return " strftime('%s', timestamp, 'unixepoch', 'start of month' ) ";
    }

    public static String getCurrentMonthEnd() {
        return " strftime('%s', timestamp, 'unixepoch', 'start of month', '+1 month', '-1 day' ) ";
    }
}
