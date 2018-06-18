package com.might.dwan.cashcalendar.apps.modules;

import android.content.Context;

import com.might.dwan.cashcalendar.apps.AppScope;
import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;

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

    @AppScope
    @Provides SubcategoryDB provideSubcategoryDb() {return new SubcategoryDB();}

    @AppScope
    @Provides CategoryDB provideCategoryDb() {return new CategoryDB();}

    @AppScope
    @Provides StatisticsDB provideStatisticsDb() {return new StatisticsDB();}
}
