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

        void showMax();



        //Min
        void setMinAmount(String amount);

        void setMinDate(String date);

        void setMinCategory(String category);

        void setMinSubcategory(String subcategory);

        void showMin();



        //Min
        void setSumAmount(String amount);



        //Charts
        void addCharts(ArrayList<ChartInfo> list);

    }
}
