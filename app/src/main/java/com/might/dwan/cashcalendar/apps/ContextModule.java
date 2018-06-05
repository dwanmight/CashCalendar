package com.might.dwan.cashcalendar.apps;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class ContextModule {
    Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @AppScope
    @Provides Context provideContext() {
        return mContext;
    }

    //    @AppContext
    //    @AppScope
    //    @Provides Context provideAppContext() {
    //        return mContext.getApplicationContext();
    //    }
}
