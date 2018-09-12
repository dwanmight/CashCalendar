package com.might.dwan.cashcalendar.ui.screens.update_profile.dagger;

import com.might.dwan.cashcalendar.apps.AppComponent;
import com.might.dwan.cashcalendar.ui.dialogs.PhotoDialog;
import com.might.dwan.cashcalendar.ui.screens.update_profile.UpdateProfileFragment;

import dagger.Component;

@UpdateProfileScope
@Component(dependencies = {AppComponent.class})
public interface UpdateProfileComponent {

    void inject(UpdateProfileFragment fragment);

    void inject(PhotoDialog photoDialog);
}
