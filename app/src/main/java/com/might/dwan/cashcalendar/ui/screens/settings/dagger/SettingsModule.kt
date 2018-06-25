package com.might.dwan.cashcalendar.ui.screens.settings.dagger

import android.view.View
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsModel
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsPresenter
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsView
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(var view: View?) {


    @SettingScope
    @Provides
    internal fun provideView(): SettingsView {
        return SettingsView(view)
    }


    @SettingScope
    @Provides
    internal fun providePresenter(): SettingsPresenter {
        return SettingsPresenter()
    }

    @SettingScope
    @Provides
    internal fun provideModel(): SettingsModel {
        return SettingsModel()
    }
}
