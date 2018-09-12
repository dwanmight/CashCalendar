package com.might.dwan.cashcalendar.apps.modules;

import android.content.Context;

import com.might.dwan.cashcalendar.apps.AppContext;
import com.might.dwan.cashcalendar.apps.AppScope;
import com.might.dwan.cashcalendar.apps.BaseContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @BaseContext
    @AppScope
    @Provides
    Context provideContext() {
        return mContext;
    }

    @AppContext
    @AppScope
    @Provides
    Context provideAppContext() {
        return mContext.getApplicationContext();
    }
}
