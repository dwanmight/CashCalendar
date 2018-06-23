package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.DateUtils;
import com.might.dwan.cashcalendar.utils.ValidUtils;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import java.util.ArrayList;

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
        loadMonthlyStatistics();
    }

    private void loadMonthlyStatistics() {
        ArrayList<ChartInfo> list = new ArrayList<>();
        ChartInfo info = model.getCurrentMonthAmount();
        if (info.getValue() > 0) {
            list.add(info);
        }

        if (list.isEmpty()) {
            view().hideCharts();
        } else {
            view().addCharts(list);
        }
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
        try {
            if (isValidItem(item)) {
                view().setMaxAmount(item.getCountPay());
                view().setMaxDate(getDate(item.getTimestamp()));
                view().setMaxCategory(item.getCategoryText());
                view().setMaxSubcategory(item.getSubcategoryText());
            } else {
                view().hideMax();
            }
        } catch (Exception e) {e.printStackTrace();}
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
        try {
            if (isValidItem(item)) {
                view().setMinAmount(item.getCountPay());
                view().setMinDate(getDate(item.getTimestamp()));
                view().setMinCategory(item.getCategoryText());
                view().setMinSubcategory(item.getSubcategoryText());
            } else {
                view().hideMin();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    private void loadSum() {
        mSubscriptions.add(model.getSum()
                .subscribeOn(mSchedule.io())
                .observeOn(mSchedule.androidThread())
                .subscribe(this::setSumToView,
                        Throwable::printStackTrace));
    }

    private void setSumToView(String amount) {
        try {
            if (!ValidUtils.isTextValid(amount)) {
                view().hideSum();
            } else {
                view().setSumAmount(amount);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    private String getDate(String timestamp) {
        if (timestamp == null) return "";

        return DateUtils.stampToYMDHMS(Long.parseLong(timestamp));
    }

    private boolean isValidItem(CostItem item) {
        return (item != null && item.getTimestamp() != null && item.getCountPay() != null);
    }

    @Override public void onRelease() {
        mSubscriptions.dispose();
    }
}
