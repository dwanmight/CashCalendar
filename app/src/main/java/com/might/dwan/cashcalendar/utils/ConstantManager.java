package com.might.dwan.cashcalendar.utils;

/**
 * Created by Might on 24.08.2017.
 */

public interface ConstantManager {

    String TAG="CASH_CALENDAR_APP";

    //Preferences
    String PREF_NICKNAME = "paycounter_nickname";

    //Intent Extras
    String EXTRA_MODE = "EXTRA_MODE";
    String EXTRA_ITEM = "EXTRA_ITEM";
    String EXTRA_STAMP = "EXTRA_STAMP";

    //REQUEST CODES
    int REQUEST_DATE_DIALOG = 10;

    int REQUEST_ACTIVITY_DETAIL = 20;
    int REQUEST_GALLERY_PICK = 21;
    int REQUEST_CAMERA=22;
}
