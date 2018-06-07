package com.might.dwan.cashcalendar.apps.modules;

import com.might.dwan.cashcalendar.utils.rx.AppRxSchedulers;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 06.06.2018.
 */

@Module
public class RxSchedulerModule {

    @Provides RxSchedulers provideRxSchedulr() {
        return new AppRxSchedulers();
    }
}
