package com.might.dwan.cashcalendar.ui.screens.sync_accounts;

import android.os.Bundle;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.core.SyncAccountsContractor;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger.DaggerSyncAccountsComponent;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger.SyncAccountsModule;

import javax.inject.Inject;

public class SyncAccountsFragment extends BaseFragment
        implements SyncAccountsContractor.IView {

    @Inject SyncAccountsContractor.IPresenter presenter;

    @Override public int getLayoutId() {
        return R.layout.fragment_sync_accounts;
    }

    @Override public void setupData(Bundle state) {
        DaggerSyncAccountsComponent.builder()
                .syncAccountsModule(new SyncAccountsModule(this))
                .build()
                .inject(SyncAccountsFragment.this);
    }
}
