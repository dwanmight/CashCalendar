package com.might.dwan.cashcalendar.ui.screens.sync_accounts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.dialogs.UpdateProfileDialog;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.core.SyncAccountsContractor;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger.DaggerSyncAccountsComponent;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.dagger.SyncAccountsModule;
import com.might.dwan.cashcalendar.utils.ConstantManager;

import java.util.Arrays;

import javax.inject.Inject;

public class SyncAccountsFragment extends BaseFragment
        implements SyncAccountsContractor.IView {

    private TextView mFullnameTv;
    private LoginButton mFbLoginBtn;
    private CallbackManager callbackManager;

    @Inject
    SyncAccountsContractor.IPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sync_accounts;
    }

    @Override
    public void setupData(Bundle state) {
        callbackManager = CallbackManager.Factory.create();

        DaggerSyncAccountsComponent.builder()
                .syncAccountsModule(new SyncAccountsModule(this))
                .build()
                .inject(SyncAccountsFragment.this);
    }

    @Override
    public void initUI(View view) {
        mFullnameTv = view.findViewById(R.id.fullname_tv);
        mFbLoginBtn = view.findViewById(R.id.login_fb_btn);
        mFbLoginBtn.setReadPermissions("email");
        mFbLoginBtn.setFragment(this);
    }


    @Override
    public void loginFB() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    //Dialogs
    @Override
    public void showSetInfoDialog(String name, String surname) {
        UpdateProfileDialog dialog = UpdateProfileDialog.newInstance(name, surname);
        dialog.setOkListener(() -> presenter.clickUpdateProfile(name, surname));
        mFullnameTv.setText(new StringBuilder().append(name).append(" ").append(surname));
        Log.i(ConstantManager.TAG, "showSetInfoDialog: " + name + " " + surname);
        dialog.show(getFragmentManager(), dialog.getClass().getSimpleName());
    }


    //actions
    @Override
    public void registerFacebookCallback(FacebookCallback<LoginResult> facebookCallback) {
        mFbLoginBtn.registerCallback(callbackManager, facebookCallback);
    }
}
