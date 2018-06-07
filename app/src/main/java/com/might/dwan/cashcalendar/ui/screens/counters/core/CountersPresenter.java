package com.might.dwan.cashcalendar.ui.screens.counters.core;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersPresenter extends BasePresenter<CountersView, CountersModel>
        implements CountersContractor.IPresenter {
    private ArrayList<CostItem> list = new ArrayList<>();
    private CompositeDisposable subscriptions = new CompositeDisposable();
    private RxSchedulers mRxSchedulers;

    public CountersPresenter(CountersModel model, CountersView view, RxSchedulers rxSchedulers) {
        super(view);
        mRxSchedulers = rxSchedulers;
        bindModel(model);
    }

    @Override public void updateView() {
        view().bindClickListener(this);
        view().setupAdapter(list);
        updateData();
        subscriptions.add(view().getItemClicks()
                .subscribe(pos -> model.goUpdateNote(list.get(pos)), Throwable::printStackTrace));
    }


    public void updateData() {
        loadData(null);
    }

    private void loadData(String id) {
        try {
            subscriptions.add(model.getData(id)
                    .subscribeOn(mRxSchedulers.io())
                    .observeOn(mRxSchedulers.androidThread())
                    .subscribe(dataList -> list.addAll(dataList),
                            Throwable::printStackTrace,
                            () -> view().adapterDataChanged(),
                            d -> list.clear()));
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override public void onRelease() {
        subscriptions.dispose();
    }

    //Click region
    @Override public void onClickAdd() {
        model.goCreateNote();
    }

    @Override public void onRefresh() {
        view().disableRefresh();
        updateData();
    }

}
