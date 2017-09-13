package com.might.dwan.cashcalendar.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Might on 24.08.2017.
 */

public class BaseActivity extends AppCompatActivity {

    public void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}
