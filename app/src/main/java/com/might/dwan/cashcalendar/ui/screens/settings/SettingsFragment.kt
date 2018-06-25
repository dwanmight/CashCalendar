package com.might.dwan.cashcalendar.ui.screens.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.might.dwan.cashcalendar.R
import com.might.dwan.cashcalendar.ui.screens.BaseFragment
import com.might.dwan.cashcalendar.ui.screens.settings.core.SettingsContractor
import com.might.dwan.cashcalendar.ui.screens.settings.dagger.DaggerSettingsComponent
import com.might.dwan.cashcalendar.ui.screens.settings.dagger.SettingsModule
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.include_app_bar_layout.*
import javax.inject.Inject

class SettingsFragment : BaseFragment(), SettingsContractor.IView {

    @Inject lateinit var presenter: SettingsContractor.IPresenter

    override fun getLayoutId() = R.layout.fragment_settings

    override fun setupData(state: Bundle?) {
        DaggerSettingsComponent.builder()
                .settingsModule(SettingsModule(this))
                .build()
                .inject(this)


        setToolbar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun releaseData() {
        presenter.release()
    }

    override fun setListeners(enable: Boolean) {
        if (enable) {
            settings_notifications_tv.setOnClickListener { presenter.clickNotifications() }
        } else {
            settings_notifications_tv.setOnClickListener(null)
        }
    }

    override fun goNotificationsSetting() {
    }

}
