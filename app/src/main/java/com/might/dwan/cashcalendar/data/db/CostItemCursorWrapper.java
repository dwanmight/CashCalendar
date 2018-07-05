package com.might.dwan.cashcalendar.data.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.might.dwan.cashcalendar.data.models.CostItem;

import java.util.ArrayList;
import java.util.List;

public class CostItemCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CostItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CostItem getItem() {
        CostItem item = new CostItem();
        item.setPayItemId(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_ITEM_ID)));
        item.setCategory(getInt(getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY)));
        item.setCategoryText(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_CATEGORY_TEXT)));
        item.setSubcategory(getInt(getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY)));
        item.setSubcategoryText(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_SUBCATEGORY_TEXT)));
        item.setDescription(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_DESCRIPTION)));
        item.setTimestamp(String.valueOf(getInt(getColumnIndex(DBHelper.COLUMN_USER_PAY_DATE)) * 1000L));
        item.setUserId(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_USER_ID)));
        item.setCountPay(getString(getColumnIndex(DBHelper.COLUMN_USER_PAY_COUNT_PAY)));
        return item;
    }

    public List<CostItem> getItems() {
        List<CostItem> list = new ArrayList<>();
        if (getCount() > 0) {
            moveToFirst();
            for (int i = 0; i < getCount(); i++) {
                list.add(getItem());
                moveToNext();
            }
        }
        return list;
    }


}
