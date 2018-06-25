package com.might.dwan.cashcalendar.ui.screens.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.might.dwan.cashcalendar.R
import com.might.dwan.cashcalendar.ui.screens.BaseFragment
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsPresenter
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsView
import com.might.dwan.cashcalendar.ui.screens.settings.dagger.DaggerSettingsComponent
import com.might.dwan.cashcalendar.ui.screens.settings.dagger.SettingsModule
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject lateinit var view: SettingsView
    @Inject lateinit var presenter: SettingsPresenter

    override fun getLayoutId() = R.layout.fragment_settings

    override fun setupData(state: Bundle?) {
        DaggerSettingsComponent.builder()
                .settingsModule(SettingsModule(getView()))
                .build()
                .inject(this)

        setToolbar(view.toolbar)

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
