package com.might.dwan.cashcalendar.apps;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.might.dwan.cashcalendar.apps.modules.ContextModule;

/**
 * Created by Might on 24.08.2017.
 */

public class App extends Application {
    public static SharedPreferences sPreferenceManager;

    private static AppComponent sAppComponent;

    @Override public void onCreate() {
        super.onCreate();
        sPreferenceManager = PreferenceManager.getDefaultSharedPreferences(this);
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getBaseContext()))
                .build();
    }

    public static SharedPreferences getSharedPreferences() {
        return sPreferenceManager;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
