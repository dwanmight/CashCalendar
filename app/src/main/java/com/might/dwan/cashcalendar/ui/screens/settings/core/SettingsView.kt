package com.might.dwan.cashcalendar.ui.screens.settings.core

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.might.dwan.cashcalendar.R

class SettingsView(root: View?) {

    private var notificationsTv: TextView? = root?.findViewById(R.id.settings_notifications_tv)
    val toolbar: Toolbar? = root?.findViewById(R.id.toolbar)

    init {
        toolbar?.title = "Settings"
    }
}
