package com.might.dwan.cashcalendar.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.statistics.StatisticsFragment;
import com.might.dwan.cashcalendar.ui.screens.sync_accounts.SyncAccountsFragment;
import com.might.dwan.cashcalendar.utils.IntentUtils;

import java.util.List;

public class MainActivity extends BaseFragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private View mNavHeader;

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override Fragment createFragment() {
        return new CountersFragment();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavHeader = mNavigationView.getHeaderView(0);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setTitle("History");
        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mNavigationView.setCheckedItem(R.id.nav_main_list);
    }

    @Override public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override public void setListeners(boolean enable) {
        if (enable) {
            mNavigationView.setNavigationItemSelectedListener(this);
            mNavHeader.findViewById(R.id.header_photo_img).setOnClickListener(this);
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        } else {
            mDrawerLayout.removeDrawerListener(mDrawerToggle);
            mNavigationView.setNavigationItemSelectedListener(null);
            mNavHeader.findViewById(R.id.header_photo_img).setOnClickListener(null);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_list) {
            startFragment(new CountersFragment(), "History");
        } else if (id == R.id.nav_statistics) {
            startFragment(new StatisticsFragment(), "Statistics");
        } else if (id == R.id.nav_sync_accounts) {
            startFragment(new SyncAccountsFragment(), "Sync");
        } else if (id == R.id.nav_settings) {
            IntentUtils.startSettings(this);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_photo_img:
                startUpdateProfile();
                break;
        }
    }

    public void startFragment(Fragment fragment, String title) {
        FragmentManager fm = getSupportFragmentManager();
        List<android.support.v4.app.Fragment> fragments = fm.getFragments();
        int stackCount = fragments.size();
        if (stackCount > 0 &&
                (fragments.get(stackCount - 1).getClass().getSimpleName()
                        .equals(fragment.getClass().getSimpleName()))) {
            return;
        }
        super.startFragment(fragment);
        mToolbar.setTitle(title);
    }

    private void startUpdateProfile() {
        Intent startUpdateIntent = new Intent(this, UpdateProfileActivity.class);
        startActivity(startUpdateIntent);
    }
}
