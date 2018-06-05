package com.might.dwan.cashcalendar.utils;

import android.widget.EditText;

/**
 * Created by Ilya on 05.06.2018.
 */

public class EditTextUtils {

    public static boolean isValid(EditText et) {
        return (et != null && et.toString() != null && !et.toString().trim().equals(""));
    }

    public static String getText(EditText et) {
        if (et == null) return "";
        return et.getText().toString().trim();
    }
}
