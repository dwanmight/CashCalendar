package com.might.dwan.cashcalendar.apps;

import com.might.dwan.cashcalendar.apps.modules.ContextModule;
import com.might.dwan.cashcalendar.apps.modules.DatabaseModule;
import com.might.dwan.cashcalendar.apps.modules.RxSchedulerModule;
import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import dagger.Component;

/**
 * Created by Ilya on 05.06.2018.
 */

@AppScope
@Component(modules = {ContextModule.class, DatabaseModule.class, RxSchedulerModule.class})
public interface AppComponent {

    PayCounterDB payCounterDb();

    RxSchedulers rxSchedulers();

    DBHelper dbHelper();

    SubcategoryDB provideSubcategoryDb();

    CategoryDB provideCategoryDb();
}
