package com.might.dwan.cashcalendar.ui.screens.settings.dagger

import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsContractor
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsModel
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsPresenter
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(var view: SettingsContractor.IView) {

    @SettingScope
    @Provides
    fun provideView(): SettingsContractor.IView = view


    @SettingScope
    @Provides

    fun providePresenter(view: SettingsContractor.IView, model: SettingsModel): SettingsContractor.IPresenter {
        return SettingsPresenter(view, model)
    }

    @SettingScope
    @Provides
    fun provideModel(): SettingsModel {
        return SettingsModel()
    }
}
