package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
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

    public Observable<ArrayList<CostItem>> getMax() {
        return Observable.just(statisticsDB.getMax(dbHelper.getReadableDatabase()));
    }

    public Observable<ArrayList<CostItem>> getMin() {
        return Observable.just(statisticsDB.getMin(dbHelper.getReadableDatabase()));
    }

    public Observable<String> getSum() {
        return Observable.just(statisticsDB.getSum(dbHelper.getReadableDatabase()));
    }

    public Maybe<List<ChartInfo>> loadMonthlyChartsInfo() {
        return Flowable.fromArray(-2, -1, 0)
                .map(index -> {
                    float amount = statisticsDB.getMonthlyAmount(dbHelper.getReadableDatabase(),
                            DateUtils.parseTimeStampToUnix(DateUtils.getMonthStamp(index)));
                    String name = DateUtils.getMonthNameFromCurrent(index);
                    return new ChartInfo(name, amount);
                }).filter(info -> info.getValue() > 0)
                .toList()
                .filter(list -> !list.isEmpty());
    }
}
