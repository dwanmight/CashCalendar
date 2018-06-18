package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;

import io.reactivex.Observable;

/**
 * Created by Ilya on 07.06.2018.
 */

public class StatisticsModel {
    private DBHelper dbHelper;
    private StatisticsDB statisticsDB;

    public StatisticsModel(DBHelper dbHelper, StatisticsDB statisticsDB) {
        this.dbHelper = dbHelper;
        this.statisticsDB = statisticsDB;
    }

    public Observable getMax() {
        Observable.zip(
                Observable.just(dbHelper),
                Observable.just(statisticsDB),
                (helper, db) -> db.getMax(helper.getReadableDatabase()))
                .flatMap();
        // TODO: 18.06.2018  
    }
}
