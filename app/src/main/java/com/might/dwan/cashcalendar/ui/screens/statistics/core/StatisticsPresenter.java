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
        mSubscriptions.add(model.loadMonthlyChartsInfo().subscribe(
                list->view().addCharts(new ArrayList<>(list)),
                Throwable::printStackTrace
        ));
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
                view().showMax();
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
                view().showMin();
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
            if (ValidUtils.isTextValid(amount)) {
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
