package com.might.dwan.cashcalendar.ui.screens.sync_accounts.core;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

public interface SyncAccountsContractor {

    interface IView {

        void registerFacebookCallback(FacebookCallback<LoginResult> facebookCallback);

        void showSetInfoDialog(String name, String surname);

        void loginFB();
    }

    interface IPresenter {

        void clickUpdateProfile(String name, String surname);
    }

}
