package com.might.dwan.cashcalendar.ui.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.DBManager;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.DatePickerDialog;
import com.might.dwan.cashcalendar.ui.adapter.SpinnerAdapter;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdModel;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.DateUtils;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Might on 27.08.2017.
 */

public class DetailPayFragment extends BaseFragment
        implements View.OnClickListener {

    public static final int MODE_NEW = 0;
    public static final int MODE_DETAIL = 1;
    private Spinner mCategorySpinner, mSubcategorySpinner;
    private EditText mDescriptionEt, mPayEt;
    private TextView mDateTv;
    private Toolbar mToolbar;

    private SpinnerAdapter mCategoryAdapter, mSubcategoryAdapter;
    private ArrayList<NameIdModel> mCategoryList, mSubcategoryList;

    private DatePickerDialog mDatePickerDialog;

    private int mCurrMode;
    private PayCounterModel mPayCounterModel;

    private AdapterView.OnItemSelectedListener mCategorySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            changeViewColor(view);
            mPayCounterModel.setCategory(mCategoryAdapter.getId(position));
            mPayCounterModel.setCategory_text(mCategoryList.get(position).getName());

            loadSubcategories();
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener mSubCategorySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            changeViewColor(view);
            mPayCounterModel.setSubcategory(mSubcategorySpinner.getSelectedItemPosition());
            mPayCounterModel.setSubcategory_text(mSubcategoryList.get(position).getName());
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void changeViewColor(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    public static DetailPayFragment newInstance(int mode, PayCounterModel model) {
        Bundle args = new Bundle();
        args.putInt(ConstantManager.EXTRA_MODE, mode);
        args.putSerializable(ConstantManager.EXTRA_ITEM, model);

        DetailPayFragment fragment = new DetailPayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public int getLayoutId() {return R.layout.fragment_detail_pay;}

    @Override public void initUI(View v) {
        mCategorySpinner = v.findViewById(R.id.category_spinner);
        mSubcategorySpinner = v.findViewById(R.id.subcategory_spinner);
        mDescriptionEt = v.findViewById(R.id.detail_description_et);
        mDateTv = v.findViewById(R.id.detail_date_tv);
        mPayEt = v.findViewById(R.id.detail_pay_et);

        mToolbar = v.findViewById(R.id.toolbar);
        setToolbar(mToolbar);
    }

    @Override public void restoreState(Bundle state) {
        Bundle bundle = state != null ? state : getArguments();
        mCurrMode = bundle.getInt(ConstantManager.EXTRA_MODE);
        if (mCurrMode == MODE_NEW) {
            mPayCounterModel = new PayCounterModel();
        } else {
            mPayCounterModel = (PayCounterModel) bundle.getSerializable(ConstantManager.EXTRA_ITEM);
        }
    }

    @Override public void setupData() {
        setupAdapters();
        loadCategories();
        loadSubcategories();
        setupMode();
    }

    private void setupAdapters() {
        mCategoryList = new ArrayList<>();
        mSubcategoryList = new ArrayList<>();
        mCategoryAdapter = new SpinnerAdapter(getActivity(), mCategoryList);
        mSubcategoryAdapter = new SpinnerAdapter(getActivity(), mSubcategoryList);

        mCategorySpinner.setAdapter(mCategoryAdapter);
        mSubcategorySpinner.setAdapter(mSubcategoryAdapter);

        mCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void loadCategories() {
        try {
            mCategoryList.clear();
            addEmptyItemToList(mCategoryList);
            mCategoryList.addAll(new CategoryDB().getCategories(DBManager.get(getActivity()).getReadableDatabase()));
            mCategoryAdapter.notifyDataSetChanged();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void loadSubcategories() {
        try {
            mSubcategoryList.clear();
            addEmptyItemToList(mSubcategoryList);
            mSubcategoryList.addAll(new SubcategoryDB().getSubCategories(DBManager.get(getActivity()).getReadableDatabase()
                    , mPayCounterModel.getCategory()));
            mSubcategoryAdapter.notifyDataSetChanged();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void addEmptyItemToList(ArrayList<NameIdModel> list) {
        if (list == null) return;
        list.add(new NameIdModel("Ничего не выбрано", 0));
    }

    private void setupMode() {
        try {
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

            if (mCurrMode == MODE_DETAIL) {
                mToolbar.setTitle(R.string.title_edit);
                mCategorySpinner.setSelection(mPayCounterModel.getCategory());
                mDescriptionEt.setText(mPayCounterModel.getDescription());

                mPayEt.setText(mPayCounterModel.getCount_pay());
                mPayEt.setHint(getResources().getString(R.string.hint_detail_description_empty));

                mDateTv.setText(DateUtils.stampToYMDHMS(Long.parseLong(mPayCounterModel.getTimestamp())));

                loadSubcategories();
                mSubcategorySpinner.setSelection(mPayCounterModel.getSubcategory());
            } else {
                mToolbar.setTitle(R.string.title_add_note);
            }
        } catch (Exception e) {e.printStackTrace();}
    }


    @Override public void setListeners(boolean enable) {
        if(enable){
            mDateTv.setOnClickListener(this);
            mCategorySpinner.setOnItemSelectedListener(mCategorySelectedListener);
            mSubcategorySpinner.setOnItemSelectedListener(mSubCategorySelectedListener);
        }else {
            mDateTv.setOnClickListener(null);
            mCategorySpinner.setOnItemSelectedListener(null);
            mSubcategorySpinner.setOnItemSelectedListener(null);
        }
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_pay, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_done:
                save();
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return false;
    }

    private void save() {
        if (isDataValid()) {
            if (mCurrMode == MODE_NEW) {
                saveToDB();
            } else {
                updateItem();
            }
            getActivity().setResult(RESULT_OK);
            getActivity().finish();
        }
    }

    private boolean isDataValid() {
        if (mCategorySpinner.getSelectedItemPosition() == 0) {
            showToast("Выберите категорию");
            return false;
        }
        if (mSubcategorySpinner.getSelectedItemPosition() == 0) {
            showToast("Выберите подкатегорию");
            return false;
        }
        if (mPayEt.getText().toString().equals("")) {
            showToast("Укажите сумму");
            return false;
        }
        return true;
    }

    private void saveToDB() {
        try {
            mPayCounterModel.setDescription(mDescriptionEt.getText().toString().trim());
            mPayCounterModel.setCount_pay(mPayEt.getText().toString().trim());
            PayCounterDB payCounterDB = new PayCounterDB();
            payCounterDB.insert(DBManager.get(getActivity()).getReadableDatabase(), mPayCounterModel);
        } catch (Exception e) {e.printStackTrace();}
    }

    private void updateItem() {
        try {
            mPayCounterModel.setDescription(mDescriptionEt.getText().toString().trim());
            mPayCounterModel.setCount_pay(mPayEt.getText().toString());
            PayCounterDB payCounterDB = new PayCounterDB();
            payCounterDB.update(DBManager.get(getActivity()).getReadableDatabase(), mPayCounterModel);
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_date_tv:
                showDatePickDialog();
                break;
        }
    }

    private void showDatePickDialog() {
        mDatePickerDialog = DatePickerDialog.newInstance(mPayCounterModel.getTimestamp());
        mDatePickerDialog.setTargetFragment(this, ConstantManager.REQUEST_DATE_DIALOG);
        mDatePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ConstantManager.EXTRA_MODE, mCurrMode);
    }



    //Activity result dialog
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean result = resultCode == Activity.RESULT_OK;
        switch (requestCode) {
            case ConstantManager.REQUEST_DATE_DIALOG:
                getResultDialogDate(result, data);
                break;
        }
    }

    private void getResultDialogDate(boolean result, Intent data) {
        if (!result) return;
        mPayCounterModel.setTimestamp(String.valueOf(data.getLongExtra(ConstantManager.EXTRA_STAMP,
                0)));
        updateDateView();
    }

    private void updateDateView() {
        if (mPayCounterModel.getTimestamp() == null) return;
        mDateTv.setText(DateUtils.stampToYMD(Long.parseLong(mPayCounterModel.getTimestamp())));
    }
}
