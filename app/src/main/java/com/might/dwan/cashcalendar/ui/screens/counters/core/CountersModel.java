package com.might.dwan.cashcalendar.ui.screens.counters.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.screens.DetailPayFragment;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;

import java.util.ArrayList;

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

    public ArrayList<PayCounterModel> getData(String id) {
        try {
            return payDb.load(db.getReadableDatabase(), 20, id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void goCreateNote() {
        fragment.goCreateNote(DetailPayFragment.MODE_NEW, null);
    }

    public void goUpdateNote(PayCounterModel item) {
        fragment.goCreateNote(DetailPayFragment.MODE_DETAIL, item);
    }
}