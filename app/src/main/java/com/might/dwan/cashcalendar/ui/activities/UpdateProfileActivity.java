package com.might.dwan.cashcalendar.ui.activities;

import android.support.v4.app.Fragment;

import com.might.dwan.cashcalendar.ui.screens.UpdateProfileFragment;

/**
 * Created by Might on 24.08.2017.
 */

public class UpdateProfileActivity extends BaseFragmentActivity {

    @Override Fragment createFragment() {
        return new UpdateProfileFragment();
    }
}
