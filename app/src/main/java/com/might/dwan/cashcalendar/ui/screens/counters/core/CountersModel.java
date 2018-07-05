package com.might.dwan.cashcalendar.ui.screens.counters.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostPresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersModel {

    private WeakReference<CountersFragment> fragment;
    private PayCounterDB payDb;
    private DBHelper db;

    public CountersModel(CountersFragment fragment, PayCounterDB payDb, DBHelper db) {
        this.fragment = new WeakReference<>(fragment);
        this.payDb = payDb;
        this.db = db;
    }

    public Observable<ArrayList<CostItem>> getData(String userId) {
        boolean local = true;
        return Observable.just(local).map(it -> payDb.load(db.getReadableDatabase()));
    }

    public void goCreateNote() {
        if (!isValidRoute()) return;
        fragment.get().goCreateNote(DetailCostPresenter.MODE_NEW, null);
    }

    public void goUpdateNote(CostItem item) {
        if (!isValidRoute()) return;
        fragment.get().goCreateNote(DetailCostPresenter.MODE_DETAIL, item);
    }

    private boolean isValidRoute() {
        return (fragment != null && fragment.get() != null);
    }
}