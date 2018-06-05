package com.might.dwan.cashcalendar.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Might on 24.08.2017.
 */

public class App extends Application {
    public static SharedPreferences sPreferenceManager;

    @Override public void onCreate() {
        super.onCreate();
        sPreferenceManager = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sPreferenceManager;
    }
}
