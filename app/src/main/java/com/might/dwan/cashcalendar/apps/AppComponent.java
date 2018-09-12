package com.might.dwan.cashcalendar.apps;

import android.content.Context;
import android.content.SharedPreferences;

import com.might.dwan.cashcalendar.apps.modules.ContextModule;
import com.might.dwan.cashcalendar.apps.modules.NetworkModule;
import com.might.dwan.cashcalendar.apps.modules.RxSchedulerModule;
import com.might.dwan.cashcalendar.apps.modules.StorageModule;
import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import dagger.Component;

/**
 * Created by Ilya on 05.06.2018.
 */
@AppScope
@Component(modules = {ContextModule.class, StorageModule.class, RxSchedulerModule.class, NetworkModule.class})
public interface AppComponent {

    PayCounterDB payCounterDb();

    RxSchedulers rxSchedulers();

    DBHelper dbHelper();

    SubcategoryDB provideSubcategoryDb();

    CategoryDB provideCategoryDb();

    StatisticsDB provideStatisticsDb();

    SharedPreferences provideSharedPreferences();

    @AppContext
    Context provideAppContext();

    @BaseContext
    Context provideBaseContext();
}
