package com.might.dwan.cashcalendar.apps;

import android.content.Context;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class DatabaseModule {


    @AppScope
    @Provides DBHelper provideDatabase(Context c) {
        return new DBHelper(c);
    }

    @AppScope
    @Provides PayCounterDB providePayCounterDb() {
        return new PayCounterDB();
    }
}
