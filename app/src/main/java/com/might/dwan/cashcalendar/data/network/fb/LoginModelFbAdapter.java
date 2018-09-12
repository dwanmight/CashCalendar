package com.might.dwan.cashcalendar.data.network.fb;

import com.facebook.Profile;
import com.might.dwan.cashcalendar.data.network.ILoginModel;

public class LoginModelFbAdapter implements ILoginModel {
    private Profile profile;

    public LoginModelFbAdapter(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String getName() {
        if (profile == null) return "";
        return profile.getName();
    }

    @Override
    public String getSurname() {
        if (profile == null) return "";
        return profile.getMiddleName();
    }

    @Override
    public boolean isValid() {
        return profile != null;
    }
}
