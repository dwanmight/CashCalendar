package com.might.dwan.cashcalendar.ui.screens.statistics.dagger;

import com.might.dwan.cashcalendar.ui.screens.statistics.StatisticsFragment;
import com.might.dwan.cashcalendar.ui.screens.statistics.core.StatisticsModel;
import com.might.dwan.cashcalendar.ui.screens.statistics.core.StatisticsPresenter;
import com.might.dwan.cashcalendar.ui.screens.statistics.core.StatisticsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ilya on 13.06.2018.
 */

@Module
public class StatisticsModule {

    private StatisticsFragment mFragment;

    public StatisticsModule(StatisticsFragment fragment) {
        mFragment = fragment;
    }

    @StatisticsScope
    @Provides
    StatisticsView provideView() {
        return new StatisticsView(mFragment);
    }

    @StatisticsScope
    @Provides
    StatisticsPresenter providePresenter(StatisticsView v) {
        return new StatisticsPresenter(v);
    }

    @StatisticsScope
    @Provides
    StatisticsModel provideModel() {
        return new StatisticsModel();
    }

}
