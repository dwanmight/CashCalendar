package com.might.dwan.cashcalendar.ui.screens.statistics;

import android.os.Bundle;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.apps.App;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.ui.screens.statistics.core.StatisticsPresenter;
import com.might.dwan.cashcalendar.ui.screens.statistics.dagger.DaggerStatisticsComponent;
import com.might.dwan.cashcalendar.ui.screens.statistics.dagger.StatisticsModule;

import javax.inject.Inject;

/**
 * Created by Ilya on 07.06.2018.
 */

public class StatisticsFragment extends BaseFragment {

    @Inject StatisticsPresenter mPresenter;

    @Override public int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override public void setupData(Bundle state) {
        DaggerStatisticsComponent.builder()
                .appComponent(App.getAppComponent())
                .statisticsModule(new StatisticsModule(this))
                .build()
                .inject(this);
    }
}
