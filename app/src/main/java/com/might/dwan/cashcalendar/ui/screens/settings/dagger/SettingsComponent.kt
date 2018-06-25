package com.might.dwan.cashcalendar.ui.screens.settings.dagger

import com.might.dwan.cashcalendar.ui.screens.settings.SettingsFragment

import dagger.Component

@Component(modules = arrayOf(SettingsModule::class))
interface SettingsComponent {
    fun inject(fragment: SettingsFragment)
}
