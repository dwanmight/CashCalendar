package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;

/**
 * Created by ilya on 13.06.2018.
 */

public class StatisticsPresenter extends BasePresenter<StatisticsContractor.IView, StatisticsModel>
        implements StatisticsContractor.IPresenter {

    public StatisticsPresenter(StatisticsModel model, StatisticsContractor.IView v) {
        super(v);
        bindModel(model);
    }

    @Override public void updateView() {

    }
}
