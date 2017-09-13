package com.might.dwan.cashcalendar.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.db_models.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_models.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_models.SubcategoryDB;
import com.might.dwan.cashcalendar.data.manager.PreferencesManager;
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

public class DetailPayFragment extends BaseFragment implements View.OnClickListener {

    public static final int MODE_NEW = 0;
    public static final int MODE_DETAIL = 1;
    private Spinner mCategorySpinner, mSubcategorySpinner;
    private EditText mDescriptionEt, mPayEt;
    private TextView mDateTv;
    private SpinnerAdapter mCategoryAdapter, mSubcategoryAdapter;
    private ArrayList<NameIdModel> mCategoryList, mSubcategoryList;

    private DatePickerDialog mDatePickerDialog;

    private int mCategoryId = 1;
    private int mSubCategoryId;
    private String mCategoryText;
    private String mSubcategoryText;
    private long mTimeStamp;

    private AdapterView.OnItemSelectedListener mCategorySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            changeViewColor(view);
            mCategoryId = mCategoryAdapter.getId(position);
            loadSubcategories();
            mCategoryText = mCategoryList.get(position).getName();
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener mSubCategorySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            changeViewColor(view);
            mSubCategoryId = mSubcategorySpinner.getSelectedItemPosition();
            mSubcategoryText = mSubcategoryList.get(position).getName();
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

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_pay, container, false);
        restoreData(savedInstanceState);
        initViews(v);
        setupData();
        setupAdapters();
        loadCategories();
        loadSubcategories();
        return v;
    }

    private void loadCategories() {
        mCategoryList.clear();
        mCategoryList.add(new NameIdModel("Ничего не выбрано", 0));
        mCategoryList.addAll(new CategoryDB(getActivity()).getCategories());
        mCategoryAdapter.notifyDataSetChanged();
    }

    private void loadSubcategories() {
        mSubcategoryList.clear();
        mSubcategoryList.add(new NameIdModel("Ничего не выбрано", 0));
        mSubcategoryList.addAll(new SubcategoryDB(getActivity()).getSubCategories(mCategoryId));
        mSubcategoryAdapter.notifyDataSetChanged();
    }

    private void restoreData(Bundle savedInstanceState) {
        // TODO: 27.08.2017
    }

    private void initViews(View v) {
        mCategorySpinner = (Spinner) v.findViewById(R.id.category_spinner);
        mSubcategorySpinner = (Spinner) v.findViewById(R.id.subcategory_spinner);
        mDescriptionEt = (EditText) v.findViewById(R.id.detail_description_et);
        mDateTv = (TextView) v.findViewById(R.id.detail_date_tv);
        mPayEt = (EditText) v.findViewById(R.id.detail_pay_et);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("Добавить запись");
    }

    private void setupData() {
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

    @Override public void onStart() {
        super.onStart();
        setListeners();
    }

    private void setListeners() {
        mDateTv.setOnClickListener(this);
        mCategorySpinner.setOnItemSelectedListener(mCategorySelectedListener);
        mSubcategorySpinner.setOnItemSelectedListener(mSubCategorySelectedListener);
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
            default:
                return false;
        }
    }

    private void save() {
        if (isDataValid()) {
            if (getArguments().getInt(ConstantManager.EXTRA_MODE) == MODE_NEW) {
                saveToDB();
            } else {
                updateItem();
            }

            getActivity().onBackPressed();
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

    private void showToast(String mes) {
        Toast.makeText(getActivity(), mes, Toast.LENGTH_SHORT).show();
    }

    private void saveToDB() {
        PayCounterModel model = new PayCounterModel(PreferencesManager.get(getActivity()).getPreferences().getNickname()
                , mCategorySpinner.getSelectedItemPosition()
                , mCategoryText
                , mSubcategorySpinner.getSelectedItemPosition()
                , mSubcategoryText
                , mDescriptionEt.getText().toString().trim()
                , mPayEt.getText().toString().trim()
                , String.valueOf(System.currentTimeMillis()));
        PayCounterDB payCounterDB = new PayCounterDB(getActivity());
        payCounterDB.insert(model);
    }

    private void updateItem() {
        PayCounterModel model = new PayCounterModel(PreferencesManager.get(getActivity()).getPreferences().getNickname()
                , mCategorySpinner.getSelectedItemPosition()
                , mCategoryText
                , mSubcategorySpinner.getSelectedItemPosition()
                , mSubcategoryText
                , mDescriptionEt.getText().toString().trim()
                , mPayEt.getText().toString()
                , String.valueOf(System.currentTimeMillis()));
        PayCounterDB payCounterDB = new PayCounterDB(getActivity());
        payCounterDB.update(model);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_date_tv:
                showDatePickDialog();
                break;
        }
    }

    private void showDatePickDialog() {
        mDatePickerDialog = DatePickerDialog.newInstance(mTimeStamp);
        mDatePickerDialog.setTargetFragment(this, ConstantManager.REQUEST_DATE_DIALOG);
        mDatePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantManager.REQUEST_DATE_DIALOG:
                getResultDialogDate(resultCode, data);
                break;
        }
    }

    private void getResultDialogDate(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mTimeStamp = data.getLongExtra(ConstantManager.EXTRA_STAMP, 0);
            updateDateView();
        }
    }

    private void updateDateView() {
        if (mTimeStamp == 0) return;
        mDateTv.setText(DateUtils.stampToYMD(mTimeStamp));
    }
}
