package com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger;

import com.might.dwan.cashcalendar.ui.screens.sync_accounts.SyncAccountsFragment;

import dagger.Component;

@SyncAccountsScope
@Component(modules = {SyncAccountsModule.class})
public interface SyncAccountsComponent {

    void inject(SyncAccountsFragment fragment);
}
