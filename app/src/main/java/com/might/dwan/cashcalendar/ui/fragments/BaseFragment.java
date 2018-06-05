package com.might.dwan.cashcalendar.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by Might on 13.09.2017.
 */

public class BaseFragment extends Fragment {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    public void showToast(String mess) {
        if (mess == null) return;
        if (getActivity() == null) return;
        if (!isAdded()) return;
        getActivity().runOnUiThread(() ->
                Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show());
    }

    public void setToolbar(Toolbar toolbar){
        if(toolbar==null)return;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
