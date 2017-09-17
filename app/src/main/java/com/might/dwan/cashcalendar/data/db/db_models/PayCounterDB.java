package com.might.dwan.cashcalendar.data.db.db_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Might on 27.08.2017.
 */

public class PayCounterDB {

    public PayCounterDB() {

    }

    public PayCounterModel getPayItem(SQLiteDatabase db, String item_id)throws Exception {
        Cursor c = db.query(DBHelper.TABLE_USER_PAY
                , null
                , DBHelper.COLUMN_USER_PAY_ITEM_ID + " = ?"
                , new String[]{item_id}
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            PayCounterModel model = new PayCounterModel();
            model.setPay_item_id(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_ITEM_ID)));
            model.setCategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY)));
            model.setCategory_text(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT)));
            model.setSubcategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY)));
            model.setSubcategory_text(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT)));
            model.setDescription(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DESCRIPTION)));
            model.setTimestamp(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DATE)));
            model.setUser_id(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_USER_ID)));
            model.setCount_pay(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_COUNT_PAY)));
            c.close();
            db.close();
            return model;
        } else {
            c.close();
            db.close();
            return null;
        }
    }

    public long insert(SQLiteDatabase db, PayCounterModel model)throws Exception {
        long res = -1;
        if (model == null) return res;
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_USER_PAY_USER_ID, model.getUser_id());
        cv.put(DBHelper.COLUMN_USER_PAY_ITEM_ID, model.getPay_item_id());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY, model.getCategory());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT, model.getCategory_text());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY, model.getSubcategory());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT, model.getSubcategory_text());
        cv.put(DBHelper.COLUMN_USER_PAY_DESCRIPTION, model.getDescription());
        cv.put(DBHelper.COLUMN_USER_PAY_DATE, model.getTimestamp());
        cv.put(DBHelper.COLUMN_USER_PAY_COUNT_PAY, model.getCount_pay());

        res = db.insert(DBHelper.TABLE_USER_PAY, null, cv);

        db.close();
        return res;
    }

    public long update(SQLiteDatabase db, PayCounterModel model)throws Exception {
        long res = -1;
        if (model == null) return res;
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_USER_PAY_USER_ID, model.getUser_id());
        cv.put(DBHelper.COLUMN_USER_PAY_ITEM_ID, model.getPay_item_id());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY, model.getCategory());
        cv.put(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT, model.getCategory_text());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY, model.getSubcategory());
        cv.put(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT, model.getSubcategory_text());
        cv.put(DBHelper.COLUMN_USER_PAY_DESCRIPTION, model.getDescription());
        cv.put(DBHelper.COLUMN_USER_PAY_DATE, model.getTimestamp());
        cv.put(DBHelper.COLUMN_USER_PAY_COUNT_PAY, model.getCount_pay());

        res = db.update(DBHelper.TABLE_USER_PAY, cv, DBHelper.COLUMN_USER_PAY_DATE + " = ?", new String[]{model.getTimestamp()});

        db.close();
        return res;
    }

    public ArrayList<PayCounterModel> load(SQLiteDatabase db) throws Exception {
        return load(db, -1, null);
    }

    public ArrayList<PayCounterModel> load(SQLiteDatabase db, int limit) throws Exception {
        return load(db, limit, null);
    }

    public ArrayList<PayCounterModel> load(SQLiteDatabase db, int limit, String timestamp) throws Exception {
        ArrayList<PayCounterModel> dataList = new ArrayList<>();
        Cursor c = null;
        try {
            if (timestamp == null || timestamp.equals("")) {
                c = getCursorFromFirst(db, limit);
            } else {
                c = getCursorFromLast(db, timestamp, limit);
            }
            Log.i(TAG, "load: " + c.getCount());
            if (c.getCount() > 0) {
                c.moveToFirst();
                for (int i = 0; i < c.getCount(); i++) {
                    PayCounterModel model = new PayCounterModel();
                    model.setPay_item_id(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_ITEM_ID)));
                    model.setCategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY)));
                    model.setCategory_text(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT)));
                    model.setSubcategory(c.getInt(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY)));
                    model.setSubcategory_text(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT)));
                    model.setDescription(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DESCRIPTION)));
                    model.setTimestamp(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_DATE)));
                    model.setUser_id(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_USER_ID)));
                    model.setCount_pay(c.getString(c.getColumnIndex(DBHelper.COLUMN_USER_PAY_COUNT_PAY)));
                    dataList.add(model);
                    c.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null)
                c.close();
            if (db != null)
                db.close();
        }
        return dataList;
    }

    private Cursor getCursorFromFirst(SQLiteDatabase db, int limit)throws Exception {
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

    private Cursor getCursorFromLast(SQLiteDatabase db, String timestamp, int limit)throws Exception {
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
