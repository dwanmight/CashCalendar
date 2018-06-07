package com.might.dwan.cashcalendar.ui.screens.detail_item.core;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.adapter.SpinnerAdapter;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdItem;
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment;
import com.might.dwan.cashcalendar.ui.screens.detail_item.list.SpinnerSelectedListener;
import com.might.dwan.cashcalendar.utils.EditTextUtils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Ilya on 06.06.2018.
 */

public class DetailCostView {
    private View root;
    private Spinner mCategorySpinner, mSubcategorySpinner;
    private EditText mDescriptionEt, mPayEt;
    private TextView mDateTv;
    private Toolbar mToolbar;
    private TextView mTitleTv;

    private SpinnerAdapter mCategoryAdapter, mSubcategoryAdapter;

    private PublishSubject<Integer> mCategoryClicks = PublishSubject.create();
    private PublishSubject<Integer> mSubCategoryClicks = PublishSubject.create();

    private DetailCostContractor.IPresenter mClickListener;

    public Observable<Integer> getCategoryClicks() {
        return mCategoryClicks;
    }

    public Observable<Integer> getSubcategoryClicks() {
        return mSubCategoryClicks;
    }

    private SpinnerSelectedListener mCategorySelectedListener = new SpinnerSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
            mCategoryClicks.onNext(pos);
            changeViewColor(v);
        }
    };

    private SpinnerSelectedListener mSubCategorySelectedListener = new SpinnerSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
            mSubCategoryClicks.onNext(pos);
            changeViewColor(v);
        }
    };

    private void changeViewColor(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(view.getResources().getColor(R.color.colorBlack));
        }
    }

    public DetailCostView(DetailCostFragment fragment) {
        root = fragment.getView();
        mCategorySpinner = root.findViewById(R.id.category_spinner);
        mSubcategorySpinner = root.findViewById(R.id.subcategory_spinner);
        mDescriptionEt = root.findViewById(R.id.detail_description_et);
        mDateTv = root.findViewById(R.id.detail_date_tv);
        mPayEt = root.findViewById(R.id.detail_pay_et);

        mToolbar = root.findViewById(R.id.toolbar);
        mTitleTv = mToolbar.findViewById(R.id.toolbar_title_tv);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        fragment.setToolbar(mToolbar);

        mPayEt.setHint(root.getResources().getString(R.string.hint_detail_description_empty));
    }

    void bindClickListener(DetailCostContractor.IPresenter presenter) {
        //        mClickListener = presenter;
        if (presenter != null) {
            mDateTv.setOnClickListener(v -> presenter.clickDate());
            mCategorySpinner.setOnItemSelectedListener(mCategorySelectedListener);
            mSubcategorySpinner.setOnItemSelectedListener(mSubCategorySelectedListener);
        } else {
            mDateTv.setOnClickListener(null);
            mCategorySpinner.setOnItemSelectedListener(null);
            mSubcategorySpinner.setOnItemSelectedListener(null);
        }
    }



    //setup ui
    void setupToolbarForCreate() {
        mTitleTv.setText(R.string.title_add_note);
    }

    void setupToolbarForUpdate() {
        mTitleTv.setText(R.string.title_edit);
    }



    //spinner region
    void setupCategoryAdapter(ArrayList<NameIdItem> list) {
        mCategoryAdapter = new SpinnerAdapter(root.getContext(), list);
        mCategorySpinner.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    void setupSubCategoryAdapter(ArrayList<NameIdItem> list) {
        mSubcategoryAdapter = new SpinnerAdapter(root.getContext(), list);
        mSubcategorySpinner.setAdapter(mSubcategoryAdapter);
        mSubcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    void adapterSubcategoryChanged() {
        mSubcategoryAdapter.notifyDataSetChanged();
    }

    void adapterCategoryChanged() {
        mCategoryAdapter.notifyDataSetChanged();
    }

    void spinnerSetCategory(int pos) {
        mCategorySpinner.setSelection(pos);
    }

    void spinnerSetSubCategory(int pos) {
        mSubcategorySpinner.setSelection(pos);
    }



    //set/get data region
    void setDate(String date) {
        mDateTv.setText(date);
    }

    void setDescription(@NonNull String desc) {
        mDescriptionEt.setText(desc);
        mDescriptionEt.setSelection(desc.length());
    }

    String getDescription() {
        return EditTextUtils.getText(mDescriptionEt);
    }

    void setPay(@NonNull String pay) {
        mPayEt.setText(pay);
        mPayEt.setSelection(pay.length());
    }

    String getPay() {
        return EditTextUtils.getText(mPayEt);
    }

    int getCategorySelectedPos() {
        return mCategorySpinner.getSelectedItemPosition();
    }

    int getSubcategorySelectedPos() {
        return mSubcategorySpinner.getSelectedItemPosition();
    }

}
