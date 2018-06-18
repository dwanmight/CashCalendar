package com.might.dwan.cashcalendar.data.db.db_writer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.models.CostItem;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class StatisticsDB extends BaseDB {


    //    public ArrayList<NameIdItem> getStatistics(SQLiteDatabase db) throws Exception {
    //        ArrayList<NameIdItem> data = new ArrayList<>();
    //
    //        Cursor c = db.query(DBHelper.TABLE_USER_PAY
    //                , new String[]{DBHelper.COLUMN_USER_PAY_DATE, DBHelper
    // .COLUMN_USER_PAY_COUNT_PAY}
    //                , DBHelper.COLUMN_USER_PAY_DATE + " = ?"
    //                , new String[]{String.valueOf(category_id)}
    //                , null
    //                , null
    //                , null);
    //        if (c.getCount() > 0) {
    //            c.moveToFirst();
    //            for (int i = 0; i < c.getCount(); i++) {
    //                NameIdItem item = new NameIdItem();
    //                item.setId(c.getInt(c.getColumnIndex(DBHelper.COLUMN_SUBCATEGORIES_ID)));
    //                item.setName(c.getString(c.getColumnIndex(DBHelper
    // .COLUMN_SUBCATEGORIES_CATEGORY_TITLE)));
    //                data.add(item);
    //                c.moveToNext();
    //            }
    //        }
    //        release(c, db);
    //        return data;
    //    }


    public ArrayList<CostItem> getMax(SQLiteDatabase db) throws Exception {
        ArrayList<CostItem> data = new ArrayList<>();

        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , new String[]{"MAX(count_pay) as max_pay"}
                , null
                , null
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                CostItem item = PayCounterDB.createItem(c);
                data.add(item);
                c.moveToNext();
            }
        }
        release(c, db);
        return data;
    }
}