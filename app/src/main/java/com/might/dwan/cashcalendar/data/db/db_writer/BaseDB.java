package com.might.dwan.cashcalendar.data.db.db_writer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ilya on 17.06.2018.
 */

public abstract class BaseDB {

    public void release(Cursor c, SQLiteDatabase db) {
        if (c != null && !c.isClosed())
            c.close();
        if (db != null)
            db.close();
    }
}
