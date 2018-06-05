package com.might.dwan.cashcalendar.ui.screens.counter_list.dagger;

import com.might.dwan.cashcalendar.apps.AppComponent;
import com.might.dwan.cashcalendar.ui.screens.counter_list.CountersFragment;

import dagger.Component;

/**
 * Created by Ilya on 05.06.2018.
 */

@CountersScope
@Component(dependencies = {AppComponent.class},modules = {CountersModule.class})
public interface CountersComponent {
    void inject(CountersFragment fragment);
}
