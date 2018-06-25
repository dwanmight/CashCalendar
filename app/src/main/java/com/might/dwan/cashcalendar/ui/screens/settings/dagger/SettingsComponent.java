package com.might.dwan.cashcalendar.ui.screens.settings.dagger;

import com.might.dwan.cashcalendar.ui.screens.settings.SettingsFragment;

import dagger.Component;

@Component(modules = {SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsFragment fragment);
}
