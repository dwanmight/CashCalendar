package com.might.dwan.cashcalendar.ui.screens.statistics.dagger;

import com.might.dwan.cashcalendar.apps.AppComponent;
import com.might.dwan.cashcalendar.ui.screens.statistics.StatisticsFragment;

import dagger.Component;

/**
 * Created by ilya on 13.06.2018.
 */

@StatisticsScope
@Component(modules = {StatisticsModule.class}, dependencies = {AppComponent.class})
public interface StatisticsComponent {

    void inject(StatisticsFragment fragment);
}
