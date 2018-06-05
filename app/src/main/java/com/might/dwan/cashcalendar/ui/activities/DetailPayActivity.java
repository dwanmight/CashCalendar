package com.might.dwan.cashcalendar.ui.activities;

import android.app.Fragment;
import android.content.Intent;

import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.screens.DetailPayFragment;
import com.might.dwan.cashcalendar.utils.ConstantManager;

/**
 * Created by Might on 27.08.2017.
 */

public class DetailPayActivity extends BaseFragmentActivity {
    @Override Fragment createFragment() {
        Intent intent = getIntent();
        int mode = intent.getIntExtra(ConstantManager.EXTRA_MODE, 0);
        PayCounterModel model = intent.hasExtra(ConstantManager.EXTRA_ITEM) ?
                (PayCounterModel) intent.getSerializableExtra(ConstantManager.EXTRA_ITEM) : null;
        return DetailPayFragment.newInstance(mode, model);
    }
}
