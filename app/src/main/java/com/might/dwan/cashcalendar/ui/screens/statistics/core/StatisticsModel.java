package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.StatisticsDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.DateUtils;

import java.util.ArrayList;

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

    public ArrayList<ChartInfo> getMonthlyChartsInfo() {
        ArrayList<ChartInfo> charts = new ArrayList<>();
        String name;
        float amount;

        for (int i = -2; i <= 0; i++) {
            amount = statisticsDB.getMonthlyAmount(
                    dbHelper.getReadableDatabase(),
                    DateUtils.parseTimeStampToUnix(DateUtils.getMonthStamp(i)));
            name = DateUtils.getMonthNameFromCurrent(i);
            charts.add(new ChartInfo(name, amount));
        }
        return charts;
    }
}
