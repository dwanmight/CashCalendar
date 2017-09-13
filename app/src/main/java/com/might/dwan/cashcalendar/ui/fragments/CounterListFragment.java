package com.might.dwan.cashcalendar.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.db_models.PayCounterDB;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.adapter.PayCounterAdapter;
import com.might.dwan.cashcalendar.utils.IntentUtils;

import java.util.ArrayList;

/**
 * Created by Might on 25.08.2017.
 */

public class CounterListFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<PayCounterModel> mList;
    private PayCounterAdapter mAdapter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_counter_list, container, false);
        initViews(v);
        setupList();
        loadData(null);
        return v;
    }

    private void initViews(View v) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) v.findViewById(R.id.add_fab);
    }

    private void setupList() {
        mList = new ArrayList<>();
        mAdapter = new PayCounterAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadData(String last_id) {
        try {
            mList.addAll(new PayCounterDB(getActivity().getBaseContext()).load(20, last_id));
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onStart() {
        super.onStart();
        setListeners();
    }

    private void setListeners() {
        mFab.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fab:
                startDetailItem(DetailPayFragment.MODE_NEW, null);
                break;
        }
    }

    private void startDetailItem(int mode, PayCounterModel model) {
        IntentUtils.startDetailPay(getActivity(), mode, model);
    }

    @Override public void onRefresh() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        loadData(null);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
