package com.might.dwan.cashcalendar.ui.screens.counters.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostPresenter;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersModel {

    private CountersFragment fragment;
    private PayCounterDB payDb;
    private DBHelper db;

    public CountersModel(CountersFragment fragment, PayCounterDB payDb, DBHelper db) {
        this.fragment = fragment;
        this.payDb = payDb;
        this.db = db;
    }

    public Observable<ArrayList<CostItem>> getData(String userId) {
        boolean local = true;
        return Observable.just(local).map(it -> payDb.load(db.getReadableDatabase(), 20, userId));
    }

    public void goCreateNote() {
        fragment.goCreateNote(DetailCostPresenter.MODE_NEW, null);
    }

    public void goUpdateNote(CostItem item) {
        fragment.goCreateNote(DetailCostPresenter.MODE_DETAIL, item);
    }
}