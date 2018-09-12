package com.might.dwan.cashcalendar.apps;

import android.app.Application;

import com.might.dwan.cashcalendar.apps.modules.ContextModule;

/**
 * Created by Might on 24.08.2017.
 */

public class App extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getBaseContext()))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
