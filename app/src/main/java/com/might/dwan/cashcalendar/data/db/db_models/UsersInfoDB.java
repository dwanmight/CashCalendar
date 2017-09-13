package com.might.dwan.cashcalendar.data.db.db_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.models.UserModel;

/**
 * Created by Might on 24.08.2017.
 */

public class UsersInfoDB {
    private Context mContext;

    public UsersInfoDB(Context context) {
        mContext = context;
    }

    public void addUser(String nickname, String name, String surname) {
        if (mContext == null) return;
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(DBHelper.TABLE_USERS_INFO
                , new String[]{DBHelper.COLUMN_USERS_INFO_NICKNAME}
                , DBHelper.COLUMN_USERS_INFO_NICKNAME + " = ?"
                , new String[]{nickname}
                , null
                , null
                , null);
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_USERS_INFO_NICKNAME, nickname);
        cv.put(DBHelper.COLUMN_USERS_INFO_NAME, name);
        cv.put(DBHelper.COLUMN_USERS_INFO_SURNAME, surname);
        if (c.getCount() <= 0) {
            db.insert(DBHelper.TABLE_USERS_INFO, null, cv);
        } else {
            db.update(DBHelper.TABLE_USERS_INFO, cv, "user_id = ?", new String[]{nickname});
        }
        c.close();
        db.close();
    }

    public UserModel getUser(String nickname) {
        Log.i("TAGTAG", "getUser: " + nickname);
        UserModel user = new UserModel("", "", "");
        if (nickname == null || nickname.equals("")) return user;

        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.query(DBHelper.TABLE_USERS_INFO
                , new String[]{DBHelper.COLUMN_USERS_INFO_NICKNAME, DBHelper.COLUMN_USERS_INFO_NAME, DBHelper.COLUMN_USERS_INFO_SURNAME}
                , "user_id = ?"
                , new String[]{nickname}
                , null
                , null
                , null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            user.setNickname(c.getString(c.getColumnIndex(DBHelper.COLUMN_USERS_INFO_NICKNAME)));
            user.setName(c.getString(c.getColumnIndex(DBHelper.COLUMN_USERS_INFO_NAME)));
            user.setSurname(c.getString(c.getColumnIndex(DBHelper.COLUMN_USERS_INFO_SURNAME)));
        }
        c.close();
        db.close();
        return user;
    }
}
