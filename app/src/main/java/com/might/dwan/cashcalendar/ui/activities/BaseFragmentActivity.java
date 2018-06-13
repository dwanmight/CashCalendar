package com.might.dwan.cashcalendar.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.might.dwan.cashcalendar.R;

/**
 * Created by Might on 25.08.2017.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {

    abstract Fragment createFragment();

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);

        Fragment fragment = createFragment();
        if (fragment != null) {
            startFragment(fragment);
        }
    }

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment_frame, fragment)
                .addToBackStack(fragment.getClass().toString())
                .commit();
    }

    @CallSuper @Override protected void onResume() {
        setListeners(true);
        super.onResume();
    }


    /**
     * Method for bind and release listeners
     *
     * @param enable TRUE if should bind and
     *               FALSE to release
     */
    public void setListeners(boolean enable) {}

    @CallSuper @Override protected void onStart() {
        setListeners(false);
        super.onStart();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override public void onBackPressed() {
        int count_fragment_stack = getFragmentManager().getBackStackEntryCount();
        if (count_fragment_stack > 1) {
            getFragmentManager().popBackStack();
        } else if (count_fragment_stack == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.container_fragment_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
