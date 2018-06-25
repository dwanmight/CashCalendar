package com.might.dwan.cashcalendar.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment;
import com.might.dwan.cashcalendar.utils.ConstantManager;

/**
 * Created by Might on 27.08.2017.
 */

public class DetailPayActivity extends BaseFragmentActivity {
    @Override Fragment createFragment() {
        Intent intent = getIntent();
        int mode = intent.getIntExtra(ConstantManager.EXTRA_MODE, 0);
        CostItem model = intent.hasExtra(ConstantManager.EXTRA_ITEM) ?
                (CostItem) intent.getSerializableExtra(ConstantManager.EXTRA_ITEM) : null;
        return DetailCostFragment.newInstance(mode, model);
    }
}