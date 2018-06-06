package com.might.dwan.cashcalendar.ui.screens.counters;

import android.app.Activity;
import android.content.Intent;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.apps.App;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.ui.screens.counters.core.CountersPresenter;
import com.might.dwan.cashcalendar.ui.screens.counters.core.CountersView;
import com.might.dwan.cashcalendar.ui.screens.counters.dagger.CountersModule;
import com.might.dwan.cashcalendar.ui.screens.counters.dagger.DaggerCountersComponent;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.IntentUtils;

import javax.inject.Inject;

/**
 * Created by Might on 25.08.2017.
 */

public class CountersFragment extends BaseFragment {

    @Inject CountersView view;
    @Inject CountersPresenter presenter;

    @Override public int getLayoutId() {return R.layout.fragment_counter_list;}

    @Override public void setupData() {
        DaggerCountersComponent.builder()
                .appComponent(App.getAppComponent())
                .countersModule(new CountersModule(this))
                .build()
                .inject(this);
    }


    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean result = resultCode == Activity.RESULT_OK;
        switch (requestCode) {
            case ConstantManager.REQUEST_ACTIVITY_DETAIL:
                getResultDetail(result, data);
                break;
        }
    }

    private void getResultDetail(boolean result, Intent data) {
        if (!result) return;
        presenter.updateData();
    }



    //Transition region
    public void goCreateNote(int mode, PayCounterModel item) {
        IntentUtils.startDetailPay(getActivity(), mode, item);
    }
}
