package com.might.dwan.cashcalendar.apps;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @AppScope
    @Provides Context provideContext() {
        return mContext;
    }

    @AppScope
    @Provides Context provideAppContext() {
        return mContext.getApplicationContext();
    }
}
