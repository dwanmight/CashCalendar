package com.might.dwan.cashcalendar.data.db.db_writer;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.ConstantManager;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class StatisticsDB extends BaseDB {

    public ArrayList<CostItem> getMax(SQLiteDatabase db) {
        ArrayList<CostItem> data = new ArrayList<>();

        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , new String[]{"MAX(count_pay) AS max_amount",
                        DBHelper.COLUMN_USER_PAY_ITEM_ID,
                        DBHelper.COLUMN_USER_PAY_USER_ID,
                        DBHelper.COLUMN_USER_PAY_CATEGORY,
                        DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_DESCRIPTION,
                        DBHelper.COLUMN_USER_PAY_COUNT_PAY,
                        DBHelper.COLUMN_USER_PAY_DATE
                }
                , null
                , null
                , null
                , null
                , null);
        Log.i(ConstantManager.TAG, "getMax: " + DatabaseUtils.dumpCursorToString(c));
        Log.i(ConstantManager.TAG, "getMax: " + c.getCount());

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

    public ArrayList<CostItem> getMin(SQLiteDatabase db) {
        ArrayList<CostItem> data = new ArrayList<>();

        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , new String[]{"MIN(count_pay) AS min_amount",
                        DBHelper.COLUMN_USER_PAY_ITEM_ID,
                        DBHelper.COLUMN_USER_PAY_USER_ID,
                        DBHelper.COLUMN_USER_PAY_CATEGORY,
                        DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_DESCRIPTION,
                        DBHelper.COLUMN_USER_PAY_COUNT_PAY,
                        DBHelper.COLUMN_USER_PAY_DATE
                }
                , null
                , null
                , null
                , null
                , null);
        Log.i(ConstantManager.TAG, "getMin: " + DatabaseUtils.dumpCursorToString(c));

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

    public String getSum(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder("");
        String columnName = "sum_amount";
        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , new String[]{"SUM(count_pay) AS " + columnName,
                        DBHelper.COLUMN_USER_PAY_ITEM_ID,
                        DBHelper.COLUMN_USER_PAY_USER_ID,
                        DBHelper.COLUMN_USER_PAY_CATEGORY,
                        DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY,
                        DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT,
                        DBHelper.COLUMN_USER_PAY_DESCRIPTION,
                        DBHelper.COLUMN_USER_PAY_COUNT_PAY,
                        DBHelper.COLUMN_USER_PAY_DATE
                }
                , null
                , null
                , null
                , null
                , null);
        Log.i(ConstantManager.TAG, "getSum: " + DatabaseUtils.dumpCursorToString(c));

        if (c.getCount() > 0) {
            c.moveToFirst();
            String amount = c.getString(c.getColumnIndex(columnName));
            if (amount != null) {
                builder.append(amount);
            }
        }
        release(c, db);
        return builder.toString();
    }
}