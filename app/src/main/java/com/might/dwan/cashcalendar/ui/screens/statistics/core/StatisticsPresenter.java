package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.DateUtils;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ilya on 13.06.2018.
 */

public class StatisticsPresenter extends BasePresenter<StatisticsContractor.IView, StatisticsModel>
        implements StatisticsContractor.IPresenter {

    private RxSchedulers mSchedule;
    private CompositeDisposable mSubscriptions = new CompositeDisposable();

    public StatisticsPresenter(StatisticsModel model, StatisticsContractor.IView v,
                               RxSchedulers schedule) {
        super(v);
        this.mSchedule = schedule;
        bindModel(model);
    }

    @Override public void updateView() {
        loadMax();
        loadMin();
        loadSum();
    }

    private void loadMax() {
        mSubscriptions.add(model.getMax()
                .subscribeOn(mSchedule.io())
                .flatMap(Observable::fromIterable)
                .firstOrError()
                .observeOn(mSchedule.androidThread())
                .subscribe(this::setMaxToView,
                        Throwable::printStackTrace));
    }

    private void setMaxToView(CostItem item) {
        view().setMaxAmount(item.getCountPay());
        view().setMaxDate(DateUtils.stampToYMDHMS(Long.parseLong(item.getTimestamp())));
        view().setMaxCategory(item.getCategoryText());
        view().setMaxSubcategory(item.getSubcategoryText());
    }

    private void loadMin() {
        mSubscriptions.add(model.getMin()
                .subscribeOn(mSchedule.io())
                .flatMap(Observable::fromIterable)
                .firstOrError()
                .observeOn(mSchedule.androidThread())
                .subscribe(this::setMinToView,
                        Throwable::printStackTrace));
    }

    private void setMinToView(CostItem item) {
        view().setMinAmount(item.getCountPay());
        view().setMinDate(DateUtils.stampToYMDHMS(Long.parseLong(item.getTimestamp())));
        view().setMinCategory(item.getCategoryText());
        view().setMinSubcategory(item.getSubcategoryText());
    }

    private void loadSum() {
        mSubscriptions.add(model.getSum()
                .subscribeOn(mSchedule.io())
                .observeOn(mSchedule.androidThread())
                .subscribe(this::setSumToView,
                        Throwable::printStackTrace));
    }

    private void setSumToView(String amount) {
        view().setSumAmount(amount);
    }

    @Override public void onRelease() {
        mSubscriptions.dispose();
    }
}
