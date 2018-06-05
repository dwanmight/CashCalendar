package com.might.dwan.cashcalendar.apps;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;

import dagger.Component;

/**
 * Created by Ilya on 05.06.2018.
 */

@AppScope
@Component(modules = {ContextModule.class, DatabaseModule.class})
public interface AppComponent {

    PayCounterDB payCounterDb();

    DBHelper dbHelper();
}
