package com.might.dwan.cashcalendar.ui.screens.detail_item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.apps.App;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.DatePickerDialog;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostPresenter;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostView;
import com.might.dwan.cashcalendar.ui.screens.detail_item.dagger.DaggerDetailCostComponent;
import com.might.dwan.cashcalendar.ui.screens.detail_item.dagger.DetailCostModule;
import com.might.dwan.cashcalendar.utils.ConstantManager;

import javax.inject.Inject;

/**
 * Created by Might on 27.08.2017.
 */

public class DetailCostFragment extends BaseFragment {

    @Inject DetailCostView view;
    @Inject DetailCostPresenter presenter;

    public static DetailCostFragment newInstance(int mode, CostItem model) {
        Bundle args = new Bundle();
        args.putInt(ConstantManager.EXTRA_MODE, mode);
        args.putSerializable(ConstantManager.EXTRA_ITEM, model);

        DetailCostFragment fragment = new DetailCostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public int getLayoutId() {return R.layout.fragment_detail_pay;}

    @Override public void setupData(Bundle state) {
        CostItem item = state.containsKey(ConstantManager.EXTRA_ITEM) ?
                (CostItem) state.getSerializable(ConstantManager.EXTRA_ITEM) : null;

        DaggerDetailCostComponent.builder().appComponent(App.getAppComponent())
                .detailCostModule(new DetailCostModule(this, item))
                .build()
                .inject(this);

    }

    @Override public void releaseData() {
        presenter.release();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_pay, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_done:
                presenter.clickSave();
                return true;
            case android.R.id.home:
                presenter.clickBack();
                return true;
        }
        return false;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveState(outState);
    }



    //Activity result dialog
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean result = resultCode == Activity.RESULT_OK;
        switch (requestCode) {
            case ConstantManager.REQUEST_DATE_DIALOG:
                getResultDialogDate(result, data);
                break;
        }
    }

    private void getResultDialogDate(boolean result, Intent data) {
        if (!result) return;
        long stamp = data.getLongExtra(ConstantManager.EXTRA_STAMP, 0);
        presenter.getResultDateDialog(stamp);
    }


    //show region
    public void showDatePickDialog(String timestamp) {
        DatePickerDialog dialog = DatePickerDialog.newInstance(timestamp);
        dialog.setTargetFragment(this, ConstantManager.REQUEST_DATE_DIALOG);
        dialog.show(getFragmentManager(), "PickDate");
    }

    public void showErrorPickCategory() {
        showToast(getString(R.string.pick_category));
    }

    public void showErrorPickSubcategory() {
        showToast(getString(R.string.pick_subcategory));
    }

    public void showErrorEnterAmount() {
        showToast(getString(R.string.enter_amount));
    }

    public void goBack(boolean result) {
        getActivity().setResult(result ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

}
