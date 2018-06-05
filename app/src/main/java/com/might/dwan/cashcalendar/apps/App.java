package com.might.dwan.cashcalendar.apps;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Might on 24.08.2017.
 */

public class App extends Application {
    public static SharedPreferences sPreferenceManager;

    private AppComponent mAppComponent;

    @Override public void onCreate() {
        super.onCreate();
        sPreferenceManager = PreferenceManager.getDefaultSharedPreferences(this);
        mAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getBaseContext()))
                .build();
    }

    public static SharedPreferences getSharedPreferences() {
        return sPreferenceManager;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
