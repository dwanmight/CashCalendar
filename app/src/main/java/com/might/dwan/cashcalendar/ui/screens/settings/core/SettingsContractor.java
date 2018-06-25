package com.might.dwan.cashcalendar.ui.screens.settings.core;

import com.might.dwan.cashcalendar.archs.presenters.BaseIPresenter;

public interface SettingsContractor {

    interface IPresenter extends BaseIPresenter {

        void clickNotifications();

    }

    interface IView {

        void goNotificationsSetting();
    }
}
