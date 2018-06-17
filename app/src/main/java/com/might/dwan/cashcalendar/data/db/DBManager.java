package com.might.dwan.cashcalendar.data.db;

import android.content.Context;

/**
 * Created by Might on 02.09.2017.
 */


/**
 * use dagger for create dependency
 */
@Deprecated
public class DBManager {
    private Context mAppContext;
    public static DBHelper sDBHelper;

    public static DBHelper get(Context context) {
        if (sDBHelper == null) {
            synchronized (DBManager.class) {
                if (sDBHelper == null) {
                    sDBHelper = new DBHelper(context.getApplicationContext());
                }
            }
        }
        return sDBHelper;
    }

    private DBManager(Context context) {
        mAppContext = context;
    }
}
