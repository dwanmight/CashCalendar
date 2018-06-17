package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.might.dwan.cashcalendar.R;

/**
 * Created by ilya on 13.06.2018.
 */

public class StatisticsView
        implements StatisticsContractor.IView {

    private RecyclerView mRecyclerView;

    public StatisticsView(Fragment fragment) {
        View v = fragment.getView();
        mRecyclerView = v.findViewById(R.id.recyclerView);
    }
}
