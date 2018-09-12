package com.might.dwan.cashcalendar.ui.screens.detail_item.core;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdItem;
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Ilya on 06.06.2018.
 */

public class DetailCostModel {

    private CategoryDB categoryDB;
    private SubcategoryDB subcategoryDB;
    private PayCounterDB payCounterDB;
    private DBHelper dbHelper;
    private WeakReference<DetailCostFragment> fragment;

    public DetailCostModel(CategoryDB categoryDB, SubcategoryDB subcategoryDB, PayCounterDB
            payCounterDB, DBHelper dbHelper, DetailCostFragment fragment) {
        this.categoryDB = categoryDB;
        this.subcategoryDB = subcategoryDB;
        this.payCounterDB = payCounterDB;
        this.dbHelper = dbHelper;
        this.fragment = new WeakReference<>(fragment);
    }


    Observable<ArrayList<NameIdItem>> loadCategories() throws Exception {
        return Observable.zip(
                Observable.just(categoryDB),
                Observable.just(dbHelper),
                (db, helper) -> db.getCategories(helper.getReadableDatabase()));
    }

    Observable<ArrayList<NameIdItem>> loadSubcategories(int category) {
        return Observable.just(category).map(this::getSubcategoriesForId);
    }

    void createCost(CostItem item) throws Exception {
        payCounterDB.insert(dbHelper.getReadableDatabase(), item);
    }

    void updateCost(CostItem item) throws Exception {
        payCounterDB.update(dbHelper.getReadableDatabase(), item);
    }


    private ArrayList<NameIdItem> getSubcategoriesForId(int id) throws Exception {
        return subcategoryDB.getSubCategories(dbHelper.getReadableDatabase(), id);
    }


    //window region
    void goBack(boolean result) {
        fragment.get().goBack(result);
    }

    void showDatePicker(String timestamp) {
        fragment.get().showDatePickDialog(timestamp);
    }

    void showErrorPickCategory() {
        fragment.get().showErrorPickCategory();
    }

    void showErrorPickSubcategory() {
        fragment.get().showErrorPickSubcategory();
    }

    void showErrorEnterAmount() {
        fragment.get().showErrorEnterAmount();
    }
}
