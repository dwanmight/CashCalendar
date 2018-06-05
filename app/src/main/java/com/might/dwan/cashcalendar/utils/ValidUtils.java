package com.might.dwan.cashcalendar.utils;

/**
 * Created by Ilya on 05.06.2018.
 */

public class ValidUtils {

    public static boolean isTextValid(String text) {
        return (text != null && !text.trim().equals(""));
    }
}
