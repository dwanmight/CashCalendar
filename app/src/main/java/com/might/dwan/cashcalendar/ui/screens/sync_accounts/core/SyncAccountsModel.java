package com.might.dwan.cashcalendar.ui.screens.sync_accounts.core;

import com.might.dwan.cashcalendar.data.network.ILoginModel;
import com.might.dwan.cashcalendar.data.network.fb.FacebookAPI;

public class SyncAccountsModel {

    private FacebookAPI facebookAPI;


    public SyncAccountsModel(FacebookAPI facebookAPI) {
        this.facebookAPI = facebookAPI;
    }

    public ILoginModel getProfile() {
        return facebookAPI.getFBProfile();
    }

    public boolean isFBNeedLogin() {
        return facebookAPI.isNeedLogin();
    }

    public void release() {
        facebookAPI.setTokenListener(null);
        facebookAPI.release();
    }

    public void registerTokenListener(FacebookAPI.OnTokenChangedListener listener) {
        facebookAPI.setTokenListener(listener);
    }
}
