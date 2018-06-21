package com.might.dwan.cashcalendar.data.db.db_writer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by Might on 27.08.2017.
 */

public class PayCounterDB extends BaseDB {

    public CostItem getPayItem(SQLiteDatabase db, String item_id) throws Exception {
        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , null
                , DBHelper.COLUMN_USER_PAY_ITEM_ID + " = ?"
                , new String[]{item_id}
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            CostItem model = new CostItem();
            model.setPayItemId(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_ITEM_ID)));
            model.setCategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY)));
            model.setCategoryText(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT)));
            model.setSubcategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY)));
            model.setSubcategoryText(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT)));
            model.setDescription(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DESCRIPTION)));
            model.setTimestamp(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DATE)));
            model.setUserId(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_USER_ID)));
            model.setCountPay(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_COUNT_PAY)));
            release(c, db);
            return model;
        } else {
            release(c, db);
            return null;
        }
    }

    public long insert(SQLiteDatabase db, CostItem model) throws Exception {
        long res = -1;
        if (model == null) return res;

        res = db.insert(DBHelper.TABLE_USER_PAY, null, getContentValuesFromItem(model));

        db.close();
        return res;
    }

    private ContentValues getContentValuesFromItem(CostItem item) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_USER_PAY_USER_ID, item.getUserId());
        cv.put(DBHelper.COLUMN_USER_PAY_ITEM_ID, item.getPayItemId());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY, item.getCategory());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT, item.getCategoryText());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY, item.getSubcategory());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT, item.getSubcategoryText());
        cv.put(DBHelper.COLUMN_USER_PAY_DESCRIPTION, item.getDescription());
        cv.put(DBHelper.COLUMN_USER_PAY_DATE, DateUtils.parseTimeStampToUnix(item.getTimestamp()));
        cv.put(DBHelper.COLUMN_USER_PAY_COUNT_PAY, item.getCountPay());
        return cv;
    }

    public long update(SQLiteDatabase db, CostItem model) throws Exception {
        long res = -1;
        if (model == null) return res;

        res = db.update(DBHelper.TABLE_USER_PAY,
                getContentValuesFromItem(model),
                DBHelper.COLUMN_USER_PAY_DATE + " = ?",
                new String[]{String.valueOf(DateUtils.parseTimeStampToUnix(model.getTimestamp()))});

        db.close();
        return res;
    }

    public ArrayList<CostItem> load(SQLiteDatabase db) throws Exception {
        return load(db, 20, null);
    }

    public ArrayList<CostItem> load(SQLiteDatabase db, int limit) throws Exception {
        return load(db, limit, null);
    }

    public ArrayList<CostItem> load(SQLiteDatabase db, int limit, String timestamp) throws Exception {
        ArrayList<CostItem> dataList = new ArrayList<>();
        Cursor c = null;
        try {
            if (timestamp == null || timestamp.equals("")) {
                c = getCursorFromFirst(db, limit);
            } else {
                c = getCursorFromLast(db, timestamp, limit);
            }

            Log.i(ConstantManager.TAG, "load: " + DatabaseUtils.dumpCursorToString(c));

            if (c.getCount() > 0) {
                c.moveToFirst();
                for (int i = 0; i < c.getCount(); i++) {
                    dataList.add(createItem(c));
                    c.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(c, db);
        }
        return dataList;
    }

    public ArrayList<CostItem> loadMonthly(@NonNull SQLiteDatabase db) {
        ArrayList<CostItem> list = new ArrayList<>();
        Cursor c = null;
        try {
            c = db.query(DBHelper.TABLE_USER_PAY,
                    null,
                    null,
                    null,
                    DBHelper.COLUMN_USER_PAY_DATE,
                    null,
                    "ASC");
            // TODO: 14.06.2018 having and group by
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(c, db);
        }
        return list;
    }

    public static CostItem createItem(Cursor c) {
        CostItem item = new CostItem();
        item.setPayItemId(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_ITEM_ID)));
        item.setCategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY)));
        item.setCategoryText(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT)));
        item.setSubcategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY)));
        item.setSubcategoryText(c.getString(c.getColumnIndex(DBHelper
                .COLUMN_USER_PAY_SUBCATEGORY_TEXT)));
        item.setDescription(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DESCRIPTION)));
        item.setTimestamp(
                String.valueOf(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DATE)) * 1000L));
        item.setUserId(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_USER_ID)));
        item.setCountPay(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_COUNT_PAY)));
        return item;
    }

    private Cursor getCursorFromFirst(SQLiteDatabase db, int limit) throws Exception {
        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , null
                , null
                , null
                , null
                , null
                , DBHelper.COLUMN_USER_PAY_DATE + " ASC"
                , String.valueOf(limit));
        return c;
    }

    private Cursor getCursorFromLast(SQLiteDatabase db, String timestamp, int limit) throws Exception {
        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , null
                , DBHelper.COLUMN_USER_PAY_DATE + " > ?"
                , new String[]{timestamp}
                , null
                , null
                , DBHelper.COLUMN_USER_PAY_DATE + " ASC"
                , String.valueOf(limit));
        return c;
    }
}
