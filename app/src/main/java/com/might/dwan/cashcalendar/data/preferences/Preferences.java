package com.might.dwan.cashcalendar.data.preferences;

import android.content.SharedPreferences;

import com.might.dwan.cashcalendar.utils.ConstantManager;

import javax.inject.Inject;

/**
 * Created by Might on 24.08.2017.
 */

public class Preferences {
    private SharedPreferences mPreferences;

    @Inject
    public Preferences(SharedPreferences preferences) {
        this.mPreferences = preferences;
    }

    public void saveNickname(String nickname) {
        mPreferences.edit().putString(ConstantManager.PREF_NICKNAME, nickname).apply();
    }

    public String getNickname() {
        return mPreferences.getString(ConstantManager.PREF_NICKNAME, null);
    }

    public void savePhotoPath(String path) {
        mPreferences.edit().putString(ConstantManager.PREF_NICKNAME, path).apply();
    }

    public String getPhotoPath() {
        return mPreferences.getString(ConstantManager.PREF_NICKNAME, null);
    }
}
