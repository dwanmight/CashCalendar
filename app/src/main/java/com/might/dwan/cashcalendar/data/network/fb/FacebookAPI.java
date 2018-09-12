package com.might.dwan.cashcalendar.data.network.fb;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.Profile;
import com.might.dwan.cashcalendar.data.network.ILoginModel;
import com.might.dwan.cashcalendar.data.network.LoginApi;

import javax.inject.Inject;

public class FacebookAPI implements LoginApi {

    private OnTokenChangedListener mTokenListener;

    @Inject
    public FacebookAPI() {
        accessTokenTracker.startTracking();
    }

    public void release() {
        accessTokenTracker.stopTracking();
    }

    public void setTokenListener(OnTokenChangedListener l) {
        this.mTokenListener = l;
    }

    private AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (mTokenListener == null) return;

            String oldToken = getToken(oldAccessToken);
            String newToken = getToken(currentAccessToken);
            if (oldToken != null && newToken != null) {
                if (!oldToken.equals(newToken)) {
                    mTokenListener.onTokenChanged(newToken);
                    return;
                }
            }

            if (oldToken == null && newToken != null) {
                mTokenListener.onNewTokenReceived(newToken);
            } else if (oldToken != null && newToken == null) {
                mTokenListener.onTokenRelease();
            }
        }
    };

    private String getToken(AccessToken accessToken) {
        if (accessToken == null) return null;
        return accessToken.getToken();
    }

    public boolean isNeedLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return !(accessToken != null && !accessToken.isExpired());
    }

    public ILoginModel getFBProfile() {
        AccessToken.getCurrentAccessToken();
        Profile.fetchProfileForCurrentAccessToken();
        return new LoginModelFbAdapter(Profile.getCurrentProfile());
    }

    public interface OnTokenChangedListener {
        void onTokenRelease();

        void onNewTokenReceived(String newToken);

        void onTokenChanged(String newToken);
    }
}
