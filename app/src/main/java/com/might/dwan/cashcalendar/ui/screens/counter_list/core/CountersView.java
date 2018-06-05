package com.might.dwan.cashcalendar.ui.screens.counter_list.core;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.adapter.PayCounterAdapter;
import com.might.dwan.cashcalendar.ui.adapter.decoration.SpaceDecoration;
import com.might.dwan.cashcalendar.ui.interfaces.OnItemPickListener;
import com.might.dwan.cashcalendar.ui.screens.counter_list.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.counter_list.interactor.CountersClickListeners;
import com.might.dwan.cashcalendar.utils.DisplayUtils;

import java.util.ArrayList;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersView
        implements OnItemPickListener<PayCounterModel> {

    private View rootView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PayCounterAdapter mAdapter;

    public CountersView(CountersFragment fragment) {
        View v = fragment.getView();
        rootView = v;
        mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mFab = v.findViewById(R.id.add_fab);

    }

    @Override public void onItemClicked(PayCounterModel item) {
        if (item != null) {
            //            IntentUtils.startDetailPay(getActivity(), DetailPayFragment
            // .MODE_DETAIL, item);
        }
    }

    public void bindClickListener(CountersClickListeners listener) {
        mFab.setOnClickListener(v -> listener.onClickAdd());
        mSwipeRefreshLayout.setOnRefreshListener(listener::onRefresh);
    }

    public void setupAdapter(ArrayList<PayCounterModel> list) {
        mAdapter = new PayCounterAdapter(list);
        mAdapter.setOnItemPickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceDecoration(
                DisplayUtils.pxToDp(rootView.getContext(), 32)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
    }


    void adapterDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    void disableRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
