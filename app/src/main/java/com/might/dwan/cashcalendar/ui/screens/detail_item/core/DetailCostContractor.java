package com.might.dwan.cashcalendar.ui.screens.detail_item.core;

/**
 * Created by Ilya on 06.06.2018.
 */

public interface DetailCostContractor {

    interface IView {

    }

    interface IPresenter {

        void clickDate();

        void getResultDateDialog(long stamp);

        void clickSave();

        void clickBack();
    }
}
