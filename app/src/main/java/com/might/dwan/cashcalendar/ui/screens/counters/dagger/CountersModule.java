package com.might.dwan.cashcalendar.ui.screens.counters.dagger;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.counters.core.CountersModel;
import com.might.dwan.cashcalendar.ui.screens.counters.core.CountersPresenter;
import com.might.dwan.cashcalendar.ui.screens.counters.core.CountersView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class CountersModule {

    private CountersFragment fragment;

    public CountersModule(CountersFragment fragment) {
        this.fragment = fragment;
    }

    @CountersScope
    @Provides CountersModel provideCountersModel(PayCounterDB payDb, DBHelper db) {
        return new CountersModel(fragment, payDb, db);
    }

    @CountersScope
    @Provides CountersPresenter providePresenter(CountersModel model, CountersView view) {
        return new CountersPresenter(model, view);
    }

    @CountersScope
    @Provides CountersView provideCountersView() {
        return new CountersView(fragment);
    }
}
