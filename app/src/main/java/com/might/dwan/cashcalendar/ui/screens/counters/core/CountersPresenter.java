package com.might.dwan.cashcalendar.ui.screens.counters.core;

import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.screens.counters.contractor.CountersClickListeners;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersPresenter implements CountersClickListeners {
    private CountersModel model;
    private CountersView view;
    private ArrayList<PayCounterModel> list = new ArrayList<>();
    private CompositeDisposable subscriptions = new CompositeDisposable();

    public CountersPresenter(CountersModel model, CountersView view) {
        this.model = model;
        this.view = view;

        updateView();
    }

    private void updateView() {
        view.bindClickListener(this);
        view.setupAdapter(list);
        subscriptions.add(view.getItemClicks()
                .subscribe(pos -> model.goUpdateNote(list.get(pos)), Throwable::printStackTrace));
        updateData();
    }


    public void updateData() {
        loadData(null);
    }

    private void loadData(String id) {
        try {
            list.clear();
            view.adapterDataChanged();
            list.addAll(model.getData(id));
            view.adapterDataChanged();
        } catch (Exception e) {e.printStackTrace();}
    }



    //Click region
    @Override public void onClickAdd() {
        model.goCreateNote();
    }

    @Override public void onRefresh() {
        view.disableRefresh();
        updateData();
    }

    public void release() {

    }
}
