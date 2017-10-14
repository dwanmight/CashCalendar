package com.might.dwan.cashcalendar.data.preferences;

import android.content.SharedPreferences;

import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.PayCounterApp;

/**
 * Created by Might on 24.08.2017.
 */

public class Preferences {
    private SharedPreferences mPreferences;

    public Preferences() {
        this.mPreferences = PayCounterApp.getSharedPreferences();
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
