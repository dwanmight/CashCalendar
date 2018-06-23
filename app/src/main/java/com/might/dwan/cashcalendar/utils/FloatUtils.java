package com.might.dwan.cashcalendar.utils;

import java.text.DecimalFormat;

public class FloatUtils {

    public static String get(float num){
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        return decimalFormat.format(num);
    }
}
