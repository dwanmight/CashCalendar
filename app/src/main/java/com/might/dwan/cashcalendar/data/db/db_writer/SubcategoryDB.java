package com.might.dwan.cashcalendar.data.db.db_writer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdItem;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class SubcategoryDB extends BaseDB {

    public SubcategoryDB() {
    }

    public ArrayList<NameIdItem> getSubCategories(SQLiteDatabase db, int category_id) throws Exception {
        ArrayList<NameIdItem> data = new ArrayList<>();

        Cursor c = db.query(DBHelper.TABLE_SUBCATEGORIES
                , null
                , DBHelper.COLUMN_SUBCATEGORIES_CATEGORY_ID + " = ?"
                , new String[]{String.valueOf(category_id)}
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                NameIdItem item = new NameIdItem();
                item.setId(c.getInt(c.getColumnIndex(DBHelper.COLUMN_SUBCATEGORIES_ID)));
                item.setName(c.getString(c.getColumnIndex(DBHelper.COLUMN_SUBCATEGORIES_CATEGORY_TITLE)));
                data.add(item);
                c.moveToNext();
            }
        }
        release(c, db);
        return data;
    }
}