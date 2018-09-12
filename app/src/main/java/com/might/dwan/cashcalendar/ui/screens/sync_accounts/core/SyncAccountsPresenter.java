package com.might.dwan.cashcalendar.ui.screens.sync_accounts.core;

import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;
import com.might.dwan.cashcalendar.data.network.ILoginModel;
import com.might.dwan.cashcalendar.data.network.fb.FacebookAPI;
import com.might.dwan.cashcalendar.utils.ConstantManager;

public class SyncAccountsPresenter extends BasePresenter<SyncAccountsContractor.IView, SyncAccountsModel>
        implements SyncAccountsContractor.IPresenter, FacebookAPI.OnTokenChangedListener {

    FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.i(ConstantManager.TAG, "onSuccess: " + loginResult.getAccessToken());
            checkFacebookLogin();
        }

        @Override
        public void onCancel() {
            Log.i(ConstantManager.TAG, "onCancel: ");
        }

        @Override
        public void onError(FacebookException error) {
            Log.i(ConstantManager.TAG, "onError: " + error.toString());
        }
    };


    public SyncAccountsPresenter(SyncAccountsContractor.IView iView, SyncAccountsModel model) {
        super(iView);
        bindModel(model);
        model.registerTokenListener(this);
        view().registerFacebookCallback(facebookCallback);
    }

    @Override
    public void updateView() {
        if (!model.isFBNeedLogin()) {
            checkFacebookLogin();
        }
    }

    private void checkFacebookLogin() {
        if (model.isFBNeedLogin()) {
            view().loginFB();
        } else {
            ILoginModel fbProfile = model.getProfile();
            if (!fbProfile.isValid()) {
                view().loginFB();
            } else {
                view().showSetInfoDialog(fbProfile.getName(), fbProfile.getSurname());
            }
        }
    }

    @Override
    public void onRelease() {
        view().registerFacebookCallback(null);
        model.release();
    }


    //Click region
    @Override
    public void clickUpdateProfile(String name, String surname) {

    }

    @Override
    public void onTokenRelease() {
        Log.i(ConstantManager.TAG, "onTokenRelease: ");
    }

    @Override
    public void onNewTokenReceived(String newToken) {
        Log.i(ConstantManager.TAG, "onNewTokenReceived: " + newToken);
        checkFacebookLogin();
    }

    @Override
    public void onTokenChanged(String newToken) {
        Log.i(ConstantManager.TAG, "onTokenChanged: " + newToken);
        checkFacebookLogin();
    }
}
