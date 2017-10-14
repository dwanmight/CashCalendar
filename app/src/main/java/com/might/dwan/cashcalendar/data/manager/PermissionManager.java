package com.might.dwan.cashcalendar.data.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;

/**
 * Created by Might on 28.09.2017.
 */

public class PermissionManager {
    private WeakReference<Activity> mWeakActivity;

    public PermissionManager(Activity activity) {
        mWeakActivity = new WeakReference<Activity>(activity);
    }

    public boolean isStorage() {
        boolean res = false;
        if (ActivityCompat.checkSelfPermission(mWeakActivity.get(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            res = true;
        } else {
            ActivityCompat.requestPermissions(mWeakActivity.get(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        return res;
    }
}
