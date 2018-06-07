package com.might.dwan.cashcalendar.apps.modules;

import android.content.Context;

import com.might.dwan.cashcalendar.apps.AppScope;

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
}
