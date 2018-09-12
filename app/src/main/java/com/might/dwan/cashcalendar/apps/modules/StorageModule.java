package com.might.dwan.cashcalendar.apps.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.might.dwan.cashcalendar.apps.AppContext;
import com.might.dwan.cashcalendar.apps.AppScope;
import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.data.preferences.Preferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 05.06.2018.
 */

@Module
public class StorageModule {

    @AppScope
    @Provides
    Preferences providePreferences(SharedPreferences manager) {
        return new Preferences(manager);
    }

    @AppScope
    @Provides
    SharedPreferences provideSharedPReferences(@AppContext Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }


    @AppScope
    @Provides
    DBHelper provideDatabase(@AppContext Context c) {
        return new DBHelper(c);
    }

    @AppScope
    @Provides
    PayCounterDB providePayCounterDb() {
        return new PayCounterDB();
    }

    @AppScope
    @Provides
    SubcategoryDB provideSubcategoryDb() {
        return new SubcategoryDB();
    }

    @AppScope
    @Provides
    CategoryDB provideCategoryDb() {
        return new CategoryDB();
    }

    @AppScope
    @Provides
    StatisticsDB provideStatisticsDb() {
        return new StatisticsDB();
    }
}
