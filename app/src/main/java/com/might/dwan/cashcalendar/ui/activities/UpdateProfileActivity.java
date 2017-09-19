package com.might.dwan.cashcalendar.ui.activities;

import android.app.Fragment;

import com.might.dwan.cashcalendar.ui.fragments.UpdateProfileFragment;

/**
 * Created by Might on 24.08.2017.
 */

public class UpdateProfileActivity extends BaseFragmentActivity {

    @Override Fragment createFragment() {
        return new UpdateProfileFragment();
    }
}
