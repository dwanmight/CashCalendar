package com.might.dwan.cashcalendar.data.db.db_models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdModel;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class CategoryDB {
    private Context mContext;

    public CategoryDB(Context context) {
        mContext = context;
    }

    public ArrayList<NameIdModel> getCategories() {
        ArrayList<NameIdModel> data = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.query(DBHelper.TABLE_CATEGORIES
                , null
                , null
                , null
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                NameIdModel item = new NameIdModel();
                item.setId(c.getInt(c.getColumnIndex(DBHelper.COLUMN_CATEGORIES_ID)));
                item.setName(c.getString(c.getColumnIndex(DBHelper.COLUMN_CATEGORIES_TITLE)));
                data.add(item);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return data;
    }
}