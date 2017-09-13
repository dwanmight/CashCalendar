package com.might.dwan.cashcalendar.data.manager;

import android.content.Context;

import com.might.dwan.cashcalendar.data.preferences.Preferences;

/**
 * Created by Might on 24.08.2017.
 */

public class PreferencesManager {
    private static PreferencesManager sPreferenceManager;
    private Context mContext;
    private Preferences mPreferences;

    public static PreferencesManager get(Context context) {
        if (sPreferenceManager == null)
            sPreferenceManager = new PreferencesManager(context.getApplicationContext());
        return sPreferenceManager;
    }

    private PreferencesManager(Context context) {
        mContext = context;
        mPreferences=new Preferences();
    }

    public Preferences getPreferences() {
        return mPreferences;
    }
}
