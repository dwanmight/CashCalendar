package com.might.dwan.cashcalendar.ui.screens.settings.core

import com.might.dwan.cashcalendar.archs.presenters.BaseIPresenter

interface SettingsContractor {

    interface IPresenter : BaseIPresenter {

        fun clickNotifications()

    }

    interface IView {

        fun goNotificationsSetting()
    }
}
