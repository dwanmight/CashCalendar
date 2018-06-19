package com.might.dwan.cashcalendar.ui.screens.detail_item.core;

import android.os.Bundle;

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdItem;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.DateUtils;
import com.might.dwan.cashcalendar.utils.ValidUtils;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ilya on 06.06.2018.
 */

public class DetailCostPresenter extends BasePresenter<DetailCostView, DetailCostModel>
        implements DetailCostContractor.IPresenter {
    public static final int MODE_NEW = 0;
    public static final int MODE_DETAIL = 1;

    private ArrayList<NameIdItem> mCategoryList, mSubcategoryList;
    private RxSchedulers mRxSchedulers;
    private CostItem mCostItem;
    private CompositeDisposable mSubscribers = new CompositeDisposable();

    private int mMode;


    public DetailCostPresenter(DetailCostView view, DetailCostModel model,
                               RxSchedulers schedulers, CostItem item) {
        super(view);
        mSubscribers = new CompositeDisposable();
        mCategoryList = new ArrayList<>();
        mSubcategoryList = new ArrayList<>();
        mRxSchedulers = schedulers;
        mMode = item == null ? MODE_NEW : MODE_DETAIL;
        mCostItem = item == null ? new CostItem() : item;
        bindModel(model);
    }

    @Override public void updateView() {
        view().setupCategoryAdapter(mCategoryList);
        view().setupSubCategoryAdapter(mSubcategoryList);
        view().bindClickListener(this);
        mSubscribers.add(view().getCategoryClicks()
                .subscribe(this::selectCategoryByPos, Throwable::printStackTrace));
        mSubscribers.add(view().getSubcategoryClicks()
                .subscribe(this::selectSubcategoryByPos, Throwable::printStackTrace));

        loadCategories();
        setupDataForMode();
    }

    private void selectCategoryByPos(int pos) {
        mCostItem.setCategory(mCategoryList.get(pos).getId());
        mCostItem.setCategoryText(mCategoryList.get(pos).getName());
        loadSubcategories();
    }

    private void loadCategories() {
        try {
            mSubscribers.add(model.loadCategories()
                    .subscribeOn(mRxSchedulers.io())
                    .observeOn(mRxSchedulers.androidThread())
                    .subscribe(list -> {
                                mCategoryList.clear();
                                addEmptyItemToList(mCategoryList);
                                mCategoryList.addAll(list);
                            },
                            Throwable::printStackTrace,
                            () -> {
                                view().adapterCategoryChanged();
                                view().spinnerSetCategory(mCostItem.getCategory());
                            }));

        } catch (Exception e) {e.printStackTrace();}
    }

    private void loadSubcategories() {
        try {
            mSubscribers.add(Observable.just(mCostItem.getCategory())
                    .subscribeOn(mRxSchedulers.io())
                    .flatMap(id -> model.loadSubcategories(id))
                    .observeOn(mRxSchedulers.androidThread())
                    .subscribe(list -> {
                                mSubcategoryList.clear();
                                addEmptyItemToList(mSubcategoryList);
                                mSubcategoryList.addAll(list);
                            },
                            Throwable::printStackTrace,
                            () -> {
                                view().adapterSubcategoryChanged();
                                view().spinnerSetSubCategory(mCostItem.getSubcategory());
                            }));
        } catch (Exception e) {e.printStackTrace();}
    }

    private void addEmptyItemToList(ArrayList<NameIdItem> list) {
        if (list == null) return;
        list.add(new NameIdItem("Ничего не выбрано", 0));
    }

    private void selectSubcategoryByPos(int pos) {
        mCostItem.setSubcategory(mSubcategoryList.get(pos).getId());
        mCostItem.setSubcategoryText(mSubcategoryList.get(pos).getName());
    }

    private void setupDataForMode() {
        if (mMode == MODE_NEW) {
            mCostItem.setTimestamp(String.valueOf(System.currentTimeMillis()));
            view().setupToolbarForCreate();
        } else {
            view().setupToolbarForUpdate();
            setDateToView();
            setDescriptionToView();
            setAmountToView();
        }
    }

    @Override public void onRelease() {
        view().bindClickListener(null);
        mSubscribers.dispose();
    }

    @Override public void onSaveState(Bundle bundle) {
        bundle.putSerializable(ConstantManager.EXTRA_ITEM, mCostItem);
    }



    //Click region
    @Override public void clickDate() {
        model.showDatePicker(mCostItem.getTimestamp());
    }

    @Override public void clickSave() {
        checkSave();
    }

    private void checkSave() {
        if (isDataValid()) {
            collectDataFromView();
            if (mMode == MODE_NEW) {
                saveToDB();
            } else {
                updateItem();
            }
            model.goBack(true);
        }
    }

    private void collectDataFromView() {
        mCostItem.setDescription(view().getDescription());
        mCostItem.setCountPay(view().getPay());
    }

    private void saveToDB() {
        try {
            model.createCost(mCostItem);
        } catch (Exception e) {e.printStackTrace();}
    }

    private void updateItem() {
        try {
            model.updateCost(mCostItem);
        } catch (Exception e) {e.printStackTrace();}
    }

    private boolean isDataValid() {
        int categoryPos = view().getCategorySelectedPos();
        int subcategoryPos = view().getSubcategorySelectedPos();
        if (categoryPos == 0) {
            model.showErrorPickCategory();
            return false;
        }
        if (subcategoryPos == 0) {
            model.showErrorPickSubcategory();
            return false;
        }
        if (!ValidUtils.isTextValid(view().getPay())) {
            model.showErrorEnterAmount();
            return false;
        }
        return true;
    }

    @Override public void clickBack() {
        model.goBack(false);
    }



    //Result region
    @Override public void getResultDateDialog(long stamp) {
        mCostItem.setTimestamp(String.valueOf(stamp));
        setDateToView();
    }



    //setup view region
    private void setDateToView() {
        try {
            view().setDate(DateUtils.stampToYMDHMS(Long.parseLong(mCostItem.getTimestamp())));
        } catch (Exception e) {e.printStackTrace();}
    }

    private void setDescriptionToView() {
        if (!ValidUtils.isTextValid(mCostItem.getDescription())) return;
        view().setDescription(mCostItem.getDescription());
    }

    private void setAmountToView() {
        if (!ValidUtils.isTextValid(mCostItem.getCountPay())) return;
        view().setPay(mCostItem.getCountPay());
    }

}
