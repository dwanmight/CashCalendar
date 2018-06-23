package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import android.view.View;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.views.ChartContainer;

import java.util.ArrayList;

/**
 * Created by ilya on 13.06.2018.
 */

public class StatisticsView
        implements StatisticsContractor.IView {
    private View v, maxRootView, minRootView;
    private TextView maxLabelTv, maxDateTv, maxAmountTv, maxCategoryTv, maxSubcategoryTv; //max
    private TextView minLabelTv, minDateTv, minAmountTv, minCategoryTv, minSubcategoryTv; //min
    private TextView sumLabelTv, sumAmountTv; //sum views
    private ChartContainer chartsView;

    public StatisticsView(View root) {
        v = root;

        maxRootView = v.findViewById(R.id.statistics_max_root_view);
        maxLabelTv = v.findViewById(R.id.statistics_max_label_tv);
        ItemVH vh = new ItemVH(maxRootView);
        maxDateTv = vh.initDate();
        maxAmountTv = vh.initAmount();
        maxCategoryTv = vh.initCategory();
        maxSubcategoryTv = vh.initSubcategory();

        minRootView = v.findViewById(R.id.statistics_min_root_view);
        minLabelTv = v.findViewById(R.id.statistics_min_label_tv);
        vh = new ItemVH(minRootView);
        minDateTv = vh.initDate();
        minAmountTv = vh.initAmount();
        minCategoryTv = vh.initCategory();
        minSubcategoryTv = vh.initSubcategory();

        sumAmountTv = v.findViewById(R.id.statistics_sum_tv);
        sumLabelTv = v.findViewById(R.id.statistics_sum_label_tv);

        chartsView = v.findViewById(R.id.statistics_charts_view);
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

    @Override public void hideMax() {
        maxRootView.setVisibility(View.GONE);
        maxLabelTv.setVisibility(View.GONE);
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

    @Override public void hideMin() {
        minRootView.setVisibility(View.GONE);
        minLabelTv.setVisibility(View.GONE);

    }



    //Sum region
    @Override public void setSumAmount(String amount) {
        sumAmountTv.setText(getAmount(amount));
    }

    @Override public void hideSum() {
        sumLabelTv.setVisibility(View.GONE);
        sumAmountTv.setVisibility(View.GONE);
    }

    private String getAmount(String amount) {
        return v.getResources().getString(R.string.value_type_uah, amount);
    }



    //Charts region
    @Override public void addCharts(ArrayList<ChartInfo> list) {
        chartsView.addCharts(list);
    }

    @Override public void hideCharts() {
        chartsView.setVisibility(View.GONE);
    }
}
