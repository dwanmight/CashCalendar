package com.might.dwan.cashcalendar.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Might on 13.09.2017.
 */

public abstract class BaseFragment extends Fragment {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable @Override public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                       Bundle state) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @LayoutRes public abstract int getLayoutId();

    @Override public final void onViewCreated(View view, @Nullable Bundle state) {
        initUI(view);
        restoreState(state);
        setupData();
    }

    public void initUI(View view) {}

    public void restoreState(Bundle state) {}

    public void setupData() {}

    @Override public final void onDestroyView() {
        super.onDestroyView();
        releaseData();
    }

    public void releaseData() {}



    @CallSuper @Override public void onResume() {
        setListeners(true);
        super.onResume();
    }


    /**
     * Method for bind and release listeners
     *
     * @param enable TRUE if should bind and
     *               FALSE to release
     */
    public void setListeners(boolean enable) {}

    @CallSuper @Override public void onStart() {
        setListeners(false);
        super.onStart();
    }


    public void showToast(String mess) {
        if (mess == null) return;
        if (getActivity() == null) return;
        if (!isAdded()) return;
        getActivity().runOnUiThread(() ->
                Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show());
    }

    public void setToolbar(Toolbar toolbar) {
        if (toolbar == null) return;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
