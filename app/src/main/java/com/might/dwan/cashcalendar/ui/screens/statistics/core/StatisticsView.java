package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;

/**
 * Created by ilya on 13.06.2018.
 */

public class StatisticsView
        implements StatisticsContractor.IView {
    private View v;
    private TextView maxDateTv, maxAmountTv, maxCategoryTv, maxSubcategoryTv; //max views
    private TextView minDateTv, minAmountTv, minCategoryTv, minSubcategoryTv; //min views
    private TextView sumAmountTv; //sum views


    public StatisticsView(Fragment fragment) {
        v = fragment.getView();

        ItemVH vh = new ItemVH(v.findViewById(R.id.statistics_max_root_view));
        maxDateTv = vh.initDate();
        maxAmountTv = vh.initAmount();
        maxCategoryTv = vh.initCategory();
        maxSubcategoryTv = vh.initSubcategory();

        vh = new ItemVH(v.findViewById(R.id.statistics_min_root_view));
        minDateTv = vh.initDate();
        minAmountTv = vh.initAmount();
        minCategoryTv = vh.initCategory();
        minSubcategoryTv = vh.initSubcategory();

        sumAmountTv = v.findViewById(R.id.statistics_sum_tv);
    }



    //Max region
    @Override public void setMaxAmount(String amount) {
        maxAmountTv.setText(getAmount(amount));
    }

    @Override public void setMaxDate(String date) {
        maxDateTv.setText(date);
    }

    @Override public void setMaxCategory(String category) {
        maxCategoryTv.setText(category);
    }

    @Override public void setMaxSubcategory(String subcategory) {
        maxSubcategoryTv.setText(subcategory);
    }



    //Min region
    @Override public void setMinAmount(String amount) {
        minAmountTv.setText(getAmount(amount));
    }

    @Override public void setMinDate(String date) {
        minDateTv.setText(date);
    }

    @Override public void setMinCategory(String category) {
        minCategoryTv.setText(category);
    }

    @Override public void setMinSubcategory(String subcategory) {
        minSubcategoryTv.setText(subcategory);
    }



    //Sum region
    @Override public void setSumAmount(String amount) {
        sumAmountTv.setText(getAmount(amount));
    }

    private String getAmount(String amount) {
        return v.getResources().getString(R.string.value_type_uah, amount);
    }
}
