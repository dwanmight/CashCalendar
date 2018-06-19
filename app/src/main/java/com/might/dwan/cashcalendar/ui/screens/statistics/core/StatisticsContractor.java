package com.might.dwan.cashcalendar.ui.screens.statistics.core;

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



        //Min
        void setMinAmount(String amount);

        void setMinDate(String date);

        void setMinCategory(String category);

        void setMinSubcategory(String subcategory);


        //Min
        void setSumAmount(String amount);
    }
}
