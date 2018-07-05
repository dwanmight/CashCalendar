package com.might.dwan.cashcalendar.data.db.db_writer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdItem;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class CategoryDB extends BaseDB {

    public CategoryDB() {
    }

    public ArrayList<NameIdItem> getCategories(SQLiteDatabase db) throws Exception {
        ArrayList<NameIdItem> data = new ArrayList<>();

        Cursor c = db.query(DBHelper.TABLE_CATEGORIES
                , null
                , null
                , null
                , null
                , null
                , null);

        // TODO: 05.07.2018 need refactor
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                NameIdItem item = new NameIdItem();
                item.setId(c.getInt(c.getColumnIndex(DBHelper.COLUMN_CATEGORIES_ID)));
                item.setName(c.getString(c.getColumnIndex(DBHelper.COLUMN_CATEGORIES_TITLE)));
                data.add(item);
                c.moveToNext();
            }
        }
        release(c, db);
        return data;
    }
}