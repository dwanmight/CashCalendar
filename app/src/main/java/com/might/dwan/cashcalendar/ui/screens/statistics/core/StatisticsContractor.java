package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import java.util.ArrayList;

/**
 * Created by ilya on 13.06.2018.
 */

public interface StatisticsContractor {

    interface IPresenter {
    }

    interface IView {

        //Max
        void setMaxAmount(String amount);

        void setMaxDate(String date);

        void setMaxCategory(String category);

        void setMaxSubcategory(String subcategory);

        void hideMax();



        //Min
        void setMinAmount(String amount);

        void setMinDate(String date);

        void setMinCategory(String category);

        void setMinSubcategory(String subcategory);

        void hideMin();



        //Min
        void setSumAmount(String amount);

        void hideSum();



        //Charts
        void addCharts(ArrayList<ChartInfo> list);

        void hideCharts();
    }
}
