package com.might.dwan.cashcalendar.ui.screens.settings.core

import com.might.dwan.cashcalendar.archs.presenters.BasePresenter

class SettingsPresenter(v: SettingsContractor.IView,
                        model: SettingsModel
) : BasePresenter<SettingsContractor.IView, Any>(v),
        SettingsContractor.IPresenter {

    init {
        bindModel(model)
    }

    override fun updateView() {

    }

    override fun clickNotifications() {
        view()?.goNotificationsSetting()
    }
}
