package com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger;

import com.might.dwan.cashcalendar.data.network.fb.FacebookAPI;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.core.SyncAccountsContractor;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.core.SyncAccountsModel;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.core.SyncAccountsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SyncAccountsModule {

    SyncAccountsContractor.IView view;

    public SyncAccountsModule(SyncAccountsContractor.IView view) {
        this.view = view;
    }


    @Provides
    SyncAccountsContractor.IView provideView() {
        return view;
    }

    @SyncAccountsScope
    @Provides
    SyncAccountsContractor.IPresenter providePresenter(SyncAccountsContractor.IView view,
                                                       SyncAccountsModel model) {
        return new SyncAccountsPresenter(view, model);
    }

    @SyncAccountsScope
    @Provides
    SyncAccountsModel provideModel(FacebookAPI facebookAPI) {
        return new SyncAccountsModel(facebookAPI);
    }
}
