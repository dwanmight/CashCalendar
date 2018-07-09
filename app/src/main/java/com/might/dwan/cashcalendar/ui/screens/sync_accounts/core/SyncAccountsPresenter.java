package com.might.dwan.cashcalendar.ui.screens.sync_accounts.core;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;

public class SyncAccountsPresenter extends BasePresenter<SyncAccountsContractor.IView, SyncAccountsModel>
        implements SyncAccountsContractor.IPresenter {

    public SyncAccountsPresenter(SyncAccountsContractor.IView iView, SyncAccountsModel model) {
        super(iView);
        bindModel(model);
    }

    @Override public void updateView() {

    }
}
