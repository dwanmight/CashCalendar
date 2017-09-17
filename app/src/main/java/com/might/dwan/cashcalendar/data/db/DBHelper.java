package com.might.dwan.cashcalendar.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.might.dwan.cashcalendar.R;

/**
 * Created by Might on 24.08.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "paycounter_database";

    public static final String TABLE_USERS_INFO = "USERS_INFO";
    public static final String COLUMN_USERS_INFO_NICKNAME = "user_id";
    public static final String COLUMN_USERS_INFO_NAME = "name";
    public static final String COLUMN_USERS_INFO_SURNAME = "surname";

    public static final String TABLE_USER_PAY = "USER_PAY";
    public static final String COLUMN_USER_PAY_ITEM_ID = "pay_item_id";
    public static final String COLUMN_USER_PAY_USER_ID = "user_id";
    public static final String COLUMN_USER_PAY_CATEGORY = "category";
    public static final String COLUMN_USER_PAY_CATEGORY_TEXT = "category_text";
    public static final String COLUMN_USER_PAY_SUBCATEGORY = "subcategory";
    public static final String COLUMN_USER_PAY_SUBCATEGORY_TEXT = "subcategory_text";
    public static final String COLUMN_USER_PAY_DESCRIPTION = "description";
    public static final String COLUMN_USER_PAY_COUNT_PAY = "count_pay";
    public static final String COLUMN_USER_PAY_DATE = "timestamp";

    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORIES_ID = "category_id";
    public static final String COLUMN_CATEGORIES_TITLE = "title";

    public static final String TABLE_SUBCATEGORIES = "subcategories";
    public static final String COLUMN_SUBCATEGORIES_ID = "subcategory_id";
    public static final String COLUMN_SUBCATEGORIES_CATEGORY_ID = "category_id";
    public static final String COLUMN_SUBCATEGORIES_CATEGORY_TITLE = "title";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }


    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS_INFO (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id TEXT," +
                "name TEXT," +
                "surname TEXT" +
                ");");

        db.execSQL("CREATE TABLE USER_PAY(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pay_item_id TEXT," +
                "user_id TEXT," +
                "category TEXT," +
                "category_text TEXT," +
                "subcategory TEXT," +
                "subcategory_text TEXT," +
                "description TEXT," +
                "count_pay TEXT," +
                "timestamp INTEGER," +
                "visible BOOLEAN," +
                "FOREIGN KEY (user_id) REFERENCES USERS_INFO (user_id)" +
                ");");

        db.execSQL("create table categories(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "category_id INTEGER ," +
                "title TEXT" +
                ");");

        db.execSQL("create table subcategories(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "category_id INTEGER," +
                "subcategory_id INTEGER," +
                "FOREIGN KEY (category_id) REFERENCES categories (category_id)" +
                ");");

        insertCategories(db);
        insertSubcategories(db);
    }

    private void insertCategories(SQLiteDatabase db) {
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_necessarily) + "\',1);");
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_products) + "\',2);");
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_auto) + "\',3);");
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment) + "\',4);");
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_self) + "\',5);");
        db.execSQL("INSERT INTO categories ('title' , 'category_id') values (\'" + mContext.getString(R.string.db_categories_home) + "\',6);");
    }

    private void insertSubcategories(SQLiteDatabase db) {
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_necessarily_credit) + "\','1');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_necessarily_rent) + "\','1');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_necessarily_internet) + "\','1');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_necessarily_communication) + "\','1');");

        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_meal_fish) + "\','2');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_veg_fruits) + "\','2');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_milk) + "\','2');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_sweet) + "\','2');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_drinks) + "\','2');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_products_rest) + "\','2');");

        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_auto_fuel) + "\','3');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_auto_repair) + "\','3');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_auto_wash) + "\','3');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_auto_insurance) + "\','3');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_auto_tax_penalty) + "\','3');");

        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment_rest) + "\','4');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment_pizza) + "\','4');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment_sushi) + "\','4');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment_films) + "\','4');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_entertainment_coffee) + "\','4');");

        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_self_salon) + "\','5');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_self_clothes) + "\','5');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_self_aid) + "\','5');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_self_cosmetics) + "\','5');");

        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_home_household) + "\','6');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_home_furniture) + "\','6');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_home_technology) + "\','6');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_home_animals) + "\','6');");
        db.execSQL("INSERT INTO subcategories ('title','category_id') values (\'" + mContext.getString(R.string.db_categories_home_repair) + "\','6');");

    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getWritableDatabase() {
        return getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return getReadableDatabase();
    }
}
